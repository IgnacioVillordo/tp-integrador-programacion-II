package entities;

import java.time.LocalDate;

public class Empleado {
    private Long id;
    private boolean eliminado;
    private String nombre; // NOT NULL, máx. 80
    private String apellido; // NOT NULL, máx. 80
    private String dni; // NOT NULL, UNIQUE, máx. 15
    private String email; // máx. 120, formato email
    private LocalDate fechaIngreso;
    private String area; // máx. 50
    private Legajo legajo;
}
