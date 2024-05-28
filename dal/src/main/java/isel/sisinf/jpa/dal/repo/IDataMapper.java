package isel.sisinf.jpa.dal.repo;

public interface IDataMapper<T> {
    T read(int id);
    T update(T entity);
    void delete(T entity);
}
