import { ChangeEvent, FormEvent, useState } from 'react';
import { useNavigate } from "react-router-dom";
import api from './FetchApi';

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
    console.log("Tentando fazer login...");
    try {
      const response = await api.post("/auth/login", {
        username, 
        password
      });
      
      // Agora estamos usando 'response' ao invés de 'responseLogin'
      if (response.status === 200) {
        console.log("Resposta de login bem-sucedida:", response.data);
        // Aqui você pode navegar para outra página se necessário
       navigate('/products');
      }
    } catch (error) {
      console.error("Falha no login:", error);
      setError("Erro ao fazer login. Verifique as credenciais e tente novamente.");
    }
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