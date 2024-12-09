import { useState } from 'react';
import api from './FetchApi';
import './NewPersonStyle.css'

const PersonRegister = () => {
  const [fullName, setFullName] = useState("");
  const [username, setUserName] = useState("");
  const [role] = useState("CLIENT");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [address, setAddress] = useState("");
  const [success, setSuccess] = useState("");
  const [error, setError] = useState("");
  const [showForm, setShowForm] = useState<boolean>(false);

  const handleCreatePerson = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    try {
      const newPerson = {
        fullname: fullName,
        username,
        email,
        password,
        address,
        role,
      };
      const response = await api.post("/persons", newPerson);
      setSuccess("Pessoa criada com sucesso!");
      console.log(response.data);

      if (response.status === 201) {
        clearForm();
      } else {
        setError("Erro ao criar pessoa!");
      }
    } catch (error) {
      setError('Erro ao criar cliente. Por favor, tente novamente.');
      console.error("Erro ao criar pessoa", error);
      clearForm();
    }
  };

  const clearForm = () => {
    setFullName('');
    setUserName('');
    setEmail('');
    setPassword('');
    setAddress('');
  };

  const cancelHandle = () => {
    setShowForm(false);
    clearForm();
  };

  return (
    <>
      <p className='cadastro'>Não é cadastrado? Cadastre-se!</p>
      {!showForm && (
        <button className='button-cadastro' onClick={() => setShowForm(true)}>Criar conta</button>
      )}
      {showForm && (
        <form onSubmit={handleCreatePerson} className="form-container">
          <div className="form-group">
            <label>Nome completo</label>
            <input
              type='text'
              value={fullName}
              onChange={(e) => setFullName(e.target.value)}
              className="form-input"
            />
          </div>

          <div className="form-group">
            <label>Nome de usuário</label>
            <input
              type='text'
              value={username}
              onChange={(e) => setUserName(e.target.value)}
              className="form-input"
            />
          </div>

          <div className="form-group">
            <label>E-mail</label>
            <input
              type='text'
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              className="form-input"
            />
          </div>

          <div className="form-group">
            <label>Senha</label>
            <input
              type='password'
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              className="form-input"
            />
          </div>

          <div className="form-group">
            <label>Endereço</label>
            <input
              type='text'
              value={address}
              onChange={(e) => setAddress(e.target.value)}
              className="form-input"
            />
          </div>

          {error && <p className="error-message">{error}</p>}

          <div className="form-buttons">
            <button type="submit" className="btn-submit">Cadastrar</button>
            <button type='button' onClick={cancelHandle} className="btn-cancel">Limpar</button>
          </div>
        </form>
      )}
      {success && <p className="success-message">{success}</p>}
    </>
  );
};

export default PersonRegister;
