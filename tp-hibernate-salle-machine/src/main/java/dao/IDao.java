package dao;

import java.util.List;

/**
 * Interface générique définissant les opérations CRUD de base.
 * @param <T> Type de l'entité gérée
 */
public interface IDao<T> {
    boolean create(T o);
    boolean delete(T o);
    boolean update(T o);
    T getById(int id);
    List<T> getAll();
}
