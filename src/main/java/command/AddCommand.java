package command;
import components.Storage;
import components.Ui;
import task.Deadline;
import task.Event;
import task.Scheduler;
import task.Task;
import task.TaskList;

/**
 * Represents a command to add task to the TaskList.
 */
public class AddCommand extends Command {
    private final Task task;

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
    public String execute(TaskList taskList, Ui ui, Storage storage, Scheduler scheduler) {
        taskList.addTask(this.task);
        int taskSize = taskList.getNumTasks();
        assert taskSize >= 1 : "task size should be more than 0";
        if (this.task instanceof Deadline deadline) {
            scheduler.addTask(deadline, deadline.getEndTime(), false);
        } else if (this.task instanceof Event event) {
            scheduler.addTask(event, event.getStartTime(), true);
            scheduler.addTask(event, event.getEndTime(), false);
        }
        return "I've added this task nya!" + "\n" + task
                + " \nNyow you have " + taskSize + " task(s) in the list.";


    }

    public Task getTask() {
        assert this.task != null : "task should be more than null";
        return this.task;
    }
}
