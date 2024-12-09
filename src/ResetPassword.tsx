import React, { useState, useEffect } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";
import api from "./FetchApi";

const ResetPassword = () => {
  const [password, setPassword] = useState<string>("");
  const [confirmPassword, setConfirmPassword] = useState<string>("");
  const [message, setMessage] = useState<string>("");
  const [searchParams] = useSearchParams();
  const navigate = useNavigate();
  
  const token = searchParams.get("token");
  console.log("Token aqui: ", token);
  

  useEffect(() => {
    if (!token) {
      setMessage("Token inválido ou expirado. Por favor, tente novamente.");
      navigate("/"); 
    }
  }, [token, navigate]);

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    if (password !== confirmPassword) {
      setMessage("As senhas não coincidem!");
      return;
    }

    try {
      const response = await api.post("/reset-password", { token, password });

      if (response.status === 200) {
        setMessage("Senha redefinida com sucesso!");
      } else {
        setMessage("Erro ao redefinir senha. Tente novamente.");
      }
    } catch (error) {
      console.error(error);
      setMessage("Erro interno de servidor. Por favor, tente novamente.");
    }
  };

  if (!token) {
    return <p>Carregando...</p>;
  }

  return (
    <div>
      <h1>Redefinir Senha</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Nova senha:</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Confirme a nova senha:</label>
          <input
            type="password"
            value={confirmPassword}
            onChange={(e) => setConfirmPassword(e.target.value)}
            required
          />
        </div>
        <button type="submit">Redefinir Senha</button>
      </form>
      {message && <p>{message}</p>}
    </div>
  );
};

export default ResetPassword;
