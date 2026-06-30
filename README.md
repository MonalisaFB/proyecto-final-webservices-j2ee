# 📝 Examen Final

> Aplicación Spring Boot para la gestión de preguntas tipo test, organizadas por temáticas, con autenticación JWT y API REST documentada.

![Java](https://img.shields.io/badge/Java-17-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.0-brightgreen?logo=springboot)
![Maven](https://img.shields.io/badge/Maven-3.9+-blue?logo=apachemaven)
![License](https://img.shields.io/badge/license-academic-lightgrey)

---

## 📚 Índice

- [Requisitos](#-requisitos)
- [Ejecutar la aplicación](#-ejecutar-la-aplicación)
- [Credenciales por defecto](#credenciales-por-defecto)
- [Ejecutar los tests](#-ejecutar-los-tests)
- [Estructura de los tests](#-estructura-de-los-tests)
- [API REST](#-api-rest)
- [Swagger UI](#swagger-ui)
- [Tecnologías](#-tecnologías)

---

## ✅ Requisitos

| Herramienta | Versión |
|---|---|
| Java | 17+ |
| Maven | 3.9+ (o usar el wrapper `./mvnw`) |

---

## 🚀 Ejecutar la aplicación

```bash
./mvnw spring-boot:run
```

La aplicación se inicia en **`http://localhost:8080`**, usando una base de datos **H2 en memoria** (perfil `dev`). Los datos de prueba se cargan automáticamente mediante `import.sql`.

### Credenciales por defecto

| Email | Contraseña | Rol |
|---|---|---|
| `admin@email.com` | `admin123` | `ADMIN` |

---

## 🧪 Ejecutar los tests

```bash
./mvnw test
```

Para ejecutar una clase específica:

```bash
./mvnw test -Dtest=PreguntaServiceTest
./mvnw test -Dtest=PreguntaApiControllerTest
```

---

## 🔍 Estructura de los tests

### Tests unitarios — `PreguntaServiceTest` (8 tests)

Tests del servicio principal con **Mockito**, siguiendo el patrón **AAA** (*Arrange–Act–Assert*).

| Método testeado | Caso |
|---|---|
| `listarTodas()` | Retorna página paginada |
| `buscarPorId()` | Retorna la pregunta cuando existe |
| `buscarPorId()` | Lanza `PreguntaNoEncontradaException` cuando no existe |
| `guardar()` | Guarda y retorna la entidad |
| `eliminar()` | Invoca `deleteById` en el repositorio |
| `listarPorTematica()` | Filtra por temática |
| `filtrar()` | Filtra por temática + tipo |
| `filtrar(null, null)` | Pasa parámetros nulos al repositorio |

### Tests de integración — `PreguntaApiControllerTest` (5 tests)

Tests de la API REST con `@SpringBootTest` + `@AutoConfigureMockMvc`, simulando los services con `@MockitoBean`.

| Endpoint | Escenario |
|---|---|
| `GET /api/preguntas` | Retorna `200` |
| `GET /api/preguntas/{id}` | Retorna `200` cuando existe |
| `GET /api/preguntas/{id}` | Retorna `404` cuando no existe |
| `POST /api/preguntas` | Retorna `201 Created` |
| `DELETE /api/preguntas/{id}` | Retorna `204 No Content` |

---

## 🌐 API REST

### 🔐 Autenticación

```http
POST /api/auth/login
Content-Type: application/json
```

```json
{
  "email": "admin@email.com",
  "password": "admin123"
}
```

**Respuesta:**

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

### ❓ Preguntas

Usa el token JWT en el header `Authorization: Bearer <token>`.

| Método | Endpoint | Descripción | Roles |
|---|---|---|---|
| `GET` | `/api/preguntas` | Listar (con paginación, filtro por temática/tipo) | Autenticado |
| `GET` | `/api/preguntas/{id}` | Obtener por ID | Autenticado |
| `POST` | `/api/preguntas` | Crear | `ADMIN` |
| `PUT` | `/api/preguntas/{id}` | Actualizar | `ADMIN` |
| `DELETE` | `/api/preguntas/{id}` | Eliminar | `ADMIN` |

### Swagger UI

Documentación interactiva disponible en:

```
http://localhost:8080/swagger-ui.html
```

---

## 🛠 Tecnologías

| Categoría | Stack |
|---|---|
| Core | Spring Boot 4.1.0 · Spring Framework 7.x |
| Persistencia | Spring Data JPA · Hibernate 7 |
| Seguridad | Spring Security 7 · JWT (JJWT 0.12) · BCrypt |
| Vistas | Thymeleaf · Layout Dialect |
| Documentación | Springdoc OpenAPI 3 |
| Base de datos | H2 (dev) · MySQL (prod) — ambos en el classpath |
| Utilidades | Lombok |
| Testing | JUnit 5 · Mockito · Spring Boot Test slices (`@WebMvcTest`, etc.) |

---

<p align="center">
  <sub>Proyecto académico — Examen Final del ciclo DAM</sub>
</p>
