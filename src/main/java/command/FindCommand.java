package command;

import components.Storage;
import components.Ui;
import task.Task;
import task.TaskList;

/**
 * Represents a command to find a specific task.
 */
public class FindCommand extends Command {

    private String searchTerm;
    public FindCommand(String searchTerm) {
        super();
        this.searchTerm = searchTerm;
    }

    /**
     * Returns nothing. Executes finding a specific task from a
     * user-inputted string.
     * @param taskList TaskList object to add the task to.
     * @param ui Ui object for interacting with user.
     * @param storage Storage object for loading and saving data.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList taskList1 = new TaskList();
        for (int i = 0; i < taskList.getNumTasks(); i++) {
            Task task = taskList.getTask(i);
            if (task.getTaskName().contains(searchTerm)) {
                taskList1.addTask(task);
            }

        }
        return "Here are the matching tyasks in your list nya!\n" +
                taskList1.displayTasks();
    }
}

