import { useEffect, useState } from 'react';
import { getUsers } from '../api';

export default function TrainerDashboard(){
  const [clients,setClients]=useState([]);
  useEffect(()=>{ getUsers().then(r=>setClients(r || [])); },[]);
  return (
    <div>
      <h2>Trainer Dashboard</h2>
      <div className="card">
        <h3>Global Client Directory</h3>
        <table>
          <thead><tr><th>Name</th><th>Age</th><th>Gender</th><th>Action</th></tr></thead>
          <tbody>
            {clients.filter(c=>c.role==='client').map(c=> (
              <tr key={c.id}><td>{c.name}</td><td>{c.age}</td><td>{c.gender}</td><td><button className="btn">View All</button></td></tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
