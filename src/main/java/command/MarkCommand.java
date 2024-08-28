package command;
import components.Storage;
import components.Ui;
import task.Task;
import task.TaskList;

/**
 * Represents a command to mark a task as done or undone.
 */
public class MarkCommand extends Command {

    private int taskNumber;
    private boolean isMark;
    public MarkCommand(int taskNumber, boolean isMark) {
        super();
        this.taskNumber = taskNumber;
        this.isMark = isMark;
    }

    /**
     * Returns nothing. Executes marking a task as done or undone.
     * @param taskList TaskList object to add the task to.
     * @param ui Ui object for interacting with user.
     * @param storage Storage object for loading and saving data.
     */

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.getTask(taskNumber-1);
        task.setIsDone(isMark);
        ui.showMessage("I've marked this " + (isMark ? "" : "un") +
                "done for you nya. " +
                "\n" + task.toString());
    }
}
