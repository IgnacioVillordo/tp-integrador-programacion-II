package adapters.cli;

import entities.Empleado;
import entities.Estado;
import entities.Legajo;
import services.EmpleadoService;
import services.LegajoService;
import utils.logger.Logger;
import utils.reader.InputReader;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
        try {
            this.logger.print("Ingrese el ID del empleado al cual le quiere setear su Legajo: ");
            int id = Integer.parseInt(this.inputReader.read());
            Empleado empleado = empleadoService.getById(id);

            if (empleado == null)
                throw new Exception("No se encontro el Empleado con el id " + empleado.getId());

            this.logger.print("Numero de Legajo: ");
            String nroLegajo = this.inputReader.read();

            this.logger.print("Categoria: ");
            String categoria = this.inputReader.read();

            Estado estado = null;
            while (estado == null) {
                this.logger.print("Estado (ACTIVO/INACTIVO): ");
                String estadoStr = this.inputReader.read().trim().toUpperCase();
                try {
                    estado = Estado.valueOf(estadoStr);
                } catch (IllegalArgumentException e) {
                    this.logger.println("⚠️ Valor inválido. Ingrese ACTIVO o INACTIVO.");
                }
            }

            LocalDate fechaAlta = null;
            DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE;
            while (fechaAlta == null) {
                this.logger.print("Fecha de alta (YYYY-MM-DD): ");
                String fechaStr = this.inputReader.read().trim();
                try {
                    fechaAlta = LocalDate.parse(fechaStr, fmt);
                } catch (DateTimeParseException e) {
                    this.logger.println("⚠️ Formato inválido. Ingrese en formato YYYY-MM-DD (ej: 2025-11-13).");
                }
            }

            this.logger.print("Observaciones: ");
            String observaciones = this.inputReader.read();

            Legajo legajo = new Legajo(nroLegajo, categoria, estado, fechaAlta, observaciones);

            this.empleadoService.setLegajo(empleado.getId(), legajo);
            this.logger.println("✅ Legajo seteado al Empleado con ID " + id);
        } catch (Exception e) {
            this.logger.println("❌ Error al setear Legajo al Empleado: " + e.getMessage());
        }
    }

    @Override
    public void obtenerLegajo() {
        try {
            this.logger.print("Ingrese el ID del empleado al cual le quiere obtener su Legajo: ");
            int id = Integer.parseInt(this.inputReader.read());
            Empleado empleado = empleadoService.getById(id);

            if (empleado == null)
                throw new Exception("No se encontro el Empleado con el id " + empleado.getId());

            Legajo legajo = this.empleadoService.getLegajo(id);
            this.logger.print(legajo);
        } catch (Exception e) {
            this.logger.println("❌ Error al obtener el Legajo: " + e.getMessage());
        }
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
            this.logger.print("Ingrese ID del empleado: ");
            int id = Integer.parseInt(this.inputReader.read());
            Empleado empleado = empleadoService.getById(id);

            if (empleado != null) {
                this.logger.println("✅ Empleado encontrado:");
                this.logger.println(empleado);
            } else {
                this.logger.println("⚠️ No se encontró el empleado con ID: " + id);
            }
        } catch (Exception e) {
            this.logger.println("❌ Error al buscar empleado: " + e.getMessage());
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

            empleado.setDni(this.inputReader.updateValue(empleado.getDni(), "DNI"));
            empleado.setNombre(this.inputReader.updateValue(empleado.getNombre(), "Nombre"));
            empleado.setApellido(this.inputReader.updateValue(empleado.getApellido(), "Apellido"));
            empleado.setEmail(this.inputReader.updateValue(empleado.getEmail(), "Email"));
            empleado.setFechaIngreso(LocalDate.parse(this.inputReader.updateValue(empleado.getFechaIngreso().toString(), "Fecha de Ingreso")));
            empleado.setArea(this.inputReader.updateValue(empleado.getArea(), "Area"));

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
            this.empleadoService.delete(id);
            this.logger.println("✅ Empleado eliminado correctamente.");
        } catch (Exception e) {
            this.logger.println("❌ Error al eliminar empleado: " + e.getMessage());
        }
    }
}
