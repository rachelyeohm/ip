
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Nyabot {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    private Parser parser;

    public enum TaskType {
        TODO, DEADLINE, EVENT;
    }

    public Nyabot() {
        this.taskList = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage();
        this.parser = new Parser();
    }
    public static void main(String[] args) {


        Storage storage = new Storage("./data/Nyabot.txt", this.ui);
        try {
            taskList = storage.load();
            System.out.println(prettifyString("Nyabot history has been loaded!"));
        } catch (NyabotFileNotFoundException | NyabotIOException e) {
            System.out.println(prettifyString(e.getMessage()));
        }



        while(scanner.hasNextLine()) {
            String input = scanner.nextLine();

        }

    }







}
