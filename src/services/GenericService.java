package services;

import java.util.List;

public interface GenericService<T> {
    void save(T entity) throws Exception;
    void update(T entity) throws Exception;
    void delete(int id) throws Exception;
    void saveTx(T entity) throws Exception;
    void updateTx(T entity) throws Exception;
    void delteTx(T entity) throws Exception;
    T getById(int id) throws Exception;
    List<T> getAll() throws Exception;
}
