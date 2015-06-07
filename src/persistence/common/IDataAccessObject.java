package persistence.common;

import java.util.Date;
import java.util.List;

public interface IDataAccessObject<T>
{
    List<T> readAll();
    T read(int id);
    void update(T data);
    List<T> search(String key, String value);
    List<T> search(String key, int value);
    List<T> search(String key, Date value);
    List<T> search(String key, double value);
    void remove(int id);
    void remove(T data);
    void insert(T data);
}