
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Nyabot {

    private static ArrayList<Task> tasklist = new ArrayList<Task>();
    public static void main(String[] args) {

        String hellostatement = "Hello! I'm Nyabot. What can I do for you, nya?";
        String byestatement = "Bye. Hope to see you again, nya!";
        System.out.println(Nyabot.prettifyString(hellostatement));

        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println(Nyabot.prettifyString(byestatement));
                break;
            } else if (input.equals("list")) {
                System.out.println(displaylist());
                continue;
            } else if (input.startsWith("mark")) {
                System.out.println(mark(input));
                continue;
            } else if (input.startsWith("unmark")) {
                System.out.println(unmark(input));
                continue;
            } else if (input.startsWith("deadline") || input.startsWith("todo") || input.startsWith("event")) {
                System.out.println(prettifyString(handleNewTask(input, input.split(" ")[0])));
            } else {
                System.out.println(prettifyString(input));
            }
        }

    }

    public static String prettifyString(String str) {
        String horizontalline = "_".repeat(70);
        return "\t" + horizontalline + "\n\t" + str + "\n\t" +
                horizontalline;
    }

    public static String displaylist() {
        StringBuilder textliststring = new StringBuilder();
        for (int i = 0 ; i < Nyabot.tasklist.size(); i++) {
            if (i > 0) {
                textliststring.append("\t");
            }
            textliststring.append((i + 1)).append(". ").append(Nyabot.tasklist.get(i));
            if (i < Nyabot.tasklist.size()-1) {
                textliststring.append("\n");
            }

        }
       return  prettifyString(textliststring.toString());
    }

    public static String mark(String input) {
        int number;
        try {
            String[] split = input.split(" ", 2);
            number = Integer.parseInt(split[1]);
            tasklist.get(number-1).setDone(true);
        }  catch (IndexOutOfBoundsException e) {
            return prettifyString("Sorry, this task does nyot exist nya.");
        }  catch (Exception e) {
            return prettifyString("Sorry, please retype your command correctly nya.");
        }
        return prettifyString("Congrats nya! I've marked this done for you. " +
                "\n\t" + tasklist.get(number-1).toString());



    }

    public static String unmark(String input) {
        int number;
        try {
            String[] split = input.split(" ", 2);
            number = Integer.parseInt(split[1]);
            tasklist.get(number-1).setDone(false);
        }  catch (IndexOutOfBoundsException e) {
            return prettifyString("Sorry, this task does nyot exist nya.");
        }  catch (Exception e) {
            return prettifyString("Sorry, please retype your command correctly nya.");
        }
        return prettifyString("I've marked this undone for you, nya :(. " +
                "\n\t" + tasklist.get(number-1).toString());


    }

    public static String handleNewTask(String input, String taskType) {
        String taskConfirmation = "";
        try {
            if (taskType.equals("todo")) {
                Task task = new ToDo(input.split(" ", 2)[1]);
                tasklist.add(task);
                taskConfirmation = task.toString();
            } else if (taskType.equals("deadline")) {
                String[] parts = input.split("/by", 2);
                String taskName = parts[0].split(" ", 2)[1];
                String deadline = parts[1];
                Task task = new Deadline(taskName, deadline);
                tasklist.add(task);
                taskConfirmation =  task.toString();
            } else {
                String[] parts = input.split("/from", 2);
                String taskName = parts[0].split(" ", 2)[1];
                String startTime = parts[1].split("/to", 2)[0];
                String endTime = parts[1].split("/to", 2)[1];
                Task task = new Event(taskName, startTime, endTime);
                tasklist.add(task);
                taskConfirmation =  task.toString();
            }
        } catch (Exception e) {
            return "Sorry, there was an nyerror with entering your task.";
        }
        return "I've added this task nya!" + "\n\t" + taskConfirmation
                + " \n\tNyow you have " + tasklist.size() + " task(s) in the list.";
    }
}
