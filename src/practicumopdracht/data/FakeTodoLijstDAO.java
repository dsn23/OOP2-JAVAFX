package practicumopdracht.data;

import practicumopdracht.models.TodoLijst;

import java.time.LocalDate;
import java.util.ArrayList;

public class FakeTodoLijstDAO extends TodoLijstDAO {

    public boolean save() {
        return true;
    }

    @Override
    public boolean load() {
        TodoLijst eersteLijst = new TodoLijst("Test 1", 12,true, LocalDate.of(2020, 6, 17) ,1);
        eersteLijst.setTodolijstId(1);
        eersteLijst.setMasterId(1);

        TodoLijst tweedeLijst = new TodoLijst("Test 2", 10, true, LocalDate.of(2020, 4, 23),2);
        tweedeLijst.setTodolijstId(2);
        tweedeLijst.setMasterId(2);

        todoLijsten.add(eersteLijst);
        todoLijsten.add(tweedeLijst);

        return true;
    }

}

