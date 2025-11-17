DROP DATABASE IF EXISTS gestionempleados;

CREATE DATABASE gestionempleados;

USE gestionempleados;

DROP TABLE IF EXISTS legajos;

-- Creación tabla legajos
CREATE TABLE legajos
(
    id            INT NOT NULL UNIQUE PRIMARY KEY,
    nroLegajo     VARCHAR(20),
    categoria     VARCHAR(30),
    estado        ENUM ('ACTIVO','INACTIVO') NOT NULL,
    fechaAlta     DATE DEFAULT (NOW()),
    observaciones VARCHAR(255)
);

DROP TABLE IF EXISTS empleados;

-- Creación tabla empleados
CREATE TABLE empleados
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    dni          VARCHAR(15)        NOT NULL,
    nombre       VARCHAR(80)        NOT NULL,
    apellido     VARCHAR(80)        NOT NULL,
    email        VARCHAR(120),
    fechaIngreso DATE    DEFAULT (NOW()),
    area         VARCHAR(50),
    id_legajo    INT,
    eliminado    BOOLEAN DEFAULT FALSE,

    FOREIGN KEY (id_legajo) REFERENCES legajos (id)
);