package practicumopdracht.data;

import practicumopdracht.models.TodoLijst;

import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

public class TextTodoLijstDAO extends TodoLijstDAO {
    private static String FILENAME = "TodoLijst.txt";

    @Override
    public boolean load() {
        todoLijsten.clear();
        try(Scanner scanner = new Scanner(new File(FILENAME))){
            while(scanner.hasNextLine()){
                String regel = scanner.nextLine();
                String[] todoLijstData = regel.split(",");
                TodoLijst todoLijst = new TodoLijst(todoLijstData[0], Double.parseDouble(todoLijstData[1]), Boolean.parseBoolean(todoLijstData[2]), LocalDate.parse(todoLijstData[3]), Integer.parseInt(todoLijstData[4]));

                addOrUpdate(todoLijst);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean save() {
        try (PrintWriter printWriter = new PrintWriter(new File(FILENAME))) {
            for (TodoLijst todoLijst: todoLijsten){
                printWriter.print(todoLijst.getNaam() + ",");
                printWriter.print(todoLijst.getCijfer() + ",");
                printWriter.print(todoLijst.isGehaald()+ ",");
                printWriter.print(todoLijst.getDatum() + ",");
                printWriter.println(todoLijst.getMasterId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
