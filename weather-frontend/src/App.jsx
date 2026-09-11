import { useState, useEffect } from 'react';
import Login from './Login';
import Weather from './Weather';

export default function App() {
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  useEffect(() => {
    const token = localStorage.getItem('jwt_token');
    if (token) {
      setIsAuthenticated(true);
    }
  }, []);

  const handleLoginSuccess = () => {
    setIsAuthenticated(true);
  };

  const handleLogout = () => {
    setIsAuthenticated(false);
  };

  return (
    <>
      {isAuthenticated ? (
        <Weather onLogout={handleLogout} />
      ) : (
        <Login onLoginSuccess={handleLoginSuccess} />
      )}
    </>
  );
}
