package command;
import components.Storage;
import components.Ui;
import exception.NyabotIndexOutOfBoundsException;
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
     * @throws NyabotIndexOutOfBoundsException If task number is not in TaskList.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws NyabotIndexOutOfBoundsException {
        try {
            this.taskDescription = taskList.getTask(taskNumber-1).toString();
            taskList.deleteTask(this.taskNumber-1);
            this.taskSize = taskList.getNumTasks();
            assert this.taskSize >= 0 : "task size should be at least 0";
            return "I've deleted this task nya!" + "\n" + this.taskDescription
                    + " \nNyow you have " + this.taskSize + " task(s) in the list.";
        } catch (IndexOutOfBoundsException e) {
            throw new NyabotIndexOutOfBoundsException("This task number does not exist nya!");
        }

    }
}
