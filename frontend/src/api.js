const BASE = '';

async function request(path, opts) {
  const res = await fetch(path, {
    headers: { 'Content-Type': 'application/json' },
    ...opts,
  });
  const text = await res.text();
  try { return JSON.parse(text); } catch (e) { return text; }
}

export const login = (email, password) => request('/api/auth/login', { method: 'POST', body: JSON.stringify({ email, password }) });
export const register = (payload) => request('/api/auth/register', { method: 'POST', body: JSON.stringify(payload) });
export const getUsers = () => request('/api/users');
export const getWorkoutsByClient = (clientId) => request(`/api/workouts/client/${clientId}`);
export const postWorkout = (payload) => request('/api/workouts', { method: 'POST', body: JSON.stringify(payload) });
export const updateWorkout = (id, payload) => request(`/api/workouts/${id}`, { method: 'PUT', body: JSON.stringify(payload) });
