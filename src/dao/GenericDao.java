package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface GenericDao<T> {
    Optional<Integer> save(T entity) throws SQLException;
    void update(T entity) throws SQLException;
    void delete(int id) throws SQLException;
    Optional<Integer> saveTx(T entity, java.sql.Connection conn) throws SQLException;
    void updateTx(T entity, java.sql.Connection conn) throws SQLException;
    void delteTx(int id, java.sql.Connection conn) throws SQLException;
    T getById(int id) throws SQLException;
    List<T> getAll() throws SQLException;
}
