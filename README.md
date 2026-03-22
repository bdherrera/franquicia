# API de Franquicias

API REST desarrollada con **Spring Boot**, **Spring Data JPA** y **MySQL** para la gestión de franquicias, sucursales y productos.

---

## Contenido

- [Descripción](#descripción)
- [Tecnologías utilizadas](#tecnologías-utilizadas)
- [Requisitos del sistema](#requisitos-del-sistema)
- [Estructura del proyecto](#estructura-del-proyecto)
- [Configuración](#configuración)
- [Ejecución en entorno local](#ejecución-en-entorno-local)
- [Acceso a la aplicación](#acceso-a-la-aplicación)
- [Problemas comunes](#problemas-comunes)
- [Comandos útiles](#comandos-útiles)

---

## Descripción

Este proyecto expone una API REST para la administración de:

- **Franquicias**
- **Sucursales**
- **Productos** por sucursal

Construido con Spring Boot, persistencia JPA sobre MySQL y documentación automática con Swagger/OpenAPI.

---

## Tecnologías utilizadas

| Tecnología | Versión | Uso |
|---|---|---|
| Java | 17 | Lenguaje principal |
| Spring Boot | 3.5.12 | Framework base |
| Spring Data JPA | — | Persistencia de datos |
| Spring Validation | — | Validación de entradas |
| Spring Actuator | — | Métricas y salud |
| Flyway | — | Migraciones de base de datos |
| MySQL | 8.0 | Base de datos relacional |
| Maven | 3.9+ | Gestión de dependencias |
| Docker / Compose | — | Contenedorización |
| OpenAPI / Swagger | — | Documentación automática |

---

## Requisitos del sistema

### Software obligatorio

| Requisito | Descripción |
|---|---|
| JDK 17 | Compilación y ejecución de la aplicación |
| Maven 3.9+ | Gestión de dependencias y empaquetado |
| Docker Desktop | Ejecución con Docker Compose (recomendado) |

### Puertos utilizados

| Puerto | Servicio |
|---|---|
| `8080` | API Spring Boot |
| `3306` | MySQL |

> Si el puerto `3306` ya está en uso, cambia el mapeo del contenedor en `docker-compose.yml`.

### Hardware recomendado

- **Procesador:** 2 núcleos o superior
- **Memoria RAM:** mínimo 4 GB, recomendado 8 GB
- **Espacio libre:** al menos 2 GB

---

## Estructura del proyecto

```text
src/
 ├── main/
 │   ├── java/co/prueba/accenture/backend/
 │   │   ├── controller/
 │   │   ├── service/
 │   │   ├── repository/
 │   │   ├── entity/
 │   │   ├── dto/
 │   │   ├── exception/
 │   │   ├── config/
 │   │   └── PruebaAccentureDevApplication.java
 │   └── resources/
 │       ├── application.yml
 │       └── db/migration/
 └── test/java/
Dockerfile
docker-compose.yml
pom.xml
README.md
```

### Archivos principales de construcción

- **`pom.xml`** — dependencias, plugins y configuración de Maven
- **`Dockerfile`** — construye la imagen Docker de la API
- **`docker-compose.yml`** — orquesta la aplicación y la base de datos localmente
- **`application.yml`** — configuración base con soporte a variables de entorno

El artefacto generado por Maven es:

```text
target/prueba-accenture-dev-0.0.1-SNAPSHOT.jar
```

---

## Configuración

La aplicación lee su configuración desde variables de entorno. Si no se definen, se usan los valores por defecto de `application.yml`.

| Variable | Valor por defecto | Descripción |
|---|---|---|
| `DB_HOST` | `localhost` | Host de la base de datos |
| `DB_PORT` | `3306` | Puerto de MySQL |
| `DB_NAME` | `franquicias_db` | Nombre de la base de datos |
| `DB_USER` | `root` | Usuario de MySQL |
| `DB_PASSWORD` | `root` | Contraseña de MySQL |
| `SERVER_PORT` | `8080` | Puerto del servidor HTTP |

---

## Ejecución en entorno local

### Opción 1: Docker Compose ✅ (recomendada)

Levanta automáticamente **MySQL 8.0** y la **API Spring Boot**. No requiere instalar MySQL localmente.

**1. Ubícate en la raíz del proyecto:**

```bash
cd PruebaAccentureDev
```

**2. Construir y levantar los contenedores:**

```bash
docker compose up --build
```

**3. (Opcional) Ejecutar en segundo plano:**

```bash
docker compose up --build -d
```

**4. Detener los contenedores:**

```bash
docker compose down
```

---

### Opción 2: Sin Docker (manual)

Requiere tener **MySQL 8.0** corriendo localmente.

**1. Crear la base de datos:**

```sql
mysql -u root -p
CREATE DATABASE franquicias_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
EXIT;
```

**2. Configurar las variables de entorno:**

```bash
# Linux / macOS
export DB_HOST=localhost
export DB_PORT=3306
export DB_NAME=franquicias_db
export DB_USER=root
export DB_PASSWORD=tu_password
```

```powershell
# Windows (PowerShell)
$env:DB_HOST="localhost"
$env:DB_PORT="3306"
$env:DB_NAME="franquicias_db"
$env:DB_USER="root"
$env:DB_PASSWORD="tu_password"
```

**3. Compilar y ejecutar:**

```bash
mvn clean package -DskipTests
java -jar target/prueba-accenture-dev-0.0.1-SNAPSHOT.jar
```

O directamente con Maven:

```bash
mvn spring-boot:run
```

---

## Acceso a la aplicación

| Recurso | URL |
|---|---|
| API base | http://localhost:8080 |
| Health check | http://localhost:8080/actuator/health |
| Swagger UI | http://localhost:8080/swagger-ui.html |
| OpenAPI JSON | http://localhost:8080/v3/api-docs |

---

## Problemas comunes

### Puerto 3306 ocupado

Si MySQL ya está instalado localmente, modifica el mapeo de puertos en `docker-compose.yml`:

```yaml
ports:
  - "3307:3306"  # puerto externo:interno
```

Luego actualiza la variable `DB_PORT=3307` en tu entorno.

### Error de conexión a la base de datos

Verifica que:
- MySQL esté corriendo antes de iniciar la API
- Las credenciales en las variables de entorno sean correctas
- El esquema de base de datos exista (Flyway aplica las migraciones automáticamente al iniciar)

### La aplicación no inicia con Docker

Verifica que Docker Desktop esté activo y consulta los logs:

```bash
docker compose logs -f   # logs en tiempo real
docker compose ps        # estado de los servicios
```

---

## Comandos útiles

| Comando | Descripción |
|---|---|
| `docker compose up --build` | Construir imágenes y levantar servicios |
| `docker compose up -d` | Levantar en segundo plano |
| `docker compose down` | Detener y eliminar contenedores |
| `docker compose logs -f` | Ver logs en tiempo real |
| `docker compose ps` | Ver estado de los servicios |
| `mvn clean package -DskipTests` | Compilar sin ejecutar tests |
| `mvn spring-boot:run` | Ejecutar la app con Maven |
| `mvn test` | Ejecutar los tests unitarios |


## Despliegue en AWS

La API está desplegada en AWS App Runner (us-east-2):

**URL base:** https://jv32bahzin.us-east-2.awsapprunner.com

| Recurso | URL |
|---|---|
| Swagger UI | https://jv32bahzin.us-east-2.awsapprunner.com/swagger-ui/index.html |
| Health check | https://jv32bahzin.us-east-2.awsapprunner.com/actuator/health |
| OpenAPI JSON | https://jv32bahzin.us-east-2.awsapprunner.com/v3/api-docs |

### Infraestructura utilizada

| Servicio | Uso |
|---|---|
| Amazon ECR | Registro privado de la imagen Docker |
| Amazon RDS MySQL 8.0 | Base de datos en subred privada sin acceso público |
| AWS App Runner | Despliegue y escalado automático del contenedor |
| VPC + VPC Connector | Comunicación privada entre App Runner y RDS |
