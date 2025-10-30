package dao;

import java.util.List;

public interface GenericDao<T> {
    void save(T entity) throws Exception;
    void update(T entity) throws Exception;
    void delete(T entity) throws Exception;
    void saveTx(T entity, java.sql.Connection conn) throws Exception;
    void updateTx(T entity, java.sql.Connection conn) throws Exception;
    void delteTx(T entity, java.sql.Connection conn) throws Exception;
    T getById(T entity) throws Exception;
    List<T> getAll() throws Exception;
}
