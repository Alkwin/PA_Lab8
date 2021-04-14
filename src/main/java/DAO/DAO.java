package DAO;

import java.util.List;

interface DAO<T> {
    List<T> findById(int id);

    List<T> findByName(String name);

    List<T> getAll();

    void insert(T t);

    void delete(T t);
}
