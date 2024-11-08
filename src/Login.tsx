import React, { useState } from 'react';
import api from './FetchApi';

function Login() {
  const [username, setUsername] = useState<string>(""); 
  const [password, setPassword] = useState<string>(""); 

  const handleLogin = async (event: React.FormEvent) => {
    event.preventDefault();
    try {
      const responseLogin = await api.post("/auth/login", {
        username, 
        password
      });
    } catch (error) {
      console.error("Login failed:", error);
    }
  }

  return (
    <div>
      <form onSubmit={handleLogin}>
        <label htmlFor="username">Username</label>
        <input
          type="text"
          id="username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          required
        />
        <label htmlFor="password">Password</label>
        <input
          type="password"
          id="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
        <button type="submit">Login</button>
      </form>
    </div>
  );
}

export default Login;
