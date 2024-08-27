package command;

import components.Storage;
import components.Ui;
import task.Task;
import task.TaskList;

public class FindCommand extends Command {

    private String searchTerm;
    public FindCommand(String searchTerm) {
        super();
        this.searchTerm = searchTerm;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList taskList1 = new TaskList();
        for (int i = 0; i < taskList.getNumTasks(); i++) {
            Task task = taskList.getTask(i);
            if (task.getTaskName().contains(searchTerm)) {
                taskList1.addTask(task);
            }
        }
        ui.showMessage("Here are the matching tyasks in your list nya!\n" +
                taskList1.displayTasks());
    }
}
