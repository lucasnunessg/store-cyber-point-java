import { ChangeEvent, FormEvent, useEffect, useState } from 'react';
import { useNavigate } from "react-router-dom";
import { jwtDecode } from 'jwt-decode';
import axios from 'axios';
import PersonRegister from './PersonRegister';
import ForgotPassword from './ForgotPassword';
import './LoginStyle.css'

interface DecodedToken {
  sub: string;
  role: string;
}

function Login() {
  const [username, setUsername] = useState(""); 
  const [password, setPassword] = useState<string>(""); 
  const [error, setError] = useState<string | null>(null);
  const [welcomeUsernameFromToken, setWelcomeUsernameFromToken] = useState<string | null>(null);
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem('username');
    localStorage.removeItem('token');  // token também
    setWelcomeUsernameFromToken(null); // Atualiza o estado para remover a mensagem de boas-vindas
    window.location.reload();
    navigate('/'); 
  };

  useEffect(() => {
    const storedUserName = localStorage.getItem('username');
    setWelcomeUsernameFromToken(storedUserName);
  }, [])

  const handleUsername = (event: ChangeEvent<HTMLInputElement>) => {
    setUsername(event.target.value);
  };

  const handlePasswordChange = (event: ChangeEvent<HTMLInputElement>) => {
    setPassword(event.currentTarget.value);
  };

  const handleLogin = async (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    setError(null);
    try {
      const response = await axios.post("http://localhost:8080/auth/login", {
        username: username, 
        password: password
      });

      const token = response.data.token;
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
      localStorage.setItem('token', token);

      const decodedToken = jwtDecode<DecodedToken>(token);
      const userName = decodedToken.sub;

      setWelcomeUsernameFromToken(userName); // Exibe mensagem de boas-vindas
      localStorage.setItem('username', userName);
      
      navigate('/products');
      window.location.reload();
    } catch (error: any) {
      console.error('Error:', error);
      setError(error.response?.data.message || 'Login ou senha inválidos');
      setWelcomeUsernameFromToken(null);
    }
  };

  return (
    <div className="login-page-container">
      <form onSubmit={handleLogin} className="login-form-container">
        <div className="input-group">
          <label htmlFor="username" className="input-label">Username</label>
          <input
            type="text"
            id="username"
            value={username}
            onChange={handleUsername}
            required
            className="username-input-field"
          />
        </div>
        <div className="input-group">
          <label htmlFor="password" className="input-label">Password</label>
          <input
            type="password"
            id="password"
            value={password}
            onChange={handlePasswordChange}
            required
            className="password-input-field"
          />
        </div>
        {welcomeUsernameFromToken && (
          <h3 className="welcome-message-text">Seja bem-vindo, {welcomeUsernameFromToken}!</h3>
        )}
        {error && <p className="login-error-message">{error}</p>}
        <button type="submit" className="login-submit-btn">Login</button> 
        <button type="button" onClick={handleLogout} className="logout-btn">Logout</button> 
      </form>
      <PersonRegister />
      <ForgotPassword />
    </div>
  );
}

export default Login;
