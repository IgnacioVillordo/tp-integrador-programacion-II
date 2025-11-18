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
    public int save(Legajo entity) throws SQLException {
        int generatedId = -1;
        String sql = "INSERT INTO legajos (nroLegajo, categoria, estado, fechaAlta, observaciones) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, entity.getNroLegajo());
            stmt.setString(2, entity.getCategoria());
            stmt.setString(3, entity.getEstado().name());
            stmt.setString(4, entity.getFechaAlta().toString());
            stmt.setString(5, entity.getObservaciones());

            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    generatedId =  rs.getInt(1);
                }
            }
        }
        return generatedId;
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
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM legajos WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
        }
    }

    @Override
    public int saveTx(Legajo entity, Connection conn) throws SQLException {
        int generatedId = -1;
        String sql = "INSERT INTO legajos (nroLegajo, categoria, estado, fechaAlta, observaciones) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, entity.getNroLegajo());
            stmt.setString(2, entity.getCategoria());
            stmt.setString(3, entity.getEstado().name());
            stmt.setString(4, entity.getFechaAlta().toString());
            stmt.setString(5, entity.getObservaciones());

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
    public void delteTx(int id, Connection conn) throws SQLException {
        String sql = "DELETE FROM legajos WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
        }
    }

    @Override
    public Legajo getById(int id) throws SQLException {
        String sql = "SELECT * FROM legajos WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
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
