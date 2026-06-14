import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import TrainerDashboard from './pages/TrainerDashboard';
import ClientDashboard from './pages/ClientDashboard';
import './App.css';

function App(){
  return (
    <BrowserRouter>
      <div className="nav">
        <Link to="/">Home</Link> | <Link to="/login">Login</Link> | <Link to="/register">Register</Link>
      </div>
      <Routes>
        <Route path="/" element={<div style={{padding:20}}>Welcome to Fitness Platform</div>} />
        <Route path="/login" element={<LoginPage/>} />
        <Route path="/register" element={<RegisterPage/>} />
        <Route path="/trainer" element={<TrainerDashboard/>} />
        <Route path="/client" element={<ClientDashboard/>} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
