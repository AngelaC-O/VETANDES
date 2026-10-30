# VetAndes API

Angela Maria Cotrina Natividad

## Ejecución

JDK 21 y Oracle con un esquema vacío VETANDES. En este equipo se preparó VETANDES en localhost:1522/freepdb1. Valores predeterminados: usuario VETANDES y contraseña 123456. Se pueden reemplazar con DB_URL, DB_USERNAME y DB_PASSWORD. La migración Flyway crea el esquema del caso y sus datos semilla; el inicio cifra con BCrypt las contraseñas vacías de los usuarios de prueba.

Desde esta carpeta:

```powershell
.\mvnw.cmd spring-boot:run
```

API: http://localhost:8080/api/v1. Swagger: http://localhost:8080/swagger-ui.html. JWT dura 3600 segundos. JWT_SECRET admite una clave Base64 de al menos 32 bytes.

## Usuarios

| Usuario | Contraseña | Rol | Estado |
|---|---|---|---|
| admin | Vet2026* | ADMIN | Activo |
| recepcion | Vet2026* | RECEPCIONISTA | Activo |
| veterinario | Vet2026* | VETERINARIO | Activo |
| inactivo | Vet2026* | RECEPCIONISTA | Inactivo |

## Contrato

Todas las rutas usan /api/v1 y Bearer JWT salvo POST /auth/login.

| Método | Ruta | Permiso |
|---|---|---|
| POST | /auth/login | Público |
| GET | /auth/me | Autenticado |
| GET | /propietarios | MASCOTA_LEER |
| GET | /prestaciones?especie | PRESTACION_LEER |
| GET | /prestaciones/{id} | PRESTACION_LEER |
| POST, PUT, DELETE | /prestaciones, /prestaciones/{id} | PRESTACION_ESCRIBIR |
| PATCH | /prestaciones/{id}/estado | PRESTACION_ESCRIBIR |
| GET | /mascotas?propietarioId, /mascotas/{id} | MASCOTA_LEER |
| POST, PUT, DELETE | /mascotas, /mascotas/{id} | MASCOTA_ESCRIBIR |
| POST | /atenciones | ATENCION_REGISTRAR |
| GET | /atenciones?mascota&especie&estado&desde&hasta, /atenciones/{id} | ATENCION_LEER |
| PATCH | /atenciones/{id}/anular | ATENCION_ANULAR |
| GET | /reportes/atenciones-por-especie?desde&hasta | REPORTE_VER |

POST de creación devuelve 201; consultas y cambios 200; DELETE 204. Los errores 400, 401, 403, 404, 409 y 500 incluyen timestamp, status, error, mensaje y ruta. La validación agrega campos. Fechas de filtros: YYYY-MM-DD.

Prestación: codigo, nombre, precio, especieAplicable, activo. Mascota: nombre, especie, raza, fechaNacimiento, sexo, propietarioId. Atención: mascotaId, peso, diagnostico, detalles [{prestacionId,cantidad}]. Anulación: {motivo}. Estado de prestación: {activo}.

## Validación

```powershell
.\mvnw.cmd test
```

La prueba de contexto requiere Oracle disponible. La colección postman/VetAndes.postman_collection.json incluye pruebas de códigos HTTP, CRUD, permisos, descuento y anulación. Ejecutar en orden desde el Collection Runner. Los datos de prueba usan un nombre único por ejecución. Las reglas de negocio se aplican en servicios transaccionales; el reporte usa GROUP BY en JPQL. La numeración utiliza una secuencia Oracle, que puede dejar saltos tras un rechazo.

## Entrega

Ramas main, develop y examen-b-cotrina. Etiqueta v1.0-examen-u2. Los repositorios son locales; falta configurar los remotos personales para publicarlos. El PDF de evidencias y las capturas están en ../entregables.
