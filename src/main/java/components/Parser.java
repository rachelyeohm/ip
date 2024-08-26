package components;

import command.*;
import exception.*;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;
import task.Task.TaskType;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static Command parse(String input) throws NyabotException {
        String firstWord = input.split(" ")[0];
        switch(firstWord) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark": case"unmark":
            int number = handleTaskWithTaskNumber(input, firstWord);
            return new MarkCommand(number, firstWord.equals("mark"));
        case "deadline": case "todo": case "event":
            task.Task.TaskType taskType =  firstWord.equals("todo")
                    ? task.Task.TaskType.TODO
                    : firstWord.equals("deadline")
                    ? task.Task.TaskType.DEADLINE
                    : task.Task.TaskType.EVENT;
            return parseNewTask(input, taskType);
        case "delete":
            int deleteNumber = handleTaskWithTaskNumber(input, "delete");
            return new DeleteCommand(deleteNumber);
        case "save":
            return new SaveCommand();
        default:
            throw new NyabotNoSuchCommandException("Nya, there is no such command!");
        }
    }


    public static Command parseNewTask(String input, task.Task.TaskType taskType) throws NyabotException {
        Task task;
        String[] parts;
        String taskName;
        Command command;
        switch (taskType) {
        case TODO:

            parts = input.split(" ", 2);
            if (parts.length == 1 || parts[1].trim().isEmpty()) {
                throw new NyabotMissingArgumentException("Valid todo name required nya!");
            }
            task = new ToDo(parts[1].trim());
            command = new AddCommand(task);
            break;
        case DEADLINE:

            parts = input.split("/by", 2);
            if (parts.length == 1 || parts[1].trim().isEmpty()) { //no by command, or by command has nothing after .
                throw new NyabotMissingArgumentException("A valid /by command is required, nya!");
            }
            taskName = parts[0].split(" ", 2)[1];
            if (taskName.trim().isEmpty()) {
                throw new NyabotMissingArgumentException("Valid deadline name required nya!");
            }
            String deadline = parts[1];
            task = new Deadline(taskName.trim(), Parser.convertInputToDate(deadline.trim()));
            command = new AddCommand(task);
            break;
        case EVENT:
            parts = input.split("/from|/to", 3);
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
            taskName = parts[0].split(" ", 2)[1];
            if (taskName.trim().isEmpty()) {
                throw new NyabotMissingArgumentException("Valid deadline name required nya!");
            }
            String startTime = fromFirst ? parts[1].trim() : parts[2].trim();
            String endTime = fromFirst ? parts[2].trim() : parts[1].trim();
            LocalDateTime startTimeDate = Parser.convertInputToDate(startTime.trim());
            LocalDateTime endTimeDate = Parser.convertInputToDate(endTime.trim());
            if (endTimeDate.isBefore(startTimeDate)) {
                throw new NyabotDateException("Start date cannot be after end date!");
            }
            task = new Event(taskName.trim(), startTimeDate, endTimeDate);
            command = new AddCommand(task);
            break;
        default:
            throw new NyabotParseException("Nya, we are unable to parse this..");

        }
        return command;
    }

    public static int handleTaskWithTaskNumber(String input, String commandName) throws NyabotMissingArgumentException, NyabotIndexOutOfBoundsException {
        try {
            String[] split = input.split(" ", 2);
            if (split.length == 1 || split[1].isEmpty()) {
                throw new NyabotMissingArgumentException("Valid task number is required " +
                        "for " + commandName + " command nya!");
            }
            return Integer.parseInt(split[1]);
        }  catch (IndexOutOfBoundsException e) {
            throw new NyabotIndexOutOfBoundsException("This task nyumber does not exist!");
        } catch (NumberFormatException e) {
            throw new NyabotMissingArgumentException("Valid task number is required for " +
                    commandName + " command, nya.");
        }

    }

    public static LocalDateTime convertInputToDate(String string) throws NyabotParseException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return LocalDateTime.parse(string, formatter);
        } catch (DateTimeParseException e) {
            throw new NyabotParseException("Nya, we can't parse your date!");
        }

    }

    public static LocalDateTime convertTxtInputToDate(String string) throws NyabotParseException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mma");
            return LocalDateTime.parse(string, formatter);
        } catch (DateTimeParseException e) {
            throw new NyabotParseException("Nya, we can't parse your date!");
        }

    }



    public static String convertDateToOutput(LocalDateTime date) throws NyabotParseException {
        try {
            return date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mma"));
        } catch (DateTimeException e) {
            throw new NyabotParseException("Nya, we can't parse your date!");
        }

    }
}
