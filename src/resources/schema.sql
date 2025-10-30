DROP DATABASE IF EXISTS gestionempleados;

CREATE DATABASE gestionempleados;

USE gestionempleados;

DROP TABLE IF EXISTS legajos;

-- Creación tabla legajos
CREATE TABLE legajos
(
    nroLegajo     VARCHAR(20)                NOT NULL UNIQUE PRIMARY KEY,
    categoria     VARCHAR(30),
    estado        ENUM ('ACTIVO','INACTIVO') NOT NULL,
    fechaAlta     DATE DEFAULT (NOW()),
    observaciones VARCHAR(255)
);

DROP TABLE IF EXISTS empleados;

-- Creación tabla empleados
CREATE TABLE empleados
(
    dni          VARCHAR(15) UNIQUE NOT NULL PRIMARY KEY,
    nombre       VARCHAR(80)        NOT NULL,
    apellido     VARCHAR(80)        NOT NULL,
    email        VARCHAR(120),
    fechaIngreso DATE    DEFAULT (NOW()),
    area         VARCHAR(50),
    nroLegajo    VARCHAR(20),
    eliminado    BOOLEAN DEFAULT FALSE,

    FOREIGN KEY (nroLegajo) REFERENCES legajos (nroLegajo)
);