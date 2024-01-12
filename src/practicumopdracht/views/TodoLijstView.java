package practicumopdracht.views;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import practicumopdracht.models.TodoLijst;

public class TodoLijstView extends View {
    private TodoLijst todoLijst;
    private GridPane view;
    private ComboBox comboBox;
    private TextField textField;
    private TextArea textArea;
    private DatePicker datePicker;
    private CheckBox checkBox;
    private Button saveButton;
    private ListView<TodoLijst> listView;
    private HBox hBox;
    private Button nieuw;
    private Button verwijderen;
    private Button terugNaarOverzicht;

    public TodoLijstView() {
        initlayout();
    }


    private void initlayout() {
        view = new GridPane();

        view.setHgap(5);
        view.setVgap(5);
        view.setPadding(new Insets(10,10,10,10));
        view.setGridLinesVisible(false);

        //labels
        Label naamComboBox = new Label("Lijst:");
        Label naamTextField = new Label("Zin om te doen:");
        Label naamTextArea = new Label("Regel:");
        Label naamDatePicker = new Label("Datum:");
        Label naamCheckBox = new Label("Afgerond:");

        String geslacht[] = {"Man", "Vrouw", "Wil ik liever niet zeggen"};
        comboBox = new ComboBox(FXCollections.observableArrayList(geslacht));
        textField = new TextField();
        textArea = new TextArea("textarea");
        datePicker = new DatePicker();
        checkBox = new CheckBox("(boolean)");
        saveButton = new Button("Opslaan");
        listView = new ListView<>();
        nieuw = new Button("nieuw");
        verwijderen = new Button("Verwijderen");
        terugNaarOverzicht = new Button("Terug Naar Overzicht");
        hBox = new HBox(nieuw, verwijderen, terugNaarOverzicht);

        //placeholder
        comboBox.setPromptText(" ");
        textField.setPromptText("leeftijd in getallen");
        textArea.setPromptText("String");
        datePicker.setPromptText("LocalDate");

        //height
        textArea.setPrefHeight(20);
        comboBox.setMaxWidth(Double.MAX_VALUE);
        datePicker.setMaxWidth(Double.MAX_VALUE);
        saveButton.setMaxWidth(Double.MAX_VALUE);

        //views
        view.add(naamComboBox, 0,1);
        view.add(comboBox, 1,1);
        view.add(naamTextField, 0,2);
        view.add(textField, 1,2);
        view.add(naamTextArea, 0,3);
        view.add(textArea, 1,3);
        view.add(naamDatePicker, 0,4);
        view.add(datePicker, 1,4);
        view.add(naamCheckBox, 0,5);
        view.add(checkBox, 1,5);
        view.add(saveButton, 1, 6);
        view.add(listView, 1, 7);
        view.add(hBox,1, 8);

    }

    public TextField getTextField() {
        return textField;
    }

    public TextArea getTextArea() {
        return textArea;
    }

    public ListView<TodoLijst> getListView() {return listView;}

    public TextArea getTextarea(){return textArea;}

    public Button getNieuw() {
        return nieuw;
    }

    public Button getVerwijderen() {
        return verwijderen;
    }

    public Button getTerugNaarOverzicht(){return terugNaarOverzicht;}

    public Button getSaveButton(){return saveButton;}

    public CheckBox getCheckBox(){
        return checkBox;
    }

    public ComboBox getComboBox(){return comboBox;}

    public DatePicker getDatePicker() {return datePicker;}

    public TextField getLeeftijd(){return textField;}
    @Override
    public Parent getRoot() {
        return view;
    }

}
