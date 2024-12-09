import { useState } from "react";
import api from "./FetchApi";
import './ForgotPasswodStyle.css';

const ForgotPassword = () => {
  const [username, setUserName] = useState("");
  const [message, setMessage] = useState("");
  const [showForm, setShowForm] = useState(false);

  const handleForgotPassword = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    try {
      const response = await api.post("/forgot-password", { username });
      if (response.status === 200) {
        setMessage("Um link de redefinição de senha foi enviado ao seu email.");
      } else {
        setMessage("Erro ao enviar email. Tente novamente mais tarde.");
      }
    } catch (error) {
      console.error("Erro ao solicitar redefinição de senha:", error);
      setMessage("Ocorreu um erro inesperado. Tente novamente.");
    }
  };

  return (
    <>
      {!showForm && (
        <button className="forgot-password-button" onClick={() => setShowForm(true)}>
          Esqueceu a senha?
        </button>
      )}
      {showForm && (
        <div className="forgot-password-container">
          <h1 className="form-title">Redefinir Senha</h1>
          <form onSubmit={handleForgotPassword} className="form-forgot-password">
            <div className="input-group">
              <label className="input-label">Username:</label>
              <input
                type="text"
                value={username}
                onChange={(e) => setUserName(e.target.value)}
                className="input-forgot-password"
                required
                placeholder="Digite seu nome de usuário"
              />
            </div>
            <button type="submit" className="button-forgot-password">
              Receber código no E-mail
            </button>
          </form>
          {message && <p className="message">{message}</p>}
        </div>
      )}
    </>
  );
};

export default ForgotPassword;
