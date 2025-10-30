package dao;

import entities.Legajo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MySQLLegajosDao implements GenericDao<Legajo> {
    @Override
    public void save(Legajo entity) throws SQLException {

    }

    @Override
    public void update(Legajo entity) throws SQLException {

    }

    @Override
    public void delete(String dni) throws SQLException {

    }

    @Override
    public void saveTx(Legajo entity, Connection conn) throws SQLException {

    }

    @Override
    public void updateTx(Legajo entity, Connection conn) throws SQLException {

    }

    @Override
    public void delteTx(String dni, Connection conn) throws SQLException {

    }

    @Override
    public Legajo getById(String dni) throws SQLException {
        return null;
    }

    @Override
    public List<Legajo> getAll() throws SQLException {
        return List.of();
    }
}
