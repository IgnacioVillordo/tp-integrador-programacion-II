package dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T> {
    void save(T entity) throws SQLException;
    void update(T entity) throws SQLException;
    void delete(String dni) throws SQLException;
    void saveTx(T entity, java.sql.Connection conn) throws SQLException;
    void updateTx(T entity, java.sql.Connection conn) throws SQLException;
    void delteTx(String dni, java.sql.Connection conn) throws SQLException;
    T getById(String dni) throws SQLException;
    List<T> getAll() throws SQLException;
}
