import { ChangeEvent, FormEvent, useState } from 'react';
import { useNavigate } from "react-router-dom";
import { jwtDecode } from 'jwt-decode';
import axios from 'axios';

interface DecodedToken {
    sub: string;
    role: string;
  };

function Login() {
  const [username, setUsername] = useState(""); 
  const [password, setPassword] = useState<string>(""); 
  const [error, setError] = useState<string | null>(null);
  const navigate = useNavigate();



  const handleUsername = (event: ChangeEvent<HTMLInputElement>) => {
    setUsername(event.target.value);
  };

  const handlePasswordChange = (event: ChangeEvent<HTMLInputElement>) => {
    setPassword(event.currentTarget.value);
  };

  const handleLogin = async (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    setError(null);
    axios.post("http://localhost:8080/auth/login", {
        username: username, 
        password: password
      }).then((response) => {
        const token = response.data.token;
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
        localStorage.setItem('token', token);
        const decodedToken = jwtDecode<DecodedToken>(token);
        const userName = decodedToken.sub;
        setUsername(userName);
        localStorage.setItem('username', userName);
       navigate('/products');
        
      })
      .catch((error) => {
        console.error('Error:', error);

        setError(error.response?.data.message || 'An error occurred')
      });    
     
          
  }

  return (
    <div>
      <form onSubmit={handleLogin}>
        <div>
          <label htmlFor="username">Username</label>
          <input
            type="text"
            id="username"
            value={username}
            onChange={handleUsername}
            required
          />
        </div>
        <div>
          <label htmlFor="password">Password</label>
          <input
            type="password"
            id="password"
            value={password}
            onChange={handlePasswordChange}
            required
          />
        </div>
        {error && <p style={{ color: 'red' }}>{error}</p>}
        <button type="submit">Login</button> 
      </form>
    </div>
  );
}

export default Login;