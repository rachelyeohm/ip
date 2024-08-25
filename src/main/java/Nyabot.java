
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Nyabot {
    enum MarkStatus {
        MARK,
        UNMARK;
    }

    public enum TaskType {
        TODO, DEADLINE, EVENT;
    }
    private static ArrayList<Task> taskList = new ArrayList<Task>();
    public static void main(String[] args) {
        String byestatement = "";
        try {
            Files.createDirectories(Paths.get("./data"));
            System.out.println(prettifyString("'./data' directory was successfully created to store the txt file nya."));
        } catch (IOException e) {
            System.out.println(prettifyString("There was an issue with creating the correct directory " +
                    "to save the tasks txt file in, nya."));
        }

        Storage storage = new Storage("./data/Nyabot.txt");
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

    public static String prettifyString(String str) {
        String horizontalline = "_".repeat(70);
        return "\t" + horizontalline + "\n\t" + str + "\n\t" +
                horizontalline;
    }

    public static String displaylist() {
        StringBuilder textliststring = new StringBuilder();
        for (int i = 0; i < Nyabot.taskList.size(); i++) {
            if (i > 0) {
                textliststring.append("\t");
            }
            textliststring.append((i + 1)).append(". ").append(Nyabot.taskList.get(i));
            if (i < Nyabot.taskList.size()-1) {
                textliststring.append("\n");
            }

        }
       return  prettifyString(textliststring.toString());
    }

    public static String mark(String input, MarkStatus mark) throws NyabotMissingArgumentException, NyabotIndexOutOfBoundsException {
        int number;

        try {
            String[] split = input.split(" ", 2);
            if (split.length == 1 || split[1].isEmpty()) {
                throw new NyabotMissingArgumentException("Valid task number is required for "
                        + (mark == MarkStatus.MARK ? "mark" : "unmark") + " command nya!");
            }
            number = Integer.parseInt(split[1]);
            taskList.get(number-1).setIsDone(mark == MarkStatus.MARK);
        }  catch (IndexOutOfBoundsException e) {
            throw new NyabotIndexOutOfBoundsException("This task nyumber does not exist!");
        } catch (NumberFormatException e) {
            throw new NyabotMissingArgumentException("Valid task number is required for "
                    + (mark == MarkStatus.MARK ? "mark" : "unmark") + " command nya.");
        }
        return prettifyString("I've marked this " + (mark == MarkStatus.MARK ? "" : "un") +
                "done for you nya. " +
                "\n\t" + taskList.get(number-1).toString());

    }



    public static String delete(String input) throws NyabotMissingArgumentException, NyabotIndexOutOfBoundsException {
        int number;
        String taskDescription;
        try {
            String[] split = input.split(" ", 2);
            if (split.length == 1 || split[1].isEmpty()) {
                throw new NyabotMissingArgumentException("Valid task number is required for delete command nya!");
            }
            number = Integer.parseInt(split[1]);
            taskDescription = taskList.get(number-1).toString();
            taskList.remove(number-1);
        }  catch (IndexOutOfBoundsException e) {
            throw new NyabotIndexOutOfBoundsException("This task nyumber does not exist!");
        } catch (NumberFormatException e) {
            throw new NyabotMissingArgumentException("Valid task number is required for delete command, nya.");
        }
        return "I've deleted! this task nya!" + "\n\t" + taskDescription
                + " \n\tNyow you have " + taskList.size() + " task(s) in the list.";

    }

}
