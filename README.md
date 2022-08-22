# Manual de usuario

Para poder ejecutar la aplicación, se necesita tener instalado:

- Maven
- PostgreSQL

Además, habría que tener creada una base de datos en PostgreSQL que se llame postgres (la información de las credenciales se pueden consultar en el
archivo src/main/resources/application.properties)

Luego, habría que hacer los siguientes pasos:

- Acceder en un terminal a la carpeta "bookings": cd bookings
- Ejecutar la sentencia: mvn spring-boot:run
- Acceder a la URL http://localhost:8080/swagger-ui/index.html#/ para poder probar los endpoints
