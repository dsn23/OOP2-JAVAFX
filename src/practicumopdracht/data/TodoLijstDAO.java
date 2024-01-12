package practicumopdracht.data;

import practicumopdracht.models.TodoLijst;
import practicumopdracht.models.TodoRegel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class TodoLijstDAO implements DAO<TodoLijst> {

    protected List<TodoLijst> todoLijsten;

    public TodoLijstDAO() {
        todoLijsten = new ArrayList<>();
    }

    public List<TodoLijst> getAllFor(TodoRegel todoRegel){
        List<TodoLijst> tijdelijke = new ArrayList<>();
        for(TodoLijst todoLijst : todoLijsten){
            if(todoLijst.getMasterId() == todoRegel.getId()){
                tijdelijke.add(todoLijst);
            }
        }
        return Collections.unmodifiableList(tijdelijke);
    }

    @Override
    public List<TodoLijst> getAll() {
        return Collections.unmodifiableList(todoLijsten);
    }

    @Override
    public TodoLijst get(int id) {
        TodoLijst todoLijstId = new TodoLijst();
        for (TodoLijst todoLijst : todoLijsten) {
            if (todoLijst.getTodolijstId() == id) {
                todoLijstId = todoLijst;
            }
        }
//        return null;
        return todoLijstId;
    }


    public void addOrUpdate(TodoLijst lijst) {
        int idOfLijst = lijst.getMasterId();
        if (lijst.getTodolijstId() > 0) {
            System.out.println("HIER");
            todoLijsten.set(todoLijsten.indexOf(get(lijst.getTodolijstId())), lijst);
        } else {
            System.out.println("NEE HIER");
            lijst.setMasterId(lijst.getMasterId());
            lijst.setTodolijstId(getUniqueId());
            todoLijsten.add(lijst);
        }
        System.out.println(todoLijsten);
    }

    public void remove(TodoLijst lijst) {
        todoLijsten.remove(todoLijsten.indexOf(lijst));
    }

    private int getUniqueId() {
        int max = 0;

        for (TodoLijst todoLijst : todoLijsten) {
            if (todoLijst.getTodolijstId() > max) {
                max = todoLijst.getTodolijstId();
            }
        }
        return ++max;
    }
}