package adapters.cli;

import utils.logger.Logger;
import utils.reader.InputReader;

public class AppMenuCliInterfaceImpl implements AppMenuCliInterface {
    private final EmpleadoCliInterface empleadoCliInterface;
    private final LegajoCliInterface legajoCliInterface;
    private final InputReader inputReader;
    private final Logger logger;
    private int option;

    public AppMenuCliInterfaceImpl(EmpleadoCliInterface empleadoCliInterface, LegajoCliInterface legajoCliInterface, InputReader inputReader, Logger logger) {
        this.empleadoCliInterface = empleadoCliInterface;
        this.legajoCliInterface = legajoCliInterface;
        this.inputReader = inputReader;
        this.logger = logger;
    }

    @Override
    public void main() {
        do {
            this.logger.println("\n===============================");
            this.logger.println("  SISTEMA DE GESTIÓN EMPLEADOS ");
            this.logger.println("===============================");
            this.logger.println("1. Gestionar Empleados");
            this.logger.println("2. Gestionar Legajos");
            this.logger.println("0. Salir");
            this.logger.print("Seleccione una opción: ");
            this.option = this.readOption();

            switch (this.option) {
                case 1 -> this.menuEmpleados();
                case 2 -> this.menuLegajos();
                case 0 -> this.logger.println("Saliendo del sistema...");
                default -> this.logger.println("Opción inválida, intente nuevamente.");
            }
        } while (this.option != 0);
    }

    private void menuEmpleados() {
        do {
            this.logger.println("\n--- Gestión de Empleados ---");
            this.logger.println("1. Crear empleado");
            this.logger.println("2. Actualizar empleado");
            this.logger.println("3. Eliminar empleado");
            this.logger.println("4. Buscar empleado por ID");
            this.logger.println("5. Buscar empleado por DNI");
            this.logger.println("6. Ver todos");
            this.logger.println("0. Volver al menú principal");
            this.logger.print("Seleccione una opción: ");
            this.option = readOption();

            switch (this.option) {
                case 1 -> this.empleadoCliInterface.crear();
                case 2 -> this.empleadoCliInterface.actualizar();
                case 3 -> this.empleadoCliInterface.eliminar();
                case 4 -> this.empleadoCliInterface.buscarPorId();
                case 5 -> this.empleadoCliInterface.buscar();
                case 6 -> this.empleadoCliInterface.listar();
                case 0 -> this.logger.println("Volviendo al menú principal...");
                default -> this.logger.println("Opción inválida.");
            }
        } while (this.option != 0);
    }

    private void menuLegajos() {
        do {
            this.logger.println("\n--- Gestión de Legajos ---");
            this.logger.println("1. Buscar por numero de legajo");
            this.logger.println("2. Buscar por ID");
            this.logger.println("3. Actualizar");
            this.logger.println("4. Eliminar");
            this.logger.println("0. Volver al menú principal");
            this.logger.print("Seleccione una opción: ");
            this.option = this.readOption();

            switch (this.option) {
                case 1 -> legajoCliInterface.buscar();
                case 2 -> legajoCliInterface.buscarPorId();
                case 3 -> legajoCliInterface.actualizar();
                case 4 -> legajoCliInterface.eliminar();
                case 0 -> this.logger.println("Volviendo al menú principal...");
                default -> this.logger.println("Opción inválida.");
            }
        } while (this.option != 0);
    }

    private int readOption () {
        return Integer.parseInt(this.inputReader.read());
    }
}
