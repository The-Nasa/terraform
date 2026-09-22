# Especificación de Validación de Pruebas antes del Push

## Descripción
Para garantizar la estabilidad y calidad del código en el repositorio, se ha establecido una restricción a nivel de control de versiones. No está permitido subir cambios (hacer `git push`) si las pruebas unitarias y de calidad de código fallan en el entorno local.

## Mecanismo de Validación
La validación se ejecuta automáticamente a través de un **Git Hook** de tipo `pre-push`. Este script intercepta cualquier intento de enviar cambios al servidor remoto y ejecuta una serie de comprobaciones. Si alguna comprobación falla, el envío se cancela.

### Pruebas del Backend (Java/Spring Boot)
- **Directorio:** `weather-backend/`
- **Comando ejecutado:** `./mvnw test`
- **Condición de éxito:** Todas las pruebas unitarias de Maven deben pasar exitosamente (código de salida `0`).
- **Comportamiento en fallo:** Si alguna prueba falla, el script muestra un error indicando que las pruebas del backend fallaron y se detiene el `push`.

### Verificaciones del Frontend (React/Vite)
- **Directorio:** `weather-frontend/`
- **Comando ejecutado:** `npm run lint`
- **Condición de éxito:** El código debe cumplir con las reglas de estilo establecidas por Oxlint (código de salida `0`). *Nota: Se utilizará el comando de linting como validación hasta que se definan pruebas específicas en el frontend.*
- **Comportamiento en fallo:** Si se detectan errores de linting, el script muestra un error indicando que el frontend falló y se detiene el `push`.

## Excepciones
El hook está configurado para ejecutarse localmente. En caso de que se necesite saltar la validación de manera forzada y excepcional (aunque no es recomendado), Git permite el uso del parámetro `--no-verify`:
```bash
git push --no-verify
```
*(Esta acción debe ser utilizada bajo la responsabilidad del desarrollador solo en situaciones de emergencia)*.

## Implementación Técnica
El script de validación se encuentra en la ruta: `.git/hooks/pre-push`.
Este archivo es un script de shell estándar y requiere que tanto el entorno backend como frontend puedan ejecutarse localmente antes de compartir el código con el equipo.
