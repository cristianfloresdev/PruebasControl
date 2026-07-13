# Emporio Backend

Backend de e-commerce para práctica de entorno laboral simulado.
Java 21 + Spring Boot 3 + MySQL + Flyway.

## Estado actual (Sprint 0 + Sprint 1)

- Setup del proyecto y conexión a MySQL.
- CRUD de **Categorías** (crear, listar).
- CRUD de **Productos** (crear, obtener por id, listar, actualizar, eliminar con soft delete).

## Arquitectura

Arquitectura por capas clásica, cada una con una única responsabilidad:

```
controller  -> recibe HTTP, valida el shape del request, no tiene lógica de negocio
service     -> lógica de negocio, transacciones
repository  -> acceso a datos (Spring Data JPA)
model       -> entidades JPA (nunca se exponen directo en la API)
dto         -> lo que realmente viaja por la API (request/response)
mapper      -> traduce entre entidad y DTO
exception   -> excepciones de negocio + manejo global de errores
```

**Regla de oro:** el controller nunca toca una entidad ni un repository directo,
solo habla con el service usando DTOs.

## Cómo levantarlo

1. Crea la base de datos en tu MySQL local (o deja que la cree sola, ver `application.yml`):
   ```sql
   CREATE DATABASE emporio_db;
   ```
2. Ajusta usuario/password si no son `root`/`root`, por variables de entorno:
   ```
   DB_USERNAME=tu_usuario
   DB_PASSWORD=tu_password
   ```
3. Corre el proyecto:
   ```
   mvn spring-boot:run
   ```
4. Flyway va a crear las tablas (`V1__init.sql`) e insertar datos de prueba
   (`V2__seed_data.sql`) automáticamente al arrancar.

## Endpoints disponibles

| Método | Endpoint | Descripción |
|---|---|---|
| POST | `/api/v1/categorias` | Crea una categoría |
| GET | `/api/v1/categorias` | Lista categorías activas |
| POST | `/api/v1/productos` | Crea un producto |
| GET | `/api/v1/productos/{id}` | Obtiene un producto por id |
| GET | `/api/v1/productos` | Lista productos activos |
| PUT | `/api/v1/productos/{id}` | Actualiza un producto |
| DELETE | `/api/v1/productos/{id}` | Elimina (soft delete) un producto |

## Decisiones de diseño a notar

- `ddl-auto: validate`, no `update`: el esquema lo controla Flyway, Hibernate
  solo valida que las entidades coincidan con las tablas. Si no coinciden,
  la app falla al arrancar en vez de alterar el schema en silencio.
- Eliminar un producto es **soft delete** (`activo = false`), no un DELETE real,
  porque en un e-commerce real un producto puede estar referenciado en pedidos pasados.
- `@ManyToOne(fetch = FetchType.LAZY)` en `Producto -> Categoria`: no cargamos
  la categoría completa a menos que se pida explícitamente.
- Los DTOs de request llevan validaciones con Bean Validation (`@NotBlank`,
  `@Positive`, etc.) para que el controller rechace datos inválidos antes de
  que lleguen al service.

## Próximos sprints

- Sprint 2: Clientes + autenticación JWT.
- Sprint 3: Pedidos + Inventario con reglas de negocio.
- Sprint 4: Pagos mock + reportes + bug hunting.
- Sprint 5: Dockerización (app + MySQL en docker-compose).
