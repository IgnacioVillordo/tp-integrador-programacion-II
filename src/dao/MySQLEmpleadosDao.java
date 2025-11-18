package dao;

import config.DatabaseConnection;
import entities.Empleado;
import entities.Legajo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLEmpleadosDao implements GenericDao<Empleado>, EmpleadoLegajoDao {

    @Override
    public int save(Empleado entity) throws SQLException {
        int generatedId = -1;
        String sql = "INSERT INTO empleados (dni, nombre, apellido, email, fechaIngreso, area) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, entity.getDni());
            stmt.setString(2, entity.getNombre());
            stmt.setString(3, entity.getApellido());
            stmt.setString(4, entity.getEmail());
            stmt.setString(5, entity.getFechaIngreso().toString());
            stmt.setString(6, entity.getArea());

            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    generatedId = rs.getInt(1);
                }
            }
        }
        return generatedId;
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
    public void delete(int id) throws SQLException {
        String sql = "UPDATE empleados SET eliminado=TRUE WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
        }
    }

    @Override
    public int saveTx(Empleado entity, Connection conn) throws SQLException {
        int generatedId = -1;
        String sql = "INSERT INTO empleados (dni, nombre, apellido, email, fechaIngreso, area) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, entity.getDni());
            stmt.setString(2, entity.getNombre());
            stmt.setString(3, entity.getApellido());
            stmt.setString(4, entity.getEmail());
            stmt.setString(5, entity.getFechaIngreso().toString());
            stmt.setString(6, entity.getArea());

            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    generatedId = rs.getInt(1);
                }
            }
        }
        return generatedId;
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
    public void delteTx(int id, Connection conn) throws SQLException {
        String sql = "UPDATE empleados SET eliminado=TRUE WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
        }
    }

    @Override
    public Empleado getById(int id) throws SQLException {
        String sql = "SELECT * FROM empleados WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, id);
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
    public void setLegajo(int id_empleado, Legajo legajo) throws SQLException {
        String sql = "UPDATE empleados SET id_legajo=? WHERE id_empleado=?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, legajo.getNroLegajo());
            stmt.setInt(2, id_empleado);

            stmt.executeQuery();
        }
    }

    @Override
    public void setLegajoTx(int id_empleado, Legajo legajo, Connection conn) throws SQLException {
        String sql = "UPDATE empleados SET id_legajo=? WHERE id_empleado=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, legajo.getNroLegajo());
            stmt.setInt(2, id_empleado);

            stmt.executeQuery();
        }
    }
}
