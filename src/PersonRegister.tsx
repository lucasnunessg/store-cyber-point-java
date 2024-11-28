import { useState } from 'react';
import api from './FetchApi';

const PersonRegister = () => {
  const [fullName, setFullName] = useState("");
  const [username, setUserName] = useState("");
  const [role] = useState("CLIENT")
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [address, setAddress] = useState("");
  const [success, setSuccess] = useState("");
  const [error, setError] = useState("");
  const [showForm, setShowForm] = useState<boolean>(false);

  const handleCreatePerson = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    try{
      const newPerson = {
        fullname: fullName, //aqui tive q mudar pra "fullname: fullName", antes tava fullName e no java esta fullname
        username,
        email,
        password,
        address,
        role,
      }
      const response = await api.post("/persons", newPerson);
      setSuccess("Pessoa criada com sucesso!")
      console.log(response.data); 

      if(response.status === 201) {
        clearForm();
      }else {
        setError("Erro ao criar pessoa!")
      }
      
    }catch(error) {
      setError('Erro ao criar cliente. Por favor, tente novamente.');
      console.error("Erro ao criar pessoa", error);
      clearForm();
    }
  }


  const clearForm = () => {
    setFullName('');
    setUserName('');
    setEmail('');
    setPassword('');
    setAddress('');
  }

  const cancelHandle = () => {
    setShowForm(false)
    clearForm()
  };

  return(
    <>
  <p>Não é cadastrado? Cadastre-se!</p>
  {!showForm && (
    <button onClick={() => setShowForm(true)}>Criar conta</button>
  )}   
  {showForm && (
    <form onSubmit={handleCreatePerson}>
      <div>
        <label>Nome completo</label>
        <input
        type='text'
        value={fullName}
        onChange={(e) => setFullName(e.target.value)} />

        <label>Nome de usuário</label>
        <input
        type='text'
        value={username}
        onChange={(e) => setUserName(e.target.value)} />

        <label>E-mail</label>
        <input
        type='text'
        value={email}
        onChange={(e) => setEmail(e.target.value)} />

        <label>Senha</label>
        <input
        type='password'
        value={password}
        onChange={(e) => setPassword(e.target.value)} />


        <label>Endereço</label>
        <input
        type='text'
        value={address}
        onChange={(e) => setAddress(e.target.value)} />

      </div>
      {error && <p style={{ color: 'red' }} >{error}</p>}
      <button type="submit">Cadastrar</button>
      <button type='button' onClick={cancelHandle}>Limpar</button>
    </form>
  )}
        {success && <p style={{ color: 'green' }}>{success}</p>}

    </>
  )
}

  export default PersonRegister;