package entities;

import java.time.LocalDate;

public class Legajo {
    private Long id;
    private boolean eliminado;
    private String nroLegajo; // NOT NULL, UNIQUE, máx. 20
    private String categoria; // máx. 30
    private Estado estado; // NOT NULL
    private LocalDate fechaAlta;
    private String observaciones; // máx. 255
}
