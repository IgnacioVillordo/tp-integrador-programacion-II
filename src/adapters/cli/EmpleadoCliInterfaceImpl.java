package adapters.cli;

import entities.Empleado;
import services.EmpleadoService;
import utils.logger.Logger;
import utils.reader.InputReader;

import java.time.LocalDate;
import java.util.List;

public class EmpleadoCliInterfaceImpl implements EmpleadoCliInterface {
    private final EmpleadoService empleadoService;
    private final InputReader inputReader;
    private final Logger logger;

    public EmpleadoCliInterfaceImpl(EmpleadoService empleadoService, InputReader inputReader, Logger logger) {
        this.empleadoService = empleadoService;
        this.inputReader = inputReader;
        this.logger = logger;
    }

    @Override
    public void crear() {
        try {
            this.logger.print("DNI: ");
            String dni = this.inputReader.read();
            this.logger.print("Nombre: ");
            String nombre = this.inputReader.read();
            this.logger.print("Apellido: ");
            String apellido = this.inputReader.read();
            this.logger.print("Email: ");
            String email = this.inputReader.read();
            this.logger.print("Fecha de ingreso (YYYY-MM-DD): ");
            LocalDate fechaIngreso = LocalDate.parse(this.inputReader.read());
            this.logger.print("Área: ");
            String area = this.inputReader.read();

            Empleado empleado = new Empleado(dni, nombre, apellido, email, fechaIngreso, area, false);
            this.empleadoService.save(empleado);

            this.logger.println("✅ Empleado creado correctamente.");
        } catch (Exception e) {
            this.logger.println("❌ Error al crear empleado: " + e.getMessage());
        }
    }

    @Override
    public void setLegajo() {

    }

    @Override
    public void obtenerLegajo() {

    }

    @Override
    public void listar() {
        try {
            List<Empleado> empleados = this.empleadoService.getAll();
            if (empleados.isEmpty()) {
                this.logger.println("⚠️ No hay empleados registrados.");
            } else {
                this.logger.println("\n📋 Lista de empleados:");
                empleados.forEach(this.logger::println);
            }
        } catch (Exception e) {
            this.logger.println("❌ Error al listar empleados: " + e.getMessage());
        }
    }

    @Override
    public void buscar() {
        try {
            this.logger.print("Ingrese DNI del empleado: ");
            String dni = this.inputReader.read();
            Empleado empleado = empleadoService.buscarPorDni(dni);

            if (empleado != null) {
                this.logger.println("✅ Empleado encontrado:");
                this.logger.println(empleado);
            } else {
                this.logger.println("⚠️ No se encontró el empleado con DNI: " + dni);
            }
        } catch (Exception e) {
            this.logger.println("❌ Error al buscar empleado: " + e.getMessage());
        }
    }

    @Override
    public void buscarPorId() {
        try {
            System.out.print("Ingrese ID del empleado: ");
            int id = Integer.parseInt(this.inputReader.read());
            Empleado empleado = empleadoService.getById(id);

            if (empleado != null) {
                System.out.println("✅ Empleado encontrado:");
                System.out.println(empleado);
            } else {
                System.out.println("⚠️ No se encontró el empleado con ID: " + id);
            }
        } catch (Exception e) {
            System.out.println("❌ Error al buscar empleado: " + e.getMessage());
        }
    }

    @Override
    public void actualizar() {
        try {
            this.logger.print("Ingrese el ID del empleado a actualizar: ");
            int id = Integer.parseInt(this.inputReader.read());

            Empleado empleado = empleadoService.getById(id);
            if (empleado == null) {
                this.logger.println("⚠️ Empleado no encontrado.");
                return;
            }

            empleado.setDni(this.inputReader.updateValue(empleado.getDni()));
            empleado.setNombre(this.inputReader.updateValue(empleado.getNombre()));
            empleado.setApellido(this.inputReader.updateValue(empleado.getApellido()));
            empleado.setEmail(this.inputReader.updateValue(empleado.getEmail()));
            empleado.setFechaIngreso(LocalDate.parse(this.inputReader.updateValue(empleado.getFechaIngreso().toString())));
            empleado.setArea(this.inputReader.updateValue(empleado.getArea()));

            empleadoService.update(empleado);
            this.logger.println("✅ Empleado actualizado correctamente.");
        } catch (Exception e) {
            this.logger.println("❌ Error al actualizar empleado: " + e.getMessage());
        }
    }

    @Override
    public void eliminar() {
        try {
            this.logger.print("Ingrese el ID del empleado a eliminar: ");
            int id = Integer.parseInt(this.inputReader.read());
            empleadoService.delete(id);
            this.logger.println("✅ Empleado eliminado correctamente.");
        } catch (Exception e) {
            this.logger.println("❌ Error al eliminar empleado: " + e.getMessage());
        }
    }
}
