package practicumopdracht.views;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import practicumopdracht.models.TodoLijst;
import practicumopdracht.models.TodoRegel;

import java.util.List;

public class TodoRegelView extends View {

    private VBox vBox;
    private HBox hBox;
    private GridPane view;
    private Button terug;
    private Button nieuw;
    private Button verwijderen;
    private ListView<TodoRegel> listView;
    private TextArea todoregelText;
    private Button opslaan;
    private Label textLabel;
    private BorderPane borderPane;
    private MenuBar menuBar;
    private Menu menu;
    private MenuItem saveMenuItem;
    private MenuItem loadMenuItem;
    private MenuItem closeMenuItem;
    private SeparatorMenuItem seperator;


    public TodoRegelView() {
        initlayout();
    }

    public MenuItem getSaveMenuItem() {
        return saveMenuItem;
    }

    public MenuItem getLoadMenuItem() {
        return loadMenuItem;
    }

    public MenuItem getCloseMenuItem() {
        return closeMenuItem;
    }

    private void initlayout() {
        view = new GridPane();

        listView = new ListView<>();
        terug = new Button("Terug Naar Overzicht");
        nieuw = new Button("Nieuw");
        verwijderen = new Button("Verwijderen");
        HBox hBox = new HBox(terug, nieuw, verwijderen);
        opslaan = new Button("Opslaan");
        VBox vBox = new VBox(hBox, listView, opslaan);
        todoregelText = new TextArea();
        textLabel = new Label("todoregel:");

        opslaan.setMaxWidth(700);
        vBox.setPrefWidth(700);
        terug.setPrefWidth(300);
        nieuw.setPrefWidth(250);
        todoregelText.setPrefHeight(20);
        verwijderen.setPrefWidth(250);
        nieuw.setPadding(new Insets (10,10,10,10));
        verwijderen.setPadding(new Insets (10,10,10,10));
        terug.setPadding(new Insets (10,10,10,10));
        opslaan.setPadding(new Insets (10,10,10,10));


        view.add(vBox, 0, 3);
        view.add(textLabel, 0,1);
        view.add(todoregelText, 0, 2);


        borderPane = new BorderPane();
        menuBar = new MenuBar();
        seperator = new SeparatorMenuItem();

        menu = new Menu("Bestand");
        saveMenuItem = new MenuItem("Opslaan");
        loadMenuItem = new MenuItem("Laden");
        closeMenuItem = new MenuItem("Afsluiten");
        menu.getItems().addAll(saveMenuItem, loadMenuItem,
                seperator, closeMenuItem);

        borderPane.setTop(menuBar);
        menuBar.getMenus().add(menu);
        view.add(menuBar, 0, 0);

    }

    public Button getBack() {
        return terug;
    }

    public Button getNew() {
        return nieuw;
    }

    public Button getDelete() {
        return verwijderen;
    }

    public ListView<TodoRegel> getListView(){
        return listView;
    }

    public Button getOpslaan() {
        return opslaan;
    }

    public TextArea getTodoregelText() {
        return todoregelText;
    }

    @Override
    public Parent getRoot() {
        return view;
    }

}
