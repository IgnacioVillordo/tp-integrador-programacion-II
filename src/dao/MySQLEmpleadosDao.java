package dao;

import config.DatabaseConnection;
import entities.Empleado;
import entities.Legajo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLEmpleadosDao implements GenericDao<Empleado>, EmpleadoLegajoDao {

    @Override
    public void save(Empleado entity) throws SQLException {
        String sql = "INSERT INTO empleados (dni, nombre, apellido, email, fechaIngreso, area) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, entity.getDni());
            stmt.setString(2, entity.getNombre());
            stmt.setString(3, entity.getApellido());
            stmt.setString(4, entity.getEmail());
            stmt.setString(5, entity.getFechaIngreso().toString());
            stmt.setString(6, entity.getArea());

            stmt.executeUpdate();
        }
    }

    @Override
    public void update(Empleado entity) throws SQLException {
        String sql = "UPDATE empleados SET dni=?, nombre=?, apellido=?, email=?, fechaIngreso=?, area=? WHERE dni=?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, entity.getDni());
            stmt.setString(2, entity.getNombre());
            stmt.setString(3, entity.getApellido());
            stmt.setString(4, entity.getEmail());
            stmt.setString(5, entity.getFechaIngreso().toString());
            stmt.setString(6, entity.getArea());
            stmt.setString(7, entity.getDni());

            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(String dni) throws SQLException {
        String sql = "DELETE FROM empleados WHERE dni=?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dni);
        }
    }

    @Override
    public void saveTx(Empleado entity, Connection conn) throws SQLException {
        String sql = "INSERT INTO empleados (dni, nombre, apellido, email, fechaIngreso, area) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, entity.getDni());
            stmt.setString(2, entity.getNombre());
            stmt.setString(3, entity.getApellido());
            stmt.setString(4, entity.getEmail());
            stmt.setString(5, entity.getFechaIngreso().toString());
            stmt.setString(6, entity.getArea());

            stmt.executeUpdate();
        }
    }

    @Override
    public void updateTx(Empleado entity, Connection conn) throws SQLException {
        String sql = "UPDATE empleados SET dni=?, nombre=?, apellido=?, email=?, fechaIngreso=?, area=? WHERE dni=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, entity.getDni());
            stmt.setString(2, entity.getNombre());
            stmt.setString(3, entity.getApellido());
            stmt.setString(4, entity.getEmail());
            stmt.setString(5, entity.getFechaIngreso().toString());
            stmt.setString(6, entity.getArea());
            stmt.setString(7, entity.getDni());

            stmt.executeUpdate();
        }
    }

    @Override
    public void delteTx(String dni, Connection conn) throws SQLException {
        String sql = "DELETE FROM empleados WHERE dni=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dni);
        }
    }

    @Override
    public Empleado getById(String dni) throws SQLException {
        String sql = "SELECT * FROM empleados WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, dni);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Empleado(
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("email"),
                        rs.getDate("fechaIngreso").toLocalDate(),
                        rs.getString("area"),
                        rs.getBoolean("eliminado")
                );
            }
        }
        return null;
    }

    @Override
    public List<Empleado> getAll() throws SQLException {
        String sql = "SELECT * FROM empleados";
        List<Empleado> empleados = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                empleados.add(
                    new Empleado(
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("email"),
                        rs.getDate("fechaIngreso").toLocalDate(),
                        rs.getString("area"),
                        rs.getBoolean("eliminado")
                    )
                );
            }
            return empleados;
        }
    }

    @Override
    public void setLegajo(String dni, Legajo legajo) throws SQLException {
        String sql = "UPDATE empleados SET legajo=? WHERE dni=?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, legajo.getNroLegajo());
            stmt.setString(2, dni);

            stmt.executeQuery();
        }
    }

    @Override
    public void setLegajoTx(String dni, Legajo legajo, Connection conn) throws SQLException {
        String sql = "UPDATE empleados SET legajo=? WHERE dni=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, legajo.getNroLegajo());
            stmt.setString(2, dni);

            stmt.executeQuery();
        }
    }
}
