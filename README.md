# Instrucciones de instalación para Voluntariado - TBD:

# Backend:
*El proyecto de Spring se encuentra alojado en la carpeta "voluntariado-be-TBD".
Para levantar el servidor de la aplicación, se debe tener previamente instalado:
- PostgreSQL 14 (para la base de datos)
- Java jdk 17 (para la aplicación)
- IntelliJ idea (preferencial para levantar la app)
Con lo anterior instalado y con la configuración estándar, es necesario cargar el backup de la base de datos. Para ello existen múltiples formas de hacerlo, pero mostraremos la que utilizamos como grupo (funciona para windows).
El único requisito es tener agregado a las variables de entorno "path" la carpeta "bin" de PostgreSQL 14.
Posteriormente, descargar el archivo "voluntariadotbd.sql" y abrir una terminal en su ubicación. Luego ejecutar el siguiente comando:
- psql -U postgres -f voluntariadotbd.sql

Recordar que:
- -U: usuario de postgres donde "postgres" es el usuario por defecto.
- -f: archivo a restaurar.
Al ejecutar estos comandos se debe posteriormente introducir la contraseña del usuario ingresado y con eso la base de datos estará lista para ser usada.

Otro cambio necesario es la conexión de la aplicación de Spring con la base de datos montada previamente. Para ello, basta con modificar el archivo de laa siguiente ruta: "voluntariado-be-TBD/src/main/resources/application.properties", donde ingresaremos las credenciales correspondientes a la base de datos creada. Debería ser algo similar a:
- database.url=jdbc:postgresql://localhost:5432/voluntariadotbd
- database.user=postgres
- database.password=postgres
- server.port=8081

*Recordar que el puerto 8081 debe estar libre y el servidor de postgres debe coincidir (por defecto el 5432).

# Frontend:
Para levantar el servidor web se necesita tener previamente instalado:
- NodeJs 16.17.1 LTS (para asegurar el correcto funcionamiento).
- Gestor de paquetes 'npm'
Luego, se necesita acceder al proyecto y en la carpeta "voluntariado-fe-TBD" abrir una terminal para ejecutar los siguientes comandos:
- npm install nuxt (para la configuración de nuxt)
- npm run dev (para levantar el servidor forntend en el puerto 3000 por defecto)

*En caso de fallar en la configuración de nuxt en el primer comando, borrar la carpeta "node_modules" que se encuentra dentro de "voluntariado-fe-TBD".

Finalmente, basta con acceder a la dirección http://localhost:3000/login para comenzar a usar las funcionalidades de esta entrega.
