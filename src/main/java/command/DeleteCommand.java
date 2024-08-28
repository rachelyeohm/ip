package command;
import components.Storage;
import components.Ui;
import task.TaskList;

/**
 * Represents a command to delete a task from the TaskList.
 */
public class DeleteCommand extends Command {
    private int taskNumber;
    private String taskDescription;
    private int taskSize;
    public DeleteCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    /**
     * Returns nothing. Executes deleting a task from the TaskList.
     * @param taskList TaskList object to add the task to.
     * @param ui Ui object for interacting with user.
     * @param storage Storage object for loading and saving data.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.deleteTask(this.taskNumber);
        this.taskDescription = taskList.getTask(taskNumber-1).toString();
        this.taskSize = taskList.getNumTasks();
        ui.showMessage("I've deleted! this task nya!" + "\n" + this.taskDescription
                + " \nNyow you have " + this.taskSize + " task(s) in the list.");
    }
}
