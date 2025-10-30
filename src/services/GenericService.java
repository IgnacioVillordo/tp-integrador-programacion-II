package services;

import java.util.List;

public interface GenericService<T> {
    void save(T entity) throws Exception;
    void update(T entity) throws Exception;
    void delete(T entity) throws Exception;
    T getById(T entity) throws Exception;
    List<T> getAll() throws Exception;
}
