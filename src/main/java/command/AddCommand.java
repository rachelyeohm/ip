package command;
import components.Storage;
import components.Ui;
import task.Task;
import task.TaskList;

/**
 * Represents a command to add task to the TaskList.
 */
public class AddCommand extends Command {
    private Task task;
    private int taskSize;
    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    /**
     * Returns nothing. Executes adding a task to the TaskList.
     * @param taskList TaskList object to add the task to.
     * @param ui Ui object for interacting with user.
     * @param storage Storage object for loading and saving data.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(this.task);
        this.taskSize = taskList.getNumTasks();
        ui.showMessage("I've added this task nya!" + "\n" + task
                + " \nNyow you have " + taskSize + " task(s) in the list.");
    }

    public Task getTask() {
        return this.task;
    }
}
