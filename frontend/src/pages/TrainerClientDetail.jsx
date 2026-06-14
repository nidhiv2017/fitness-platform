import { useEffect, useState } from 'react';
import { getWorkoutsByClient, postWorkout } from '../api';

export default function TrainerClientDetail({ clientId }){
  const [workouts,setWorkouts]=useState([]);
  const [exercise,setExercise]=useState('');
  const [sets,setSets]=useState(3);
  const [reps,setReps]=useState(10);

  useEffect(()=>{ if (clientId) getWorkoutsByClient(clientId).then(r=>setWorkouts(r||[])); },[clientId]);

  async function add(e){
    e.preventDefault();
    await postWorkout({ clientId, exerciseName: exercise, targetSets: sets, targetReps: reps });
    setWorkouts(await getWorkoutsByClient(clientId));
  }

  return (
    <div className="card">
      <h3>Client Detail</h3>
      <form onSubmit={add}>
        <input placeholder="exercise" value={exercise} onChange={e=>setExercise(e.target.value)} />
        <input type="number" value={sets} onChange={e=>setSets(parseInt(e.target.value||0))} />
        <input type="number" value={reps} onChange={e=>setReps(parseInt(e.target.value||0))} />
        <button className="btn-primary" type="submit">Add Exercise</button>
      </form>
      <div>
        {workouts.map(w=> <div key={w.id}>{w.exerciseName} - {w.targetSets}x{w.targetReps}</div>)}
      </div>
    </div>
  );
}
