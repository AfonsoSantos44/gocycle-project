package isel.sisinf.jpa.dal.repo;

public interface IRespository<T,TK>{
    T findById(TK id);
    T save(T entity);
    void deleteById(TK id);
    void delete(T entity);
    void deleteAll();
    long count();
    boolean existsById(TK id);
}
