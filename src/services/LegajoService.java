package services;

import dao.MySQLLegajosDao;
import entities.Estado;
import entities.Legajo;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class LegajoService {

    private MySQLLegajosDao legajoDao = new MySQLLegajosDao();

    // ========================= CREAR =========================
    public void crearLegajoDesdeConsola(Scanner scanner) {
        try {
            System.out.print("Número de legajo: ");
            String nroLegajo = scanner.nextLine().trim();

            System.out.print("Categoría: ");
            String categoria = scanner.nextLine().trim();

            // Validar estado (usa el enum Estado)
            Estado estado = null;
            while (estado == null) {
                System.out.print("Estado (ACTIVO/INACTIVO): ");
                String estadoStr = scanner.nextLine().trim().toUpperCase();
                try {
                    estado = Estado.valueOf(estadoStr);
                } catch (IllegalArgumentException e) {
                    System.out.println("⚠️ Valor inválido. Ingrese ACTIVO o INACTIVO.");
                }
            }

            // Validar fecha
            LocalDate fechaAlta = null;
            DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE;
            while (fechaAlta == null) {
                System.out.print("Fecha de alta (YYYY-MM-DD): ");
                String fechaStr = scanner.nextLine().trim();
                try {
                    fechaAlta = LocalDate.parse(fechaStr, fmt);
                } catch (DateTimeParseException e) {
                    System.out.println("⚠️ Formato inválido. Ingrese en formato YYYY-MM-DD (ej: 2025-11-13).");
                }
            }

            System.out.print("Observaciones: ");
            String observaciones = scanner.nextLine().trim();

            // Crear el legajo usando tu constructor
            Legajo legajo = new Legajo(nroLegajo, categoria, estado, fechaAlta, observaciones);

            legajoDao.save(legajo);
            System.out.println("✅ Legajo creado correctamente.");

        } catch (Exception e) {
            System.out.println("❌ Error al crear legajo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // ========================= LISTAR =========================
    public void listarLegajos() {
        try {
            List<Legajo> legajos = legajoDao.getAll();
            if (legajos.isEmpty()) {
                System.out.println("⚠️ No hay legajos registrados.");
            } else {
                System.out.println("\n📋 Lista de legajos:");
                legajos.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("❌ Error al listar legajos: " + e.getMessage());
        }
    }

    // ========================= BUSCAR POR ID =========================
    public void buscarLegajoPorId(Scanner scanner) {
        try {
            System.out.print("Ingrese número de legajo: ");
            String nro = scanner.nextLine().trim();
            Legajo legajo = legajoDao.getById(nro);

            if (legajo != null) {
                System.out.println("✅ Legajo encontrado:");
                System.out.println(legajo);
            } else {
                System.out.println("⚠️ No se encontró un legajo con ese número.");
            }
        } catch (Exception e) {
            System.out.println("❌ Error al buscar legajo: " + e.getMessage());
        }
    }

    // ========================= ACTUALIZAR =========================
    public void actualizarLegajo(Scanner scanner) {
        try {
            System.out.print("Ingrese número de legajo a actualizar: ");
            String nro = scanner.nextLine().trim();

            Legajo legajo = legajoDao.getById(nro);
            if (legajo == null) {
                System.out.println("⚠️ Legajo no encontrado.");
                return;
            }

            System.out.print("Nueva categoría: ");
            legajo.setCategoria(scanner.nextLine().trim());

            Estado nuevoEstado = null;
            while (nuevoEstado == null) {
                System.out.print("Nuevo estado (ACTIVO/INACTIVO): ");
                String estadoStr = scanner.nextLine().trim().toUpperCase();
                try {
                    nuevoEstado = Estado.valueOf(estadoStr);
                    legajo.setEstado(nuevoEstado);
                } catch (IllegalArgumentException e) {
                    System.out.println("⚠️ Valor inválido. Ingrese ACTIVO o INACTIVO.");
                }
            }

            System.out.print("Nuevas observaciones: ");
            legajo.setObservaciones(scanner.nextLine().trim());

            legajoDao.update(legajo);
            System.out.println("✅ Legajo actualizado correctamente.");
        } catch (Exception e) {
            System.out.println("❌ Error al actualizar legajo: " + e.getMessage());
        }
    }

    // ========================= ELIMINAR =========================
    public void eliminarLegajo(Scanner scanner) {
        try {
            System.out.print("Ingrese número de legajo a eliminar: ");
            String nro = scanner.nextLine().trim();
            legajoDao.delete(nro);
            System.out.println("✅ Legajo eliminado correctamente.");
        } catch (Exception e) {
            System.out.println("❌ Error al eliminar legajo: " + e.getMessage());
        }
    }
}