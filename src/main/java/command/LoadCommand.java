package command;
import components.Storage;
import components.Ui;
import exception.NyabotException;
import task.TaskList;

public class LoadCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws NyabotException {
        taskList = storage.load();
        ui.showMessage("Nyabot history has been loaded!");

    }
}
