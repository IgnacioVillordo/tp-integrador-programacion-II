package dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T> {
    void save(T entity) throws SQLException;
    void update(T entity) throws SQLException;
    void delete(String id) throws SQLException;
    void saveTx(T entity, java.sql.Connection conn) throws SQLException;
    void updateTx(T entity, java.sql.Connection conn) throws SQLException;
    void delteTx(String id, java.sql.Connection conn) throws SQLException;
    T getById(String id) throws SQLException;
    List<T> getAll() throws SQLException;
}
