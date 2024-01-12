package practicumopdracht.data;

import practicumopdracht.models.TodoRegel;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class TextTodoRegelDAO extends TodoRegelDAO{
    private static String FILENAME = "TodoRegel.txt";

    @Override
    public boolean load() {
        todoRegels.clear();
        try(Scanner scanner = new Scanner(new File(FILENAME))){
            while(scanner.hasNextLine()){
                String regel = scanner.nextLine();
                String[] TodoRegelData = regel.split(",");
                TodoRegel todoRegel = new TodoRegel(TodoRegelData[0]);
                addOrUpdate(todoRegel);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean save() {
        try (PrintWriter printWriter = new PrintWriter(new File(FILENAME))) {
            for (TodoRegel todoRegel : todoRegels){
                printWriter.println(todoRegel.getNaam() + ",");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;

    }
}
