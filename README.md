# AREP-LAB3 CLIENTES Y SERVICIOS

# DESCRIPCION

## RETO 1
Escriba un servidor web que soporte múlltiples solicitudes seguidas (no concurrentes). El servidor debe retornar todos los archivos solicitados, incluyendo páginas html e imágenes. Construya un sitio web con javascript para probar su servidor. Despliegue su solución en Heroku. NO use frameworks web como Spark o Spring use solo Java y las librerías para manejo de la red.

## RETO 2 (AVANZADO)
Usando su  servidor y java (NO use frameworks web como Spark o Spring). Escriba un framework similar a Spark que le permita publicar servicios web "get" con funciones lambda y le permita acceder a recursoso estáticos como páginas, javascripts, imágenes, y CSSs. Cree una aplicación que conecte con una base de datos desde el servidor para probar su solución. Despliegue su solución en Heroku.

# REQUISITOS
- Java 
- Maven 

# DISEÑO
El despliegue de la aplicacion esta hecho en Heroku el cual usa un Dyno para soportar la app y por medio del protocolo HTTP el cliente o usuario final puede hacer uso de la aplicacion.

![](https://github.com/aosfandres/AREP-LAB3/blob/master/images/diagrama.PNG)

# USO
- clonar el repositorio ``` git clone https://github.com/aosfandres/AREP-LAB3```
- construir el proyecto ```mvn package```
- ejecutar el proyecto ```java -cp target/classes edu.escuelaing.arep.App``` se recomienda tener la ultima actualizacion de maven, de lo contrario ejecutar la clase 'App' desde su IDE preferido.
- ingresar a http://localhost:36000/ 

![](https://github.com/aosfandres/AREP-LAB3/blob/master/images/1.PNG)

- alli podra ver y acceder a los recursos disponibles

- imagen

![](https://github.com/aosfandres/AREP-LAB3/blob/master/images/2.PNG)

- imagen

![](https://github.com/aosfandres/AREP-LAB3/blob/master/images/3.PNG)

- imagen

![](https://github.com/aosfandres/AREP-LAB3/blob/master/images/4.PNG)

- Base de datos

![](https://github.com/aosfandres/AREP-LAB3/blob/master/images/5.PNG)


# DOCUMENTO LATEX
[Taller Clientes y servicios](https://github.com/aosfandres/AREP-LAB3/blob/master/LatexDocument.pdf)

# DOCUMENTACION (JAVADOC)
Para generar la documentacion con maven uasr mvn javdoc:javadoc en consola
[JAVADOC](https://github.com/aosfandres/AREP-LAB3/blob/master/JAVADOC.lnk)

# DESPLIEGUE

[![Deployed to Heroku](https://www.herokucdn.com/deploy/button.png)](https://still-basin-02265.herokuapp.com/)

# AUTOR
Andres Orlando Sotelo Fajardo 

# LICENCIA

[LICENSE](https://github.com/aosfandres/AREP-LAB3/blob/master/LICENSE)
