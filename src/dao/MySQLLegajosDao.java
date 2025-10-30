package dao;

import config.DatabaseConnection;
import entities.Estado;
import entities.Legajo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLLegajosDao implements GenericDao<Legajo> {
    @Override
    public void save(Legajo entity) throws SQLException {
        String sql = "INSERT INTO legajos (nroLegajo, categoria, estado, fechaAlta, observaciones) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, entity.getNroLegajo());
            stmt.setString(2, entity.getCategoria());
            stmt.setString(3, entity.getEstado().name());
            stmt.setString(4, entity.getFechaAlta().toString());
            stmt.setString(5, entity.getObservaciones());

            stmt.executeUpdate();
        }
    }

    @Override
    public void update(Legajo entity) throws SQLException {
        String sql = "UPDATE legajos SET categoria=?, estado=?, fechaAlta=?, observaciones=? WHERE nroLegajo=?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, entity.getCategoria());
            stmt.setString(2, entity.getEstado().name());
            stmt.setString(3, entity.getFechaAlta().toString());
            stmt.setString(4, entity.getObservaciones());
            stmt.setString(5, entity.getNroLegajo());

            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(String nroLegajo) throws SQLException {
        String sql = "DELETE FROM legajos  WHERE nroLegajo=?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nroLegajo);
        }
    }

    @Override
    public void saveTx(Legajo entity, Connection conn) throws SQLException {
        String sql = "INSERT INTO legajos (nroLegajo, categoria, estado, fechaAlta, observaciones) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, entity.getNroLegajo());
            stmt.setString(2, entity.getCategoria());
            stmt.setString(3, entity.getEstado().name());
            stmt.setString(4, entity.getFechaAlta().toString());
            stmt.setString(5, entity.getObservaciones());

            stmt.executeUpdate();
        }
    }

    @Override
    public void updateTx(Legajo entity, Connection conn) throws SQLException {
        String sql = "UPDATE legajos SET categoria=?, estado=?, fechaAlta=?, observaciones=? WHERE nroLegajo=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, entity.getCategoria());
            stmt.setString(2, entity.getEstado().name());
            stmt.setString(3, entity.getFechaAlta().toString());
            stmt.setString(4, entity.getObservaciones());
            stmt.setString(5, entity.getNroLegajo());

            stmt.executeUpdate();
        }
    }

    @Override
    public void delteTx(String nroLegajo, Connection conn) throws SQLException {
        String sql = "DELETE FROM legajos  WHERE nroLegajo=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nroLegajo);
        }
    }

    @Override
    public Legajo getById(String nroLegajo) throws SQLException {
        String sql = "SELECT * FROM legajos  WHERE nroLegajo=?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nroLegajo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Legajo(
                        rs.getString("nroLegajo"),
                        rs.getString("categoria"),
                        Estado.valueOf(rs.getString("estado")),
                        rs.getDate("fechaAlta").toLocalDate(),
                        rs.getString("observaciones")
                );
            }
        }
        return null;
    }

    @Override
    public List<Legajo> getAll() throws SQLException {
        String sql = "SELECT * FROM legajos";
        List<Legajo> legajos = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                legajos.add(new Legajo(
                        rs.getString("nroLegajo"),
                        rs.getString("categoria"),
                        Estado.valueOf(rs.getString("estado")),
                        rs.getDate("fechaAlta").toLocalDate(),
                        rs.getString("observaciones")
                ));
            }
            return legajos;
        }
    }
}
