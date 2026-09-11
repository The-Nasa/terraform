import { useState } from 'react';

export default function Login({ onLoginSuccess }) {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');

    try {
      const response = await fetch('http://localhost:8080/api/v1/auth/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password })
      });

      if (response.ok) {
        const data = await response.json();
        localStorage.setItem('jwt_token', data.token);
        onLoginSuccess();
      } else {
        setError('Credenciales inválidas.');
      }
    } catch (err) {
      console.error('Error:', err);
      setError('Error de conexión con el servidor.');
    }
  };

  return (
    <div className="flex-center bg-gradient">
      <div className="card glass-effect">
        <h1 className="title">Bienvenido</h1>
        <p className="subtitle">Inicia sesión para consultar el clima</p>
        
        <form onSubmit={handleSubmit} className="form-container">
          <div className="input-group">
            <label htmlFor="username">Usuario</label>
            <input 
              type="text" 
              id="username" 
              placeholder="admin" 
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required 
            />
          </div>
          <div className="input-group">
            <label htmlFor="password">Contraseña</label>
            <input 
              type="password" 
              id="password" 
              placeholder="1234" 
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required 
            />
          </div>
          <button type="submit" className="btn btn-primary">Ingresar</button>
          {error && <p className="error-text">{error}</p>}
        </form>
      </div>
    </div>
  );
}
