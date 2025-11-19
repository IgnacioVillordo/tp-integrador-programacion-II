package entities;

import java.time.LocalDate;

public class Legajo {
    private int id;
    private String nroLegajo; // NOT NULL, UNIQUE, máx. 20
    private String categoria; // máx. 30
    private Estado estado; // NOT NULL
    private LocalDate fechaAlta;
    private String observaciones; // máx. 255

    public Legajo(
            int id,
            String nroLegajo,
            String categoria,
            Estado estado,
            LocalDate fechaAlta,
            String observaciones
    ) {
        this.setId(id);
        this.setNroLegajo(nroLegajo);
        this.setCategoria(categoria);
        this.setEstado(estado);
        this.setFechaAlta(fechaAlta);
        this.setObservaciones(observaciones);
    }

    public Legajo(
            String nroLegajo,
            String categoria,
            Estado estado,
            LocalDate fechaAlta,
            String observaciones
    ) {
        this.setNroLegajo(nroLegajo);
        this.setCategoria(categoria);
        this.setEstado(estado);
        this.setFechaAlta(fechaAlta);
        this.setObservaciones(observaciones);
    }

    public void setId(int id) {
        if (id <= 0)
            throw new IllegalArgumentException("Id invalido");
        if (this.id > 0)
            throw new IllegalArgumentException("El id no se puede modificar");
        this.id = id;
    }

    public void setNroLegajo(String nroLegajo) {
        if (nroLegajo != null && !nroLegajo.isEmpty() && nroLegajo.length() <= 20) {
            this.nroLegajo = nroLegajo;
            return;
        }
        throw new IllegalArgumentException("Numero de legajo invalido. No puede estar vacio ni tener mas de 20 caracteres");
    }

    public void setCategoria(String categoria) {
        if (categoria != null && !categoria.isEmpty() && categoria.length() <= 30) {
            this.categoria = categoria;
            return;
        }
        throw new IllegalArgumentException("La categoria es invalida. No puede estar vacia ni tener mas de 30 caracteres");
    }

    public void setEstado(Estado estado) {
        if (estado != null) {
            this.estado = estado;
            return;
        }
        throw new IllegalArgumentException("Estado invalido");
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        if (fechaAlta != null && !fechaAlta.isAfter(LocalDate.now())) {
            this.fechaAlta = fechaAlta;
            return;
        }
        throw new IllegalArgumentException("La fecha no puede ser futura ni nula");
    }

    public void setObservaciones(String observaciones) {
        if (observaciones.length() <= 255) {
            this.observaciones = observaciones;
            return;
        }
        throw new IllegalArgumentException("Las observaciones no pueden tener mas de 255 caracteres");
    }

    @Override
    public String toString() {
        return "Legajo{" + "\n" +
                "  nroLegajo='" + nroLegajo + '\'' + ",\n" +
                "  categoria='" + categoria + '\'' + ",\n" +
                "  estado=" + estado + ",\n" +
                "  fechaAlta=" + fechaAlta + ",\n" +
                "  observaciones='" + observaciones + '\'' + ",\n" +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getNroLegajo() {
        return nroLegajo;
    }

    public String getCategoria() {
        return categoria;
    }

    public Estado getEstado() {
        return estado;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public String getObservaciones() {
        return observaciones;
    }
}
