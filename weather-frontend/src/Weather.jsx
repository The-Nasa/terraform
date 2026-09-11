import { useState } from 'react';

export default function Weather({ onLogout }) {
  const [location, setLocation] = useState('');
  const [error, setError] = useState('');
  const [weatherData, setWeatherData] = useState(null);

  const handleLogout = () => {
    localStorage.removeItem('jwt_token');
    onLogout();
  };

  const handleConsult = async () => {
    if (!location) {
      setError('Por favor selecciona una ubicación.');
      return;
    }

    setError('');
    const token = localStorage.getItem('jwt_token');

    try {
      const response = await fetch(`http://localhost:8080/api/v1/weather?location=${location}`, {
        method: 'GET',
        headers: { 
          'Authorization': `Bearer ${token}` 
        }
      });

      if (response.status === 401 || response.status === 403) {
        handleLogout();
        return;
      }

      if (response.ok) {
        const data = await response.json();
        setWeatherData(data);
      } else {
        setError('Error al consultar el clima.');
      }
    } catch (err) {
      console.error('Error:', err);
      setError('Error de conexión con el servidor.');
    }
  };

  return (
    <div className="flex-center bg-gradient">
      <div className="card glass-effect wide-card">
        <div className="header">
          <h1 className="title">Estado del Clima</h1>
          <button onClick={handleLogout} className="btn btn-secondary btn-sm">Cerrar Sesión</button>
        </div>
        
        <div className="search-section">
          <label htmlFor="locationSelect">Selecciona una ubicación:</label>
          <div className="search-controls">
            <select 
              id="locationSelect" 
              className="select-input"
              value={location}
              onChange={(e) => setLocation(e.target.value)}
            >
              <option value="" disabled>Elige una ciudad...</option>
              <option value="Tingo Maria">Tingo María (Perú)</option>
              <option value="Huanuco">Huánuco (Perú)</option>
            </select>
            <button onClick={handleConsult} className="btn btn-primary">Consultar</button>
          </div>
        </div>

        {weatherData && (
          <div className="result-section fade-in">
            <h2 className="location-title">{weatherData.location}</h2>
            <div className="weather-grid">
              <div className="weather-box">
                <span className="label">Temperatura</span>
                <span className="value">{weatherData.temperature}</span>
              </div>
              <div className="weather-box">
                <span className="label">Condición</span>
                <span className="value">{weatherData.condition}</span>
              </div>
              <div className="weather-box">
                <span className="label">Humedad</span>
                <span className="value">{weatherData.humidity}</span>
              </div>
            </div>
          </div>
        )}
        
        {error && <p className="error-text">{error}</p>}
      </div>
    </div>
  );
}
