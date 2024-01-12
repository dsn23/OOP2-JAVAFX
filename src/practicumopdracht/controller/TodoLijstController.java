package practicumopdracht.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import practicumopdracht.MainApplication;
import practicumopdracht.data.TodoLijstDAO;
import practicumopdracht.data.TodoRegelDAO;
import practicumopdracht.models.TodoLijst;
import practicumopdracht.models.TodoRegel;
import practicumopdracht.views.TodoLijstView;
import practicumopdracht.views.View;

import java.time.LocalDate;
import java.util.Optional;

public class TodoLijstController extends Controller {

    private TodoLijstView todoLijstView;
    private TodoLijst todolijst;
    private TodoLijst currentlijst;
    private TodoLijstDAO todolijstDAO = MainApplication.getTodolijstDAO();
    private TodoRegelDAO todoRegelDAO = MainApplication.getTodoregelDAO();

    public TodoLijstController(TodoRegel todoRegel) {
        todoLijstView = new TodoLijstView();

        ObservableList<TodoLijst> observableTodolijstList = FXCollections.observableArrayList(todolijstDAO.getAllFor(todoRegel));

        todoLijstView.getComboBox().setValue(todoRegel);


        todoLijstView.getListView().setItems(observableTodolijstList);

//        todoLijstView.getListView().getSelectionModel().selectedItemProperty()
//                .addListener(
//                        (observableValue, oldLijst, newLijst) -> {
//                            currentlijst = newLijst;
//                        }
//                );

        //eventhandlers
        todoLijstView.getNieuw().setOnAction(actionEvent -> nieuwButton());
        todoLijstView.getVerwijderen().setOnAction(actionEvent -> removeButtonHandler());
        todoLijstView.getTerugNaarOverzicht().setOnAction(event -> switchTo());
        todoLijstView.getSaveButton().setOnAction(event -> aantalKerenGeprobeerd());

        ObservableList observableList1 = FXCollections.observableArrayList(todoRegelDAO.getAll());

        todoLijstView.getComboBox().setItems(observableList1);

        todoLijstView.getComboBox().getSelectionModel().selectedItemProperty()
                .addListener(
                        (observableValue, oldValue, newValue) -> {
                            ObservableList observableListViewItems = FXCollections.observableArrayList(todolijstDAO.getAllFor((TodoRegel) newValue));

                            todoLijstView.getListView().setItems(observableListViewItems);
                        }
                );

        todoLijstView.getListView().getSelectionModel().selectedItemProperty()
                .addListener(
                        (observableValue, oldValue, newValue) -> {
                            view((TodoLijst) newValue);
                        }
                );

//        refresh();
    }

    private void view(TodoLijst newValue) {
        if(newValue != null){
            todoLijstView.getDatePicker().setValue(newValue.getDatum());
            todoLijstView.getTextarea().setText(Double.toString(newValue.getCijfer()));
            todoLijstView.getTextField().setText(newValue.getNaam());
            todoLijstView.getCheckBox().setSelected(newValue.isGehaald());
        }
    }

//    private void haalWaardesOp(TodoLijst todolijst) {
//            currentlijst = todoLijstView.getListView().getSelectionModel().getSelectedItem();
////        if (todolijst != null) {
////            todoLijstView.getTextarea().setText(String.valueOf(todolijst.getNaam()));
////            todoLijstView.getLeeftijd().setText(String.valueOf(Integer.valueOf((int) todolijst.getCijfer()))) ;
////            todoLijstView.getCheckBox().setSelected(todolijst.isGehaald());
////            todoLijstView.getDatePicker().setValue(todolijst.getDatum());
////
////        }
//    }

    private void removeButtonHandler() {

//        Alert delete = new Alert(Alert.AlertType.CONFIRMATION);
//        delete.setContentText("U staat op het punt een lijst te verwijderen. Weet u het zeker?");
//        Optional<ButtonType> knop = delete.showAndWait();
//        if (knop.get() == ButtonType.OK) {
//            System.out.println(currentlijst);
            TodoLijst selectResultaat = (TodoLijst) todoLijstView.getListView().getSelectionModel().getSelectedItem();

            todolijstDAO.remove(selectResultaat);
//        }
        refresh();
    }

    private void switchTo() {
        MainApplication.switchController(new TodoRegelController());
    }


    private void aantalKerenGeprobeerd() {
//        try {
//            int intLeeftijd = Integer.parseInt(todoLijstView.getLeeftijd().getText());
//        } catch (Exception e) {
//            Alert fouteLeeftijd = new Alert(Alert.AlertType.ERROR);
//            fouteLeeftijd.setContentText("De volgende fouten zijn gevonden: \n - Invoerveld moet een geheel getal zijn.");
//            fouteLeeftijd.showAndWait();
//            todoLijstView.getLeeftijd().clear();
//        }
//        try {
//            double doubleLeeftijd = Double.parseDouble(todoLijstView.getLeeftijd().getText());
//        } catch (NumberFormatException e) {
//            Alert fouteLeeftijd = new Alert(Alert.AlertType.ERROR);
//            fouteLeeftijd.setContentText("De volgende fouten zijn gevonden: \n - Invoerveld moet een geheel of decimaal getal zijn.");
//            fouteLeeftijd.showAndWait();
//            todoLijstView.getLeeftijd().clear();
//        }

        String text = todoLijstView.getTextField().getText();
        String leeftijd = todoLijstView.getLeeftijd().getText();
        LocalDate datum = todoLijstView.getDatePicker().getValue();
        boolean checkbox = todoLijstView.getCheckBox().isSelected();

        double resultLeeftijd = Double.parseDouble(leeftijd);

        TodoRegel select = (TodoRegel) todoLijstView.getComboBox().getSelectionModel().getSelectedItem();

        TodoLijst todoLijst = new TodoLijst(text, resultLeeftijd, checkbox, datum, select.getId());

        TodoLijst selectRegel = todoLijstView.getListView().getSelectionModel().getSelectedItem();




        if(selectRegel != null){
            todoLijst.setTodolijstId(selectRegel.getTodolijstId());
        }


        todolijstDAO.addOrUpdate(todoLijst);
        refresh();
    }

    private void refresh() {

        TodoRegel selectItem = (TodoRegel) todoLijstView.getComboBox().getSelectionModel().getSelectedItem();


        ObservableList observableListViewItems = FXCollections.observableArrayList(todolijstDAO.getAllFor(selectItem));

        todoLijstView.getListView().setItems(observableListViewItems);
    }

    private void nieuwButton() {
        TodoLijst nieuwRegel = todoLijstView.getListView().getSelectionModel().getSelectedItem();
        Alert buttonFoutmelding = new Alert(Alert.AlertType.INFORMATION);
        buttonFoutmelding.setContentText("Nieuwbutton is aangeklikt");
        buttonFoutmelding.showAndWait();
        if (nieuwRegel != null) {
            todoLijstView.getListView().getSelectionModel().clearSelection();
            todoLijstView.getTextarea().clear();
            todoLijstView.getLeeftijd().clear();
            todoLijstView.getDatePicker().getEditor().clear();
            todoLijstView.getCheckBox().setSelected(false);
        }
        refresh();
    }

    @Override
    public View getView() {
        return todoLijstView;
    }
}
