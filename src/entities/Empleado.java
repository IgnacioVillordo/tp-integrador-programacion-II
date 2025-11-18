package entities;

import java.time.LocalDate;

public class Empleado {
    private int id;

    private String dni; // NOT NULL, UNIQUE, máx. 15
    private String nombre; // NOT NULL, máx. 80
    private String apellido; // NOT NULL, máx. 80
    private String email; // máx. 120, formato email
    private LocalDate fechaIngreso;
    private String area; // máx. 50
    private boolean eliminado = false;
    private Legajo legajo;

    public Empleado(
            String dni,
            String nombre,
            String apellido,
            String email,
            LocalDate fechaIngreso,
            String area,
            boolean eliminado
    ) {
        this.setDni(dni);
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setEmail(email);
        this.setFechaIngreso(fechaIngreso);
        this.setArea(area);
        this.setEliminado(eliminado);
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.isEmpty() && nombre.length() <= 80) {
            this.nombre = nombre;
            return;
        }
        throw new IllegalArgumentException("Nombre invalido");
    }

    public void setApellido(String apellido) {
        if (apellido != null && !apellido.isEmpty() && apellido.length() <= 80) {
            this.apellido = apellido;
            return;
        }
        throw new IllegalArgumentException("Apellido invalido");
    }

    public void setDni(String dni) {
        if (dni != null && !dni.isEmpty() && dni.length() <= 16) {
            this.dni = dni;
            return;
        }
        throw new IllegalArgumentException("DNI invalido");
    }

    public void setEmail(String email) {
        if (email.length() <= 120 && email.contains("@") && email.contains(".")) {
            this.email = email;
            return;
        }
        throw new IllegalArgumentException("Email invalido");
    }

   public void setFechaIngreso(LocalDate fechaIngreso) {
    if (fechaIngreso != null && !fechaIngreso.isAfter(LocalDate.now())) {
        this.fechaIngreso = fechaIngreso;
        return;
    }
    throw new IllegalArgumentException("Fecha invalida: no puede ser futura.");
}

    public void setArea(String area) {
        if (area.length() <= 50) {
            this.area = area;
            return;
        }
        throw new IllegalArgumentException("Area invalida");
    }

    public void setLegajo(Legajo legajo) {
        if (legajo != null) {
            this.legajo = legajo;
            return;
        }
        throw new IllegalArgumentException("El legajo es requerido");
    }

    @Override
    public String toString() {
        return "Empleado{" + "\n" +
                "  id='" + id + '\'' + ",\n" +
                "  dni='" + dni + '\'' + ",\n" +
                "  nombre='" + nombre + '\'' + ",\n" +
                "  apellido='" + apellido + '\'' + ",\n" +
                "  email='" + email + '\'' + ",\n" +
                "  fechaIngreso=" + fechaIngreso + ",\n" +
                "  area='" + area + '\'' + ",\n" +
                "  legajo=" + legajo + ",\n" +
                "  eliminado=" + eliminado + ",\n" +
                '}';
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public String getArea() {
        return area;
    }

    public Legajo getLegajo() {
        return legajo;
    }

    public int getId() {
        return id;
    }
}
