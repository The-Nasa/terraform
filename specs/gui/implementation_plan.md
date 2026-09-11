# Migración del Frontend a React + Vite

El objetivo es modernizar la aplicación frontend actual (basada en HTML, CSS y JavaScript puro) utilizando React y Vite para un entorno de desarrollo más rápido y una arquitectura basada en componentes.

## User Review Required

> [!IMPORTANT]
> El directorio `weather-frontend` actual contiene los archivos HTML originales. Para evitar conflictos con la inicialización de Vite, los archivos originales se moverán a un directorio temporal llamado `legacy-frontend` (o simplemente se eliminarán una vez que el código haya sido migrado a React). Recomiendo conservar temporalmente un respaldo hasta que verifiquemos que React funciona.

## Open Questions

> [!WARNING]
> 1. ¿Deseas utilizar alguna librería de enrutamiento como `react-router-dom` para manejar las páginas de Login y Clima, o prefieres un renderizado condicional simple (basado en el estado del token) como se hace actualmente?
> 2. ¿Deseas mantener los mismos estilos visuales (el mismo CSS) o aprovechamos para actualizar el diseño?

## Proposed Changes

La migración consistirá en los siguientes pasos:

### 1. Inicialización del Proyecto

Ejecutaremos el comando de Vite para crear un nuevo proyecto React en el directorio `weather-frontend` (después de respaldar/limpiar los archivos actuales):
`npx create-vite@latest weather-frontend --template react`

### 2. Migración de Estilos (CSS)

#### [MODIFY] `src/index.css`
Copiaremos el contenido íntegro de `styles.css` actual a los estilos globales de React, asegurando que las clases como `.bg-gradient`, `.card`, y `.glass-effect` sigan funcionando.

### 3. Creación de Componentes React

El código de los archivos HTML se dividirá en componentes funcionales de React:

#### [NEW] `src/Login.jsx`
- Contendrá el formulario de inicio de sesión (`login.html`).
- Manejará el estado del `username` y `password`.
- Realizará la petición `POST` a `/api/v1/auth/login`.
- Guardará el token en `localStorage` y actualizará el estado de la aplicación para mostrar la vista del clima.

#### [NEW] `src/Weather.jsx`
- Contendrá la vista principal (`index.html`).
- Incluirá el selector de ciudades y mostrará el resultado del clima.
- Realizará la petición `GET` a `/api/v1/weather` incluyendo el token Bearer.
- Manejará el cierre de sesión (`logout`) eliminando el token.

#### [MODIFY] `src/App.jsx`
- Actuará como el componente contenedor principal.
- Verificará si existe el `jwt_token` en `localStorage`.
- Si existe, renderizará `<Weather />`. Si no, renderizará `<Login />`.

## Verification Plan

### Automated Tests
- No se han especificado pruebas automatizadas en esta fase inicial, pero el entorno de Vite incluirá soporte para correr el proyecto localmente y asegurar que compila.

### Manual Verification
- Ejecutar `npm install` y luego `npm run dev` en el directorio `weather-frontend`.
- Verificar que la página de Login se muestra inicialmente si no hay token.
- Introducir las credenciales y verificar que redirige a la vista del Clima.
- Probar la consulta del clima seleccionando una ciudad.
- Verificar el cierre de sesión.
