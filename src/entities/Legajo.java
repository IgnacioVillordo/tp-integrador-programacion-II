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

    public Legajo(Long id, boolean eliminado, String nroLegajo, String categoria, Estado estado, LocalDate fechaAlta, String observaciones) {
        this.setId(id);
        this.setEliminado(eliminado);
        this.setNroLegajo(nroLegajo);
        this.setCategoria(categoria);
        this.setEstado(estado);
        this.setFechaAlta(fechaAlta);
        this.setObservaciones(observaciones);
    }

    public void setId(Long id) {
        if (id > 0) {
            this.id = id;
            return;
        }
        System.out.println("ID invalido!");
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public void setNroLegajo(String nroLegajo) {
        if (nroLegajo != null && !nroLegajo.isEmpty() && nroLegajo.length() <= 20) {
            this.nroLegajo = nroLegajo;
            return;
        }
        System.out.println("Nro legajo invalido!");
    }

    public void setCategoria(String categoria) {
        if (categoria != null && !categoria.isEmpty() && categoria.length() <= 30) {
            this.categoria = categoria;
            return;
        }
        System.out.println("Categoria invalida!");
    }

    public void setEstado(Estado estado) {
        if (estado != null) {
            this.estado = estado;
        }
        System.out.println("Estado invalido!");
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        if (!fechaAlta.isBefore(LocalDate.now())) {
            this.fechaAlta = fechaAlta;
            return;
        }
        System.out.println("Fecha alta invalida!");
    }

    public void setObservaciones(String observaciones) {
        if (observaciones.length() <= 255) {
            this.observaciones = observaciones;
            return;
        }
        System.out.println("Observaciones invalida!");
    }

    @Override
    public String toString() {
        return "Legajo{" + "\n" +
                "  id=" + id + ",\n" +
                "  eliminado=" + eliminado + ",\n" +
                "  nroLegajo='" + nroLegajo + '\'' + ",\n" +
                "  categoria='" + categoria + '\'' + ",\n" +
                "  estado=" + estado + ",\n" +
                "  fechaAlta=" + fechaAlta + ",\n" +
                "  observaciones='" + observaciones + '\'' + ",\n" +
                '}';
    }

    public Long getId() {
        return id;
    }

    public boolean isEliminado() {
        return eliminado;
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
