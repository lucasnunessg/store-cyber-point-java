import { useState } from "react";
import api from "./FetchApi";
import { useNavigate } from "react-router-dom";

const ForgotPassword = () => {
  const [username, setUserName] = useState("");
  const [message, setMessage] = useState("");
  const [showForm, setShowForm] = useState(false);
  const navigate = useNavigate();

  const handleForgotPassword = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    try {
      const response = await api.post("/forgot-password", {username});
      if (response.status === 200) {
        setMessage("Um link de redefinição de senha foi enviado ao seu email.");
        navigate("/reset-password"); 
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
        <button onClick={() => setShowForm(true)}>Esqueceu a senha?</button>
      )}
      {showForm && (
        <div>
          <h1>Redefinir Senha</h1>
          <form onSubmit={handleForgotPassword}>
            <div>
              <label>Username:</label>
              <input
                type="text"
                value={username}
                onChange={(e) => setUserName(e.target.value)}
                required
              />
            </div>
            <button type="submit">Receber código no E-mail</button>
          </form>
          {message && <p>{message}</p>}
        </div>
      )}
    </>
  );
};

export default ForgotPassword;
