import { useState } from 'react';
import { register } from '../api';
import { useNavigate } from 'react-router-dom';

export default function RegisterPage(){
  const [name,setName]=useState('');
  const [email,setEmail]=useState('');
  const [password,setPassword]=useState('');
  const [role,setRole]=useState('client');
  const navigate = useNavigate();

  async function handle(e){
    e.preventDefault();
    const payload = { name, email, password, role };
    const res = await register(payload);
    if (res && res.email) navigate('/login');
    else alert('Register failed');
  }

  return (
    <div className="card">
      <h2>Register</h2>
      <form onSubmit={handle}>
        <input placeholder="name" value={name} onChange={e=>setName(e.target.value)} />
        <input placeholder="email" value={email} onChange={e=>setEmail(e.target.value)} />
        <input placeholder="password" type="password" value={password} onChange={e=>setPassword(e.target.value)} />
        <select value={role} onChange={e=>setRole(e.target.value)}>
          <option value="client">Client</option>
          <option value="trainer">Trainer</option>
        </select>
        <button className="btn-primary" type="submit">Register</button>
      </form>
    </div>
  );
}
