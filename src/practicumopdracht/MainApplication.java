package practicumopdracht;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import practicumopdracht.controller.Controller;
import practicumopdracht.controller.TodoRegelController;
import practicumopdracht.data.*;
import practicumopdracht.views.TodoLijstView;

public class MainApplication extends Application {
    private static Stage stage;
    private static TodoLijstDAO todolijstDAO = new TextTodoLijstDAO();
    private static TodoRegelDAO todoregelDAO = new TextTodoRegelDAO();
//    private static MasterDAO masterDAO = new TextMasterDAO;
//    private static DetailDAO detailDAO = new tekstDetailDAO;

    @Override
    public void start(Stage stage) {
        MainApplication.stage = stage;
        stage.setTitle("Practicumopdracht OOP2 - <Danny>");

        stage.setWidth(640);
        stage.setWidth(480);

        //TodoLijstController is de MasterController
        switchController(new TodoRegelController());
    }

    public static void switchController(Controller controller) {

        stage.setScene(new Scene(controller.getView().getRoot()));
        stage.show();
    }

    public static TodoLijstDAO getTodolijstDAO() {
        return todolijstDAO;
    }

    public static TodoRegelDAO getTodoregelDAO() {
        return todoregelDAO;
    }

}
