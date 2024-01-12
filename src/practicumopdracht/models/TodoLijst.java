package practicumopdracht.models;

import javafx.collections.ObservableList;

import java.time.LocalDate;

public class TodoLijst {
    private TodoLijst hoortBijTodolijst;
    private String naam;
    private String tekstArea;
    private int todolijstId;
    private int masterId;
    private boolean gehaald;
    private double cijfer;
    private LocalDate datum;

    public TodoLijst(String naam, double cijfer, boolean gehaald, LocalDate datum, int masterId) {
        this.naam=naam;
        this.cijfer = cijfer;
        this.datum=datum;
        this.gehaald=gehaald;
        this.masterId = masterId;
    }

    public TodoLijst getHoortBijTodolijst(){
        return hoortBijTodolijst;
    }

    public boolean isGehaald(){
        return gehaald;
    }

    public double getCijfer(){
        return cijfer;
    }

    public LocalDate getDatum(){
        return datum;
    }

    public String getNaam()
    {
        return naam;
    }

    public int getTodolijstId() {
        return todolijstId;
    }

    public void setTodolijstId(int todolijstId){
        this.todolijstId = todolijstId;
    }

    public int getMasterId() { return masterId; }

    public void setMasterId(int masterId) { this.masterId = masterId; }

    public TodoLijst(){

    }

    @Override
    public String toString() {
        return "(Todoregel)" +
                ", naam ='" + naam + '\'' +
                "hoortBijTodolijst=" + hoortBijTodolijst +
                ", masterId=" + masterId +
                '}';
    }
}



