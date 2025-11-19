package adapters.cli;

import entities.Estado;
import entities.Legajo;
import services.LegajoService;
import utils.logger.Logger;
import utils.reader.InputReader;

import java.time.LocalDate;

public class LegajoCliInterfaceImpl implements LegajoCliInterface {
    private final LegajoService legajoService;
    private final InputReader inputReader;
    private final Logger logger;

    public LegajoCliInterfaceImpl(LegajoService legajoService, InputReader inputReader, Logger logger) {
        this.legajoService = legajoService;
        this.inputReader = inputReader;
        this.logger = logger;
    }

    @Override
    public void buscar() {
        try {
            this.logger.print("Ingrese número de legajo: ");
            int nro = Integer.parseInt(this.inputReader.read().trim());
            Legajo legajo = this.legajoService.getById(nro);

            if (legajo != null) {
                this.logger.println("✅ Legajo encontrado:");
                this.logger.println(legajo);
            } else {
                this.logger.println("⚠️ No se encontró el legajo con ID: " + nro);
            }
        } catch (Exception e) {
            this.logger.println("❌ Error al buscar legajo: " + e.getMessage());
        }
    }

    @Override
    public void buscarPorId() {
        try {
            this.logger.print("Ingrese el id del legajo: ");
            int id = Integer.parseInt(this.inputReader.read().trim());
            Legajo legajo = this.legajoService.getById(id);

            if (legajo != null) {
                this.logger.println("✅ Legajo encontrado:");
                this.logger.println(legajo);
            } else {
                this.logger.println("⚠️ No se encontró un legajo con ID: " + id);
            }
        } catch (Exception e) {
            this.logger.println("❌ Error al buscar legajo: " + e.getMessage());
        }
    }

    @Override
    public void actualizar() {
        try {
            this.logger.print("Ingrese el ID del empleado a actualizar: ");
            int id = Integer.parseInt(this.inputReader.read());

            Legajo legajo = this.legajoService.getById(id);
            if (legajo == null) {
                this.logger.println("⚠️ Legajo no encontrado.");
                return;
            }

            legajo.setNroLegajo(this.inputReader.updateValue(legajo.getNroLegajo()));
            legajo.setCategoria(this.inputReader.updateValue(legajo.getCategoria()));
            legajo.setObservaciones(this.inputReader.updateValue(legajo.getCategoria()));
            Estado nuevoEstado = null;
            while (nuevoEstado == null) {
                try {
                    nuevoEstado = Estado.valueOf(this.inputReader.updateValue(legajo.getEstado().name(), "Nuevo estado (ACTIVO/INACTIVO): ").toUpperCase());
                    legajo.setEstado(nuevoEstado);
                } catch (IllegalArgumentException e) {
                    this.logger.println("⚠️ Valor inválido. Ingrese ACTIVO o INACTIVO.");
                }
            }
            legajo.setFechaAlta(LocalDate.parse(this.inputReader.updateValue(legajo.getFechaAlta().toString())));

            this.legajoService.update(legajo);
            this.logger.println("✅ Legajo actualizado correctamente.");
        } catch (Exception e) {
            this.logger.println("❌ Error al actualizar legajo: " + e.getMessage());
        }
    }

    @Override
    public void eliminar() {
        try {
            this.logger.print("Ingrese el ID del legajo a eliminar: ");
            int id = Integer.parseInt(this.inputReader.read().trim());
            this.legajoService.delete(id);
            this.logger.println("✅ Legajo eliminado correctamente.");
        } catch (Exception e) {
            this.logger.println("❌ Error al eliminar legajo: " + e.getMessage());
        }
    }
}
