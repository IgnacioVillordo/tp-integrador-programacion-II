package main;

import java.util.Scanner;
import services.EmpleadoService;
import services.LegajoServiceFacke;

public class AppMenu {

    private Scanner scanner = new Scanner(System.in);
    private EmpleadoService empleadoService = new EmpleadoService();
    private LegajoServiceFacke legajoService = new LegajoServiceFacke();

    public void iniciar() {
        int opcion;
        do {
            System.out.println("\n===============================");
            System.out.println("  SISTEMA DE GESTIÓN EMPLEADOS ");
            System.out.println("===============================");
            System.out.println("1. Gestionar Empleados");
            System.out.println("2. Gestionar Legajos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> menuEmpleados();
                case 2 -> menuLegajos();
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción inválida, intente nuevamente.");
            }
        } while (opcion != 0);
    }

    // ================= MENÚ EMPLEADOS =================
    private void menuEmpleados() {
        int opcion;
        do {
            System.out.println("\n--- Gestión de Empleados ---");
            System.out.println("1. Crear empleado");
            System.out.println("2. Listar empleados");
            System.out.println("3. Buscar empleado por ID");
            System.out.println("4. Actualizar empleado");
            System.out.println("5. Eliminar empleado (baja lógica)");
            System.out.println("6. Buscar empleado por DNI");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> empleadoService.crearEmpleadoDesdeConsola(scanner);
                case 2 -> empleadoService.listarEmpleados();
                case 3 -> empleadoService.buscarEmpleadoPorId(scanner);
                case 4 -> empleadoService.actualizarEmpleado(scanner);
                case 5 -> empleadoService.eliminarEmpleado(scanner);
                case 6 -> empleadoService.buscarEmpleadoPorDni(scanner);
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    // ================= MENÚ LEGAJOS =================
    private void menuLegajos() {
        int opcion;
        do {
            System.out.println("\n--- Gestión de Legajos ---");
            System.out.println("1. Crear legajo");
            System.out.println("2. Listar legajos");
            System.out.println("3. Buscar legajo por ID");
            System.out.println("4. Actualizar legajo");
            System.out.println("5. Eliminar legajo (baja lógica)");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> legajoService.crearLegajoDesdeConsola(scanner);
                case 2 -> legajoService.listarLegajos();
                case 3 -> legajoService.buscarLegajoPorId(scanner);
                case 4 -> legajoService.actualizarLegajo(scanner);
                case 5 -> legajoService.eliminarLegajo(scanner);
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    // ================= MÉTODOS AUXILIARES =================
    private int leerEntero() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Ingrese un número válido.");
            return -1;
        }
    }
}