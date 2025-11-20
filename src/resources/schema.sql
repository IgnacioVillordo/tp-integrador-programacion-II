DROP DATABASE IF EXISTS gestionempleados;

CREATE DATABASE gestionempleados;

USE gestionempleados;

DROP TABLE IF EXISTS legajos;

-- Creación tabla legajos
CREATE TABLE legajos
(
    id            INT NOT NULL PRIMARY KEY,
    nroLegajo     VARCHAR(20) NOT NULL,
    categoria     VARCHAR(30) NOT NULL,
    estado        ENUM ('ACTIVO','INACTIVO') NOT NULL,
    fechaAlta     DATE DEFAULT (NOW()),
    observaciones VARCHAR(255)
);

CREATE INDEX idx_legajos_nroLegajo
ON legajos (nroLegajo);

DROP TABLE IF EXISTS empleados;

-- Creación tabla empleados
CREATE TABLE empleados
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    dni          VARCHAR(15)  NOT NULL UNIQUE,
    nombre       VARCHAR(80)  NOT NULL,
    apellido     VARCHAR(80)  NOT NULL,
    email        VARCHAR(120) NOT NULL,
    fechaIngreso DATE    DEFAULT (NOW()),
    area         VARCHAR(50),
    id_legajo    INT,
    eliminado    BOOLEAN DEFAULT FALSE,

    FOREIGN KEY (id_legajo) REFERENCES legajos (id)
);

CREATE INDEX idx_empleados_dni
ON empleados (dni);

SHOW INDEX FROM empleados;
SHOW INDEX FROM legajos;