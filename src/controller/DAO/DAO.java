package controller.DAO;

import java.util.ArrayList;

/**
 * @author robot
 */
public interface DAO<T> {
    void insert(T t);

    void update(T t);

    void delete(String t);

    ArrayList<T> selectAll();

    T selectById(String t);
}
