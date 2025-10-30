package main;

import config.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConnectionDb {
    public static void main(String[] args) {
        try (Connection conn = new DatabaseConnection().getConnection()) {
            if (conn != null) {
                System.out.println("✅ Conexión establecida con éxito.");
            } else {
                System.out.println("❌ No se pudo establecer la conexión.");
            }
        } catch (SQLException e) {
            System.err.println("⚠️ Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("⚠️ Error al conectar a la base de datos");
        }
    }
}
