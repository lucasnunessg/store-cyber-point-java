import React, { useState } from 'react';
import api from './FetchApi';

function Login() {
  const [username, setUsername] = useState<string>(""); 
  const [password, setPassword] = useState<string>(""); 
  const [error, setError] = useState<string | null>(null);
  const [loading, setLoading] = useState<boolean>(false);

  const handleLogin = async (event: React.FormEvent) => {
    event.preventDefault();
    setLoading(true);
    setError(null) //limpa erros anteriores
    try {
      const responseLogin = await api.post("/auth/login", {
        username, 
        password
      });
    } catch (error) {
      setError("Login falhou!")
      console.error("Login failed:", error);
    } finally{
      setLoading(false);
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
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </div>
        <div>
          <label htmlFor="password">Password</label>
          <input
            type="password"
            id="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        {error && <p style={{ color: 'red' }}>{error}</p>}
        <button type="submit" disabled={loading}>
          {loading ? 'Logging in...' : 'Login'}
        </button>
      </form>
    </div>
  );
}

export default Login;
