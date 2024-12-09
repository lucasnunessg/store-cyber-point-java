import React, { useState, useEffect } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";
import api from "./FetchApi";
import './ResetPasswordStyle.css'

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
    <div className="reset-password-container">
      <h1 className="reset-password-title">Redefinir Senha</h1>
      <form onSubmit={handleSubmit} className="reset-password-form">
        <div className="reset-password-field">
          <label className="reset-password-label">Nova senha:</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            className="reset-password-input"
            required
          />
        </div>
        <div className="reset-password-field">
          <label className="reset-password-label">Confirme a nova senha:</label>
          <input
            type="password"
            value={confirmPassword}
            onChange={(e) => setConfirmPassword(e.target.value)}
            className="reset-password-input"
            required
          />
        </div>
        <button type="submit" className="reset-password-button">Redefinir Senha</button>
      </form>
      {message && (
        <p 
          className={`reset-password-message ${message === "Senha redefinida com sucesso!" ? "success" : ""}`}
        >
          {message}
        </p>
      )}
    </div>
  );
};

export default ResetPassword;
