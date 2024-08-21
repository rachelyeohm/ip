
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Nyabot {
    enum MarkStatus {
        MARK,
        UNMARK;
    }
    private static ArrayList<Task> tasklist = new ArrayList<Task>();
    public static void main(String[] args) {

        String hellostatement = "Hello! I'm Nyabot. What can I do for you, nya?";
        String byestatement = "Bye. Hope to see you again, nya!";
        System.out.println(Nyabot.prettifyString(hellostatement));

        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String firstWord = input.split(" ")[0];
            if (firstWord.equals("bye")) {
                System.out.println(Nyabot.prettifyString(byestatement));
                break;
            } else if (firstWord.equals("list")) {
                System.out.println(displaylist());
                continue;
            } else if (firstWord.equals("mark") || firstWord.equals("unmark")) {
                try {
                    System.out.println(mark(input, firstWord.equals("mark") ? MarkStatus.MARK : MarkStatus.UNMARK));
                }
                catch (NyabotMissingArgumentException | NyabotIndexOutOfBoundsException e){
                    System.out.println(prettifyString(e.getMessage()));
                }

                continue;
            } else if (firstWord.equals("deadline") || firstWord.equals("todo") || firstWord.equals("event")) {
                System.out.println(prettifyString(handleNewTask(input, input.split(" ")[0])));
                continue;
            } else if (firstWord.equals("delete")) {
                try {
                    System.out.println(prettifyString(delete(input)));
                }
                catch (NyabotMissingArgumentException | NyabotIndexOutOfBoundsException e){
                    System.out.println(prettifyString(e.getMessage()));
                }
                continue;
            } else {
                System.out.println(prettifyString("This is nyot a valid command :("));
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

    public static String mark(String input, MarkStatus mark) throws NyabotMissingArgumentException, NyabotIndexOutOfBoundsException {
        int number;

        try {
            String[] split = input.split(" ", 2);
            if (split.length == 1 || split[1].isEmpty()) {
                throw new NyabotMissingArgumentException("Valid task number is required for "
                        + (mark == MarkStatus.MARK ? "mark" : "unmark") + " command nya!");
            }
            number = Integer.parseInt(split[1]);
            tasklist.get(number-1).setDone(mark == MarkStatus.MARK);
        }  catch (IndexOutOfBoundsException e) {
            throw new NyabotIndexOutOfBoundsException("This task nyumber does not exist!");
        } catch (NumberFormatException e) {
            throw new NyabotMissingArgumentException("Valid task number is required for "
                    + (mark == MarkStatus.MARK ? "mark" : "unmark") + " command nya.");
        }
        return prettifyString("Congrats nya! I've marked this " + (mark == MarkStatus.MARK ? "" : "un") +
                "done for you. " +
                "\n\t" + tasklist.get(number-1).toString());

    }


    public static String handleNewTask(String input, String taskType) {
        String taskConfirmation = "";
        try {
            if (taskType.equals("todo")) {
                String[] parts = input.split(" ", 2);
                if (parts.length == 1 || parts[1].trim().isEmpty()) {
                    throw new NyabotMissingArgumentException("Valid todo name required nya!");
                }
                Task task = new ToDo(parts[1]);
                tasklist.add(task);
                taskConfirmation = task.toString();
            } else if (taskType.equals("deadline")) {
                String[] parts = input.split("/by", 2);
                if (parts.length == 1 || parts[1].trim().isEmpty()) { //no by command, or by command has nothing after .
                    throw new NyabotMissingArgumentException("A valid /by command is required, nya!");
                }
                String taskName = parts[0].split(" ", 2)[1];
                if (taskName.trim().isEmpty()) {
                    throw new NyabotMissingArgumentException("Valid deadline name required nya!");
                }
                String deadline = parts[1];
                Task task = new Deadline(taskName, deadline);
                tasklist.add(task);
                taskConfirmation =  task.toString();
            } else {
                String[] parts = input.split("/from|/to", 3);
                if (parts.length < 3 || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
                    throw new NyabotMissingArgumentException("A valid /from and /to command is required, nya!");
                }
                String[] partsFull = input.split(" ");

                //handles order of commands /from and /to
                boolean fromFirst = true;
                for (int i = 0; i < partsFull.length; i++) {
                    if (partsFull[i].equals("/from") || partsFull[i].equals("/to")) {
                        fromFirst = partsFull[i].equals("/from");
                        i = partsFull.length;
                    }
                }
                String taskName = parts[0].split(" ", 2)[1];
                if (taskName.trim().isEmpty()) {
                    throw new NyabotMissingArgumentException("Valid deadline name required nya!");
                }
                String startTime = fromFirst ? parts[1].trim() : parts[2].trim();
                String endTime = fromFirst ? parts[2].trim() : parts[1].trim();
                Task task = new Event(taskName, startTime, endTime);
                tasklist.add(task);
                taskConfirmation =  task.toString();
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return "I've added this task nya!" + "\n\t" + taskConfirmation
                + " \n\tNyow you have " + tasklist.size() + " task(s) in the list.";
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
            taskDescription = tasklist.get(number-1).toString();
            tasklist.remove(number-1);
        }  catch (IndexOutOfBoundsException e) {
            throw new NyabotIndexOutOfBoundsException("This task nyumber does not exist!");
        } catch (NumberFormatException e) {
            throw new NyabotMissingArgumentException("Valid task number is required for delete command, nya.");
        }
        return "I've deleted! this task nya!" + "\n\t" + taskDescription
                + " \n\tNyow you have " + tasklist.size() + " task(s) in the list.";

    }

}
