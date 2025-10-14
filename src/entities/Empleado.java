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

    public Empleado(Long id, boolean eliminado, String nombre, String apellido, String dni, String email, LocalDate fechaIngreso, String area) {
        this.setId(id);
        this.setEliminado(eliminado);
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setDni(dni);
        this.setEmail(email);
        this.setFechaIngreso(fechaIngreso);
        this.setArea(area);
    }

    public void setId(Long id) {
        if (id > 0) {
            this.id = id;
            return;
        }
        System.out.println("ID invalido");
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.isEmpty() && nombre.length() <= 80) {
            this.nombre = nombre;
            return;
        }
        System.out.println("Nombre invalido");
    }

    public void setApellido(String apellido) {
        if (apellido != null && !apellido.isEmpty() && apellido.length() <= 80) {
            this.apellido = apellido;
            return;
        }
        System.out.println("Apellido invalido");
    }

    public void setDni(String dni) {
        if (dni != null && !dni.isEmpty() && dni.length() <= 16) {
            this.dni = dni;
            return;
        }
        System.out.println("DNI invalido");
    }

    public void setEmail(String email) {
        if (email.length() <= 120 && email.contains("@") && email.contains(".")) {
            this.email = email;
            return;
        }
        System.out.println("Email invalido");
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        if (!fechaIngreso.isBefore(LocalDate.now())) {
            this.fechaIngreso = fechaIngreso;
            return;
        }
        System.out.println("Fecha ingreso invalida!");
    }

    public void setArea(String area) {
        if (area.length() <= 50) {
            this.area = area;
            return;
        }
        System.out.println("Area invalida!");
    }

    public void setLegajo(Legajo legajo) {
        if (legajo != null) {
            this.legajo = legajo;
            return;
        }
        System.out.println("Legajo es requerido!");
    }

    @Override
    public String toString() {
        return "Empleado{" + "\n" +
                "  id=" + id + ",\n" +
                "  eliminado=" + eliminado + ",\n" +
                "  nombre='" + nombre + '\'' + ",\n" +
                "  apellido='" + apellido + '\'' + ",\n" +
                "  dni='" + dni + '\'' + ",\n" +
                "  email='" + email + '\'' + ",\n" +
                "  fechaIngreso=" + fechaIngreso + ",\n" +
                "  area='" + area + '\'' + ",\n" +
                "  legajo=" + legajo + ",\n" +
                '}';
    }

    public Long getId() {
        return id;
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
}
