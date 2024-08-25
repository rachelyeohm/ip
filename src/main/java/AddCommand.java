public class AddCommand extends Command {
    private Task task;
    public AddCommand(Task task) {
        super();
        this.task = task;
    }
    @Override
    public String successfulMessage() {
        return "I've added this task nya!" + "\n\t" + taskConfirmation
                + " \n\tNyow you have " + taskList.size() + " task(s) in the list."
    }
}
