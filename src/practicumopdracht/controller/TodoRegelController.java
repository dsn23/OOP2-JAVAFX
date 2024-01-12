package practicumopdracht.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import practicumopdracht.MainApplication;
import practicumopdracht.data.DAO;
import practicumopdracht.data.TodoLijstDAO;
import practicumopdracht.data.TodoRegelDAO;
import practicumopdracht.models.TodoLijst;
import practicumopdracht.models.TodoRegel;
import practicumopdracht.views.TodoRegelView;
import practicumopdracht.views.View;

import java.util.List;
import java.util.Optional;

public class TodoRegelController extends Controller{

    private TodoRegelView todoRegelview;
    private DAO<TodoRegel> todoRegelDAO = MainApplication.getTodoregelDAO();
    private TodoLijstDAO todoLijstDAO = MainApplication.getTodolijstDAO();


    public TodoRegelController(){
        todoRegelview = new TodoRegelView();
        todoRegelview.getOpslaan().setOnAction(actionEvent -> opslaanButton());
        todoRegelview.getBack().setOnAction(event -> switchTo());
        todoRegelview.getDelete().setOnAction(actionEvent -> verwijderButton());
        todoRegelview.getNew().setOnAction(actionEvent -> nieuwButton());


        todoRegelview.getLoadMenuItem().setOnAction(e -> handleLoad());
        todoRegelview.getSaveMenuItem().setOnAction(e -> handleSave());
        todoRegelview.getCloseMenuItem().setOnAction(e -> handleClose());



        todoRegelview.getListView().getSelectionModel().selectedItemProperty()
                .addListener(
                        (observableValue, oldValue, newValue) -> {
                            view((TodoRegel) newValue);
                        }
                );

        refreshTodoRegelListView();
    }

    private void handleClose() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Wil je nog dat de data wordt opgeslagen?");
        ButtonType okButton = new ButtonType("Ja", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("Nee", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(okButton, noButton);
        Optional<ButtonType> close = alert.showAndWait();

        if(close.get() == okButton){
            this.handleSave();
        }

        Platform.exit();
    }

    private void handleLoad() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Wil je dat de data wordt geladen?");
        ButtonType okButton = new ButtonType("Ja", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("Nee", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(okButton, noButton);
        Optional<ButtonType> load = alert.showAndWait();

        if(load.get() == okButton){
            todoRegelDAO.load();
            todoLijstDAO.load();
            Alert showSuccesMessage = new Alert(Alert.AlertType.CONFIRMATION);
            showSuccesMessage.setContentText("Het laden is gelukt");
            showSuccesMessage.show();
        }else{
            Alert showSuccesMessage = new Alert(Alert.AlertType.CONFIRMATION);
            showSuccesMessage.setContentText("Het laden is mislukt");
            showSuccesMessage.show();
        }
        refreshTodoRegelListView();
    }

    private void handleSave() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Wil je dat de data wordt opgeslagen?");
        ButtonType okButton = new ButtonType("Ja", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("Nee", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(okButton, noButton);
        Optional<ButtonType> save = alert.showAndWait();

        if(save.get() == okButton){
            todoRegelDAO.save();
            todoLijstDAO.save();
            Alert showSuccesMessage = new Alert(Alert.AlertType.CONFIRMATION);
            showSuccesMessage.setContentText("Data is volledig opgeslagen");
            showSuccesMessage.show();
        }else{
            Alert showSuccesMessage = new Alert(Alert.AlertType.CONFIRMATION);
            showSuccesMessage.setContentText("Data is niet opgeslagen");
            showSuccesMessage.show();
        }


    }

    private void view(TodoRegel newValue) {
        if(newValue != null){
            todoRegelview.getTodoregelText().setText(newValue.getNaam());
        }
    }

    private void switchTo(){

        TodoRegel selectRegel = todoRegelview.getListView().getSelectionModel().getSelectedItem();

        if(selectRegel != null){
            MainApplication.switchController(new TodoLijstController(selectRegel));
        }
    }


    private void verwijderButton() {
        Alert buttonFoutmelding = new Alert(Alert.AlertType.CONFIRMATION);
        buttonFoutmelding.setContentText("Weet u zeker dat u deze lijst wilt verwijderen?");
        Optional<ButtonType> delete = buttonFoutmelding.showAndWait();
        if(delete.get()== ButtonType.OK){
            TodoRegel selectLijst = todoRegelview.getListView().getSelectionModel().getSelectedItem();
            todoRegelDAO.remove(selectLijst);
//            List<TodoRegel> lijsten = todoRegelDAO.getAllFor(selectLijst);

//            for(TodoRegel lijst: lijsten){
//                todoRegelDAO.remove(lijst);
//            }
        }

        refreshTodoRegelListView();
    }

    private void nieuwButton() {
        Alert newButtonAlert = new Alert(Alert.AlertType.INFORMATION);
        newButtonAlert.setContentText("Nieuwbutton is aangeklikt");
        newButtonAlert.showAndWait();
        todoRegelview.getListView().getSelectionModel().clearSelection();
    }
    private void refreshTodoRegelListView(){
        ObservableList<TodoRegel> observableRegel = FXCollections.observableArrayList(todoRegelDAO.getAll());
        todoRegelview.getListView().setItems(observableRegel);
    }

    private void opslaanButton(){
        String nieuweRegel = todoRegelview.getTodoregelText().getText();
        TodoRegel todoRegel = new TodoRegel(nieuweRegel);

        TodoRegel selectRegel = todoRegelview.getListView().getSelectionModel().getSelectedItem();

        if(selectRegel != null){
            todoRegel.setId(selectRegel.getId());
        }


        todoRegelDAO.addOrUpdate(todoRegel);

        refreshTodoRegelListView();
    }

    @Override
    public View getView() {
        return todoRegelview;
    }
}
