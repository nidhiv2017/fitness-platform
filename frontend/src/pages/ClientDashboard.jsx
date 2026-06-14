import { useEffect, useState } from 'react';
import { getUsers, getWorkoutsByClient, updateWorkout } from '../api';

export default function ClientDashboard(){
  const [me,setMe]=useState(null);
  const [workouts,setWorkouts]=useState([]);
  useEffect(()=>{
    getUsers().then(list=>{
      const client = list && list.find(u=>u.email==='client@example.com');
      setMe(client);
      if (client) getWorkoutsByClient(client.id).then(ws=>setWorkouts(ws || []));
    });
  },[]);

  async function toggleComplete(w){
    await updateWorkout(w.id, {...w, isCompleted: !w.isCompleted});
    setWorkouts(await getWorkoutsByClient(me.id));
  }

  if (!me) return <div>Loading profile...</div>;

  return (
    <div>
      <h2>Client Dashboard</h2>
      <div className="card">
        <h3>Profile</h3>
        <div>{me.name} - {me.age} - {me.gender}</div>
      </div>
      <div className="card">
        <h3>Workouts</h3>
        {workouts.map(w=> (
          <div key={w.id} className="workout">
            <div>{w.exerciseName} ({w.targetSets} x {w.targetReps})</div>
            <label><input type="checkbox" checked={w.isCompleted} onChange={()=>toggleComplete(w)} /> Completed</label>
            <div>{w.clientComment}</div>
          </div>
        ))}
      </div>
    </div>
  );
}
