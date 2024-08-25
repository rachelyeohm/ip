public class Parser {

    public Command parse(String input) throws NyabotException {
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
            Nyabot.TaskType taskType =  firstWord.equals("todo")
                    ? Nyabot.TaskType.TODO
                    : firstWord.equals("deadline")
                    ? Nyabot.TaskType.DEADLINE
                    : Nyabot.TaskType.EVENT;
            return parseNewTask(input, taskType);
        case "delete":
            int number = handleTaskWithTaskNumber(input, "delete");
            return new DeleteCommand(number);
        case "save":
            return new SaveCommand();
        default:
            throw new NyabotNoSuchCommandException("Nya, there is no such command!");
        }
    }


    public Command parseNewTask(String input, Nyabot.TaskType taskType) throws NyabotMissingArgumentException, NyabotParseException {
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
            task = new Deadline(taskName.trim(), deadline.trim());
            command = new AddCommand(task);
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
            task = new Event(taskName.trim(), startTime.trim(), endTime.trim());
            command = new AddCommand(task);
            break;
        default:
            throw new NyabotParseException("Nya, we are unable to parse this..");

        }
        return command;
    }

    public int handleTaskWithTaskNumber(String input, String commandName) throws NyabotMissingArgumentException, NyabotIndexOutOfBoundsException {
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
}
