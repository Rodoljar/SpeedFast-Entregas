==================================================
  SPEEDFAST - SISTEMA DE GESTIÓN DE ENTREGAS
==================================================

REQUISITOS Y CONFIGURACIÓN DEL ENTORNO:

1. BASE DE DATOS (MySQL):
   - Asegúrese de tener un servidor MySQL activo en localhost:3306.
   - Ejecute el script 'schema.sql' adjunto en la raíz del proyecto dentro de su cliente MySQL (Workbench, DBeaver, etc.) para crear la base de datos 'speedfast_s6' y la estructura de la tabla 'pedido'.

2. CREDENCIALES Y SEGURIDAD:
   - Por buenas prácticas de seguridad, las credenciales no están hardcodeadas en el código fuente.
   - La aplicación obtiene la contraseña de MySQL desde la variable de entorno 'DB_PASSWORD'.
   - Si no define la variable de entorno, el sistema intentará conectar por defecto con contraseña vacía ("") para garantizar la portabilidad.

3. EJECUCIÓN:
   - Abra el proyecto en IntelliJ IDEA o su IDE preferido.
   - Ejecute la clase principal: main.Main