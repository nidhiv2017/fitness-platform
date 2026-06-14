import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { login } from '../api';

export default function LoginPage() {
  const [email,setEmail] = useState('');
  const [password,setPassword] = useState('');
  const navigate = useNavigate();

  async function handle(e){
    e.preventDefault();
    const res = await login(email,password);
    if (res && res.role === 'trainer') navigate('/trainer');
    else if (res && res.role === 'client') navigate('/client');
    else alert('Login failed');
  }

  return (
    <div className="card">
      <h2>Login</h2>
      <form onSubmit={handle}>
        <input placeholder="email" value={email} onChange={e=>setEmail(e.target.value)} />
        <input placeholder="password" type="password" value={password} onChange={e=>setPassword(e.target.value)} />
        <button className="btn-primary" type="submit">Login</button>
      </form>
    </div>
  );
}
