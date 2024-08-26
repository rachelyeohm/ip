public class AddCommand extends Command {
    private Task task;
    private int taskSize;
    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(this.task);
        this.taskSize = taskList.getNumTasks();
        ui.showMessage("I've added this task nya!" + "\n" + task
                + " \nNyow you have " + taskSize + " task(s) in the list.");
    }
}
