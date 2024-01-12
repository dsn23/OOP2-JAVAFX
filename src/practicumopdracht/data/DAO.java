package practicumopdracht.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public interface DAO<T> {

    public List<T> getAll();

    public T get(int id);

    public void addOrUpdate(T model);

    public void remove(T model);

    public boolean save();

    public boolean load();
}
