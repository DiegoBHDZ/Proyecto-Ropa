# Instrucciones para ejecutar el proyecto en Windows

Este proyecto tiene dos partes:
- **Backend**: API REST con Spring Boot (Java)
- **Frontend**: Aplicación web con Angular

Ambas deben estar corriendo al mismo tiempo para que el sistema funcione.

---

## Requisitos previos

Instala los siguientes programas antes de continuar. Todos son gratuitos.

### 1. Java 17

- Descarga desde: https://adoptium.net/temurin/releases/?version=17
- Selecciona: **Windows**, **x64**, **JDK**, versión **17**
- Descarga el instalador `.msi` y ejecútalo
- Durante la instalación activa la opción **"Set JAVA_HOME variable"**
- Verifica la instalación abriendo una terminal (CMD o PowerShell) y ejecutando:
  ```
  java -version
  ```
  Debe mostrar algo como: `openjdk version "17.x.x"`

---

### 2. MySQL 8

- Descarga desde: https://dev.mysql.com/downloads/installer/
- Descarga el instalador **MySQL Installer for Windows** (el más grande, ~450 MB)
- Al instalar, selecciona **"Developer Default"** o al menos **MySQL Server**
- Durante la instalación se te pedirá una contraseña para el usuario `root` — **anótala**
- Verifica la instalación:
  ```
  mysql --version
  ```

---

### 3. Node.js 22 (LTS)

- Descarga desde: https://nodejs.org/
- Descarga la versión **LTS** (la recomendada)
- Ejecuta el instalador `.msi` con todas las opciones por defecto
- Verifica la instalación:
  ```
  node -v
  npm -v
  ```

---

### 4. Maven (opcional — el proyecto ya incluye `mvnw.cmd`)

No es necesario instalarlo por separado. El proyecto incluye `mvnw.cmd` que descarga Maven automáticamente.

---

## Paso 1 — Configurar la base de datos MySQL

Abre la terminal de MySQL. Puedes hacerlo desde el menú Inicio buscando **"MySQL 8.0 Command Line Client"**, o desde CMD ejecutando:

```
mysql -u root -p
```

Ingresa la contraseña de `root` que configuraste durante la instalación.

Una vez dentro, ejecuta estos comandos uno por uno:

```sql
SET GLOBAL validate_password.policy = LOW;
SET GLOBAL validate_password.length = 4;

CREATE DATABASE IF NOT EXISTS ropa_shop_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE USER IF NOT EXISTS 'ropa_dev'@'localhost' IDENTIFIED BY 'ropa_pass';

GRANT ALL PRIVILEGES ON ropa_shop_db.* TO 'ropa_dev'@'localhost';

FLUSH PRIVILEGES;
```

Verifica que funcionó:

```sql
SHOW DATABASES;
```

Debes ver `ropa_shop_db` en la lista. Escribe `exit` para salir de MySQL.

---

## Paso 2 — Ejecutar el Backend (Spring Boot)

1. Abre una terminal (CMD o PowerShell)
2. Navega a la carpeta `api` del proyecto:
   ```
   cd ruta\del\proyecto\api
   ```
   Por ejemplo, si descomprimiste el ZIP en el escritorio:
   ```
   cd C:\Users\TuNombre\Desktop\Proyecto_ropa\api
   ```
3. Ejecuta el backend:
   ```
   mvnw.cmd spring-boot:run
   ```
   La primera vez descargará dependencias de internet (puede tardar unos minutos).

4. El backend está listo cuando veas en la terminal:
   ```
   Started RopaApiApplication in X.XXX seconds
   ```
   El servidor corre en: `http://localhost:8080`

> **No cierres esta terminal.** Déjala abierta mientras usas el sistema.

---

## Paso 3 — Ejecutar el Frontend (Angular)

1. Abre **otra** terminal (CMD o PowerShell) — no cierres la del backend
2. Navega a la carpeta `frontend` del proyecto:
   ```
   cd ruta\del\proyecto\frontend
   ```
   Por ejemplo:
   ```
   cd C:\Users\TuNombre\Desktop\Proyecto_ropa\frontend
   ```
3. Instala las dependencias de Angular (solo la primera vez):
   ```
   npm install
   ```
4. Inicia el frontend:
   ```
   npm start
   ```

5. El frontend está listo cuando veas:
   ```
   Application bundle generation complete.
   Local: http://localhost:4200/
   ```

6. Abre tu navegador y entra a: **http://localhost:4200**

> **No cierres esta terminal.** Déjala abierta mientras usas el sistema.

---

## Resumen — orden de arranque

| Paso | Qué hacer | Terminal |
|------|-----------|----------|
| 1 | Configurar MySQL (solo la primera vez) | MySQL CLI |
| 2 | `mvnw.cmd spring-boot:run` dentro de `/api` | Terminal 1 |
| 3 | `npm install` dentro de `/frontend` (solo la primera vez) | Terminal 2 |
| 4 | `npm start` dentro de `/frontend` | Terminal 2 |
| 5 | Abrir `http://localhost:4200` en el navegador | — |

---

## Posibles errores y soluciones

### "java is not recognized as an internal or external command"
Java no está en el PATH. Reinicia la terminal después de instalar Java, o reinstala Java asegurándote de activar la opción de agregar al PATH.

### "Access denied for user 'ropa_dev'"
El usuario de MySQL no fue creado correctamente. Repite el Paso 1 completo.

### Error al conectar a la base de datos al arrancar el backend
Asegúrate de que el servicio MySQL esté corriendo. Ve a **Servicios de Windows** (busca "Servicios" en el menú Inicio) y verifica que **MySQL80** esté en estado "En ejecución".

### "npm is not recognized..."
Node.js no está instalado o no está en el PATH. Reinstala Node.js y reinicia la terminal.

### El frontend carga pero no muestra datos
Verifica que el backend esté corriendo en `http://localhost:8080`. Si el backend no está activo, el frontend no puede obtener datos.

---

## Documentación de la API (Swagger)

Con el backend corriendo, puedes explorar todos los endpoints en:

**http://localhost:8080/swagger-ui/index.html**
