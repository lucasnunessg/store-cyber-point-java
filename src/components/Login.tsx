import { useEffect, useState } from 'react';
import api from '../FetchApi';

function Login() {
  const [email, setEmail] = useState<String>("");
  const [password, setPassword] = useState<String>("");

 const handleLogin = async(event: React.FormEvent) => {
  event.preventDefault();
  const responseLogin = await api.post("/auth/login", {
    email, password
  });
 }
}

export default Login;