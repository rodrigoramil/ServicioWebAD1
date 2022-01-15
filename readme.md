# AD-1. Servicios web

## Introducción

Para desarrollar este proyecto hemos utilizado el lenguaje ***Java***.

Hemos añadido una librería externa, que hemos obtenido el este [link](http://www.java2s.com/Code/Jar/h/Downloadhttpserver061sourcesjar.htm).

El archivo _.jar_ que descargamos debemos añadirlo a nuestro proyecto mediante la opción _Java Bild Path_ en las propiedades del proyecto.

---

## Uso del programa

Al ejecutar el programa se arrancará un servidor local, al que accederemos mediante el puerto _1234_.

Para probar su correcto funcionamiento, abriremos un navegador web e introduciremos en la barra de direcciones ___127.0.0.1___ o ___localhost___ seguido del puerto ___1234___.

- `127.0.0.1:1234`
- `localhost:1234`

---

### Almacenar

Para añadir una palabra al fichero _.txt_, que se crea automáticamente en la raíz del proyecto, si éste no existe, introduciremos en el navegador lo anteriormente citado, seguido de ___/almacena?___ e introduciendo la palabra que deseamos almacenar.
 
- `127.0.0.1:1234/almacena?palabra`
- `localhost:1234/almacena?palabra`

![ejemplo de almacenar una palabra](https://github.com/rodrigoramil/ServicioWebAD1/blob/master/img/almacena.PNG)

---

### Consultar

Para consultar si la palabra introducida por el usuario ya se encuentra almacenada en el fichero y las veces que se encuentra repetida, obviando las mayúsculas, minúsculas y tildes; debemos introducir ___/consulta?___ seguido de la palabra que deseamos consultar.

- `127.0.0.1:1234/consulta?palabra`
- `localhost:1234/consulta?palabra`

![ejemplo de consultar una palabra](https://github.com/rodrigoramil/ServicioWebAD1/blob/master/img/consulta.PNG)

---

### Sintaxis incorrecta

Si introducimos mal en la barra de direcciones alguno de los comandos indicados con anterioridad, obtendremos un mensaje en el navegador indicado como debemos realizar correctamente la sentencia de consulta o almacenaje.

![ejemplo de estructura incorrecta](https://github.com/rodrigoramil/ServicioWebAD1/blob/master/img/estructuraIncorrecta.PNG)
# servicioWeb_AD1
