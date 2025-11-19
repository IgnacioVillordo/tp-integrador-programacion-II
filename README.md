## Universidad Tecnológica Nacional

### Tecnicatura Universitaria en Programación a Distancia

### Trabajo Práctico Integrador: Programación 2 y Bases de Datos 1

**Alumnos**:

• Francisco Gutierrez

• Andrés Rodríguez, DNI: 33101592

• Ignacio Nicolás Rodríguez Villordo, DNI: 42973917

• Joel Dario Muñoz, DNI: 33975916

### Explicación del Proyecto

Este proyecto es una aplicación de consola (CLI) desarrollada en Java para la gestión de empleados y sus respectivos legajos. La aplicación sigue una arquitectura por capas para separar responsabilidades, interactuando con una base de datos MySQL para la persistencia de datos.

Las principales funcionalidades incluyen:
- Alta, baja y modificación de empleados.
- Gestión de legajos asociados a los empleados.
- Menú interactivo en la consola para una fácil operación.

### Estructura del Proyecto

A continuación se muestra un diagrama con la estructura de carpetas y archivos más relevantes del proyecto:

```
/
├── .gitignore
├── README.md
├── src
│   ├── adapters
│   │   └── cli/
│   │       ├── AppMenuCliInterface.java
│   │       ├── EmpleadoCliInterface.java
│   │       └── LegajoCliInterface.java
│   ├── config/
│   │   ├── DatabaseConnection.java
│   │   └── TransaccionManager.java
│   ├── dao/
│   │   ├── MySQLEmpleadoDao.java
│   │   └── MySQLLegajoDao.java
│   ├── entities/
│   │   ├── Empleado.java
│   │   └── Legajo.java
│   ├── main/
│   │   └── Main.java
│   ├── resources/
│   │   ├── data.sql
│   │   └── schema.sql
│   ├── services/
│   │   ├── EmpleadoService.java
│   │   └── LegajoService.java
│   └── utils/
│       ├── logger/
│       └── reader/
└── ...
```

En caso de utilizar una base de datos desde docker, ejecutar el siguiente comando

```shell
docker run --rm --name tp-integrador-programacion -e MYSQL_ROOT_PASSWORD=secreto -p 3306:3306 -d mysql:8.0 --default-authentication-plugin=mysql_native_password
```

Ésto levantará una base de datos MySQL con las siguientes caracteristicas

Usuario: root

Contraseña: secreto

Puerto: 3306

Al detener el contenedor, éste se eliminará

Detener contenedor de la base de datos

```shell
docker stop tp-integrador-programacion
```

Ejecutar phpmyadmin desde docker.

Nota: El contenedor de phpmyadmin necesitara un contenedor de base de datos llamado tp-integrador-programacion para ejecutarse.

```shell
docker run --rm --name phpmyadmin-tp-integrador -d --link tp-integrador-programacion:db -p 8080:80 phpmyadmin
```

Visitar [http://localhost:8080](http://localhost:8080) e ingresar con las credenciales de la base de datos.

Detener contenedor de phpmyadmin

```shell
docker stop phpmyadmin-tp-integrador
```