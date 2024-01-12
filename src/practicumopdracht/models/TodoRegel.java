package practicumopdracht.models;

public class TodoRegel {
    private TodoLijst todoLijst;
    private int id;
    private String naam;
    private int aantalTodoLijsten;

    public TodoRegel(String naam){
        this.naam = naam;
    }

    public TodoRegel() {

    }

    public TodoLijst getTodoLijst() {
        return todoLijst;
    }

    public void setTodoLijst(TodoLijst todoLijst) {
        this.todoLijst = todoLijst;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getAantalTodoLijsten() {
        return aantalTodoLijsten;
    }

    public void setAantalTodoLijsten(int aantalTodoLijsten) {
        this.aantalTodoLijsten = aantalTodoLijsten;
    }

    @Override
    public String toString() {

        return "(TodoLijst) \n" +
                "\n TODO - '" + naam + '\'';
    }
}
