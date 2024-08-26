package command;
import components.Storage;
import components.Ui;
import task.TaskList;
public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {

        ui.showMessage(taskList.displayTasks());
    }

}
