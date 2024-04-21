# Aplicativo usuarioAPI
Esta aplicación fue hecha bajo alguna de las siguientes tecnologías arquitectura hexagonal, java 11 pruebas unitarias con Junit5 y mockito, a demás de todas las tecnologías solicitadas
la documentación en Swagger, seguridad bearer token con JWT y se utilizó una base de datos H2 la cual inicia sus servicios al momento de levantar el servidor
y ejecuta los scripts que se encuentran en la carpeta resources, se ejecuta el script “schema.sql” el cual se encarga de generar
las tablas y también se ejecuta el script “data.sql”. Adjunto al correo enviado se encontrará la colección de peticiones Postman para realizar las pruebas.

En este ejercicio se crea un primer usuario con nombre “sergio”,  username “admin” y password “admin”. Este usuario es el unico que contiene el Rol administrador ROLE_ADMIN
y es el encargado de generar la primera carga. Para poder realizar la primera carga se debe levantar el aplicativo que corre por el puerto 8080.
Una vez el servidor despliegue el aplicativo se debe ingresar al auth de autenticación que se encuentra en:
http://localhost:8080/api/auth/login es un metodo POST y recibe el siguiente JSON en el body
{
"usernameOrEmail": "admin",
"password": "admin"
}
El servicio devuelve nuestro token como el siguiente JSON
{
"tokenDeAcceso": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzZXJnaW9AdGVzdC5jb20iLCJpYXQiOjE3MTM3MTkxMDAsImV4cCI6MTcxMzcyMDMwMH0.d-nvT9BMfAQ1AmBYETrER0Ht316bRMVBL1VJLvBwC4OgYvL6fZaTcdIMsqlfT2HzUBt00kyrHbgSeKNDPSNYkw",
"tipoDeToken": "Bearer"
}

Una vez logueado se pueden realizar las peticiones creadas para la Api user utilizando la autenticación Bearer token. A continuación, se muestran los siguientes endpoints
POST http://localhost:8080/api/auth/login
POST http://localhost:8080/api/user
GET http://localhost:8080/api/findAll
PUT http://localhost:8080/api/user/{idUser}
PUT http://localhost:8080/api/disableUsuer/{id}

La documentación de los servicios se encuentra en http://localhost:8080/v2/api-docs

La configuración de configuración de la contraseña es una variable configurable que se encuentra en el archivo de propiedades “application.properties” está actualmente se encuentra con la siguiente configuración:
password.regex=^(?=.*[a-zA-Z0-9])(?=.*[$@$!%*?&]).{6,}$
donde la expresión regular de la contraseña debe tener 6 caracteres alfa numéricos y un carácter especial.

También se encuentran los parámetros de configuración de la base de datos H2:
spring.datasource.url=jdbc:h2:mem:usuariodb
spring.datasource.driverClassName= org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=admin
spring.h2.console.enabled=true
spring.jpa.defer-datasource-initialization=true
