## Universidad Tecnológica Nacional

### Tecnicatura Universitaria en Programación a Distancia

### Trabajo Práctico Integrador: Programación 2 y Bases de Datos 1

**Alumnos**:

• Francisco Gutierrez

• Andrés Rodríguez, DNI: 33101592

• Ignacio Nicolás Rodríguez Villordo, DNI: 42973917

• Joel Dario Muñoz, DNI: 33975916

En caso de utilizar una base de datos desde docker, ejecutar el siguiente comando

```shell
docker run --rm --name tp-integrador-programacion -e MYSQL_ROOT_PASSWORD=secreto -p 3306:3306 -d mysql:8.0 --default-authentication-plugin=mysql_native_password
```

Ésto levantará una base de datos MySQL con las siguientes caracteristicas

Usuario: root

Contraseña: secreto

Puerto: 3306

Al detener el contenedor, éste se eliminará

Detener contenedor

```shell
docker stop tp-integrador
```

Ejecutar phpmyadmin desde docker.

Nota: El contenedor de phpmyadmin necesitara un contenedor de base de datos llamado tp-integrador-programacion para ejecutarse.

```shell
docker run --rm --name phpmyadmin -d --link tp-integrador-programacion:db -p 8080:80 phpmyadmin
```

Visitar [http://localhost:8080](http://localhost:8080) e ingresar con las credenciales de la base de datos.
