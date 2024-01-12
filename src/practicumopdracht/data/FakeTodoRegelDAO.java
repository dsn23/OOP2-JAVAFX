package practicumopdracht.data;

import practicumopdracht.models.TodoRegel;

import java.util.List;

public class FakeTodoRegelDAO extends TodoRegelDAO {
    public boolean save() {
        return true;
    }

    public boolean load() {
        TodoRegel een = new TodoRegel("Vandaag, ergens, hopelijk...");
        een.setId(1);
        TodoRegel twee = new TodoRegel("Morgen doe ik het zeker!");
        twee.setId(2);

        todoRegels.add(een);
        todoRegels.add(twee);
        return true;
    }

}
