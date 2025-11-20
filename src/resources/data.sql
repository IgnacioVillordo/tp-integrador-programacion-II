use gestionempleados;

INSERT INTO legajos (id, nroLegajo, categoria, estado, fechaAlta, observaciones) VALUES
(101, 'LEG-001', 'ADMINISTRATIVO', 'ACTIVO', '2022-01-15', 'Sin observaciones.'),
(102, 'LEG-002', 'TECNICO SENIOR', 'ACTIVO', '2021-05-20', 'Excelente desempeño en proyecto X.'),
(103, 'LEG-003', 'ANALISTA JR', 'ACTIVO', '2023-08-01', 'Período de prueba finalizado satisfactoriamente.'),
(104, 'LEG-004', 'GERENTE', 'ACTIVO', '2019-03-10', 'Antigüedad y liderazgo clave.'),
(105, 'LEG-005', 'OPERARIO', 'INACTIVO', '2020-11-25', 'Renuncia voluntaria a partir del 2024-06-30.'),
(106, 'LEG-006', 'VENTAS', 'ACTIVO', '2024-02-14', 'Alta reciente, pendiente de capacitación.'),
(107, 'LEG-007', 'MANTENIMIENTO', 'ACTIVO', '2018-07-03', 'Referente en reparaciones eléctricas.'),
(108, 'LEG-008', 'DISEÑADOR', 'ACTIVO', '2022-10-20', 'Bajo licencia por paternidad hasta 2025-01-30.'),
(109, 'LEG-009', 'CONTADOR', 'ACTIVO', '2021-04-05', 'Traslado de sede en 2023.'),
(110, 'LEG-010', 'RRHH JR', 'ACTIVO', '2024-01-01', NULL);

INSERT INTO empleados (id, dni, nombre, apellido, email, fechaIngreso, area, id_legajo, eliminado) VALUES
(101, '35123456', 'Ana', 'García', 'ana.garcia@empresa.com', '2022-01-15', 'Administración', 101, FALSE),
(102, '28987654', 'Javier', 'Rodríguez', 'javier.r@empresa.com', '2021-05-20', 'IT', 102, FALSE),
(103, '40555111', 'Laura', 'Martínez', 'laura.m@empresa.com', '2023-08-01', 'Operaciones', 103, FALSE),
(104, '20777333', 'Carlos', 'López', 'carlos.l@empresa.com', '2019-03-10', 'Dirección', 104, FALSE),
(105, '33444999', 'Sofía', 'Fernández', 'sofia.f@empresa.com', '2020-11-25', 'Producción', 105, TRUE),
(106, '42888000', 'Mateo', 'Díaz', 'mateo.diaz@empresa.com', '2024-02-14', 'Comercial', 106, FALSE),
(107, '25666222', 'Elena', 'Pérez', 'elena.p@empresa.com', '2018-07-03', 'Servicios Generales', 107, FALSE),
(108, '38222555', 'Diego', 'Sánchez', 'diego.s@empresa.com', '2022-10-20', 'Marketing', 108, FALSE),
(109, '30111888', 'Paula', 'Gómez', 'paula.g@empresa.com', '2021-04-05', 'Finanzas', 109, FALSE),
(110, '41000777', 'Andrés', 'Ruiz', 'andres.r@empresa.com', '2024-01-01', 'Recursos Humanos', 110, FALSE);