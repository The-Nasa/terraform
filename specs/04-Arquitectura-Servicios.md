# Especificación: Arquitectura en Capas (Capa de Servicios)

## Descripción
Para mantener un código limpio, mantenible y escalable, el backend (`weather-backend`) debe seguir estrictamente un patrón de **Arquitectura en Capas**. 

Específicamente, se requiere la separación de la lógica de negocio de los controladores a través de una **Capa de Servicios**.

## Reglas de Arquitectura

1. **Controladores (`@RestController`)**:
   - Solo deben ser responsables de recibir peticiones HTTP, validar las entradas (DTOs) y retornar respuestas HTTP (ej. `ResponseEntity`).
   - **No deben** contener reglas de negocio, validaciones complejas de base de datos ni llamadas directas a APIs de terceros.
   - Deben inyectar y delegar el procesamiento a clases de la capa de Servicios.

2. **Servicios (`@Service`)**:
   - Residen en el paquete `com.example.weatherapi.service`.
   - Contienen toda la lógica central de la aplicación (reglas de negocio, procesamiento de datos, encriptación, validaciones pesadas).
   - Son el punto de unión entre los controladores y la capa de datos (Repositorios) o clientes externos.

## Beneficios
- **Testabilidad**: Es mucho más fácil hacer pruebas unitarias (Unit Tests) a un `@Service` sin necesidad de levantar el contexto HTTP (MVC).
- **Reusabilidad**: Diferentes controladores o incluso otros servicios pueden reutilizar la misma lógica inyectando el servicio necesario.
- **Mantenibilidad**: Previene el antipatrón "Fat Controller" (controladores gigantes y difíciles de leer).

## Ejemplo de Flujo
Petición HTTP -> `WeatherController` -> `WeatherService.getWeather(location)` -> (retorna datos) -> `WeatherController` responde JSON.
