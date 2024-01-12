package practicumopdracht.data;

import practicumopdracht.models.TodoLijst;
import practicumopdracht.models.TodoRegel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class TodoRegelDAO implements DAO<TodoRegel> {

    protected List<TodoRegel> todoRegels;

    public TodoRegelDAO() {
        todoRegels = new ArrayList<>();
    }

    @Override
    public List<TodoRegel> getAll() {
        return Collections.unmodifiableList(todoRegels);
    }

    @Override
    public TodoRegel get(int id) {
        TodoRegel todoRegelId = new TodoRegel();
        for (TodoRegel todoRegel : todoRegels) {
            if (todoRegel.getId() == id) {
                todoRegelId = todoRegel;
            }
        }
//        return null;
        return todoRegelId;
    }

    public void addOrUpdate(TodoRegel regel) {
        int idOfRegel = regel.getId();
        if (idOfRegel > 0) {
            todoRegels.set(todoRegels.indexOf(get(regel.getId())), regel);
        } else {
            regel.setId(getUniqueId());
            todoRegels.add(regel);
        }
    }

    public void remove(TodoRegel todoRegel) {
        todoRegels.remove(todoRegel);
    }


    private int getUniqueId() {
        int hoogsteId = 0;

        for (TodoRegel regel : todoRegels) {
            if (regel.getId() > hoogsteId) {
                hoogsteId = regel.getId();
            }
        }
        return ++hoogsteId;
    }


}