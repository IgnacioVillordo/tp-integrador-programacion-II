package services;

import dao.MySQLEmpleadoDao;
import entities.Empleado;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

class EmpleadoServiceFacke {

    private MySQLEmpleadoDao empleadoDao = new MySQLEmpleadoDao();

    // ========================= CREAR =========================
    public void crearEmpleadoDesdeConsola(Scanner scanner) {
        try {
            System.out.print("DNI: ");
            String dni = scanner.nextLine();
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Apellido: ");
            String apellido = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Fecha de ingreso (YYYY-MM-DD): ");
            LocalDate fechaIngreso = LocalDate.parse(scanner.nextLine());
            System.out.print("Área: ");
            String area = scanner.nextLine();

            Empleado empleado = new Empleado(dni, nombre, apellido, email, fechaIngreso, area, false);
            empleadoDao.save(empleado);

            System.out.println("✅ Empleado creado correctamente.");
        } catch (Exception e) {
            System.out.println("❌ Error al crear empleado: " + e.getMessage());
        }
    }

    // ========================= LISTAR =========================
    public void listarEmpleados() {
        try {
            List<Empleado> empleados = empleadoDao.getAll();
            if (empleados.isEmpty()) {
                System.out.println("⚠️ No hay empleados registrados.");
            } else {
                System.out.println("\n📋 Lista de empleados:");
                empleados.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("❌ Error al listar empleados: " + e.getMessage());
        }
    }

    // ========================= BUSCAR POR ID (DNI) =========================
    public void buscarEmpleadoPorId(Scanner scanner) {
        try {
            System.out.print("Ingrese DNI del empleado: ");
            String dni = scanner.nextLine();
            Empleado empleado = empleadoDao.getById(dni);

            if (empleado != null) {
                System.out.println("✅ Empleado encontrado:");
                System.out.println(empleado);
            } else {
                System.out.println("⚠️ No se encontró un empleado con ese DNI.");
            }
        } catch (Exception e) {
            System.out.println("❌ Error al buscar empleado: " + e.getMessage());
        }
    }

    // ========================= ACTUALIZAR =========================
    public void actualizarEmpleado(Scanner scanner) {
        try {
            System.out.print("Ingrese DNI del empleado a actualizar: ");
            String dni = scanner.nextLine();

            Empleado empleado = empleadoDao.getById(dni);
            if (empleado == null) {
                System.out.println("⚠️ Empleado no encontrado.");
                return;
            }

            System.out.print("Nuevo nombre: ");
            empleado.setNombre(scanner.nextLine());
            System.out.print("Nuevo apellido: ");
            empleado.setApellido(scanner.nextLine());
            System.out.print("Nuevo email: ");
            empleado.setEmail(scanner.nextLine());
            System.out.print("Nueva área: ");
            empleado.setArea(scanner.nextLine());

            empleadoDao.update(empleado);
            System.out.println("✅ Empleado actualizado correctamente.");
        } catch (Exception e) {
            System.out.println("❌ Error al actualizar empleado: " + e.getMessage());
        }
    }

    // ========================= ELIMINAR =========================
    public void eliminarEmpleado(Scanner scanner) {
        try {
            System.out.print("Ingrese DNI del empleado a eliminar: ");
            String dni = scanner.nextLine();
            empleadoDao.delete(dni);
            System.out.println("✅ Empleado eliminado correctamente.");
        } catch (Exception e) {
            System.out.println("❌ Error al eliminar empleado: " + e.getMessage());
        }
    }

    // ========================= BUSCAR POR DNI =========================
    public void buscarEmpleadoPorDni(Scanner scanner) {
        try {
            System.out.print("Ingrese DNI del empleado: ");
            String dni = scanner.nextLine();
            Empleado empleado = empleadoDao.getById(dni);

            if (empleado != null) {
                System.out.println("✅ Empleado encontrado:");
                System.out.println(empleado);
            } else {
                System.out.println("⚠️ No se encontró un empleado con ese DNI.");
            }
        } catch (Exception e) {
            System.out.println("❌ Error al buscar empleado: " + e.getMessage());
        }
    }
}