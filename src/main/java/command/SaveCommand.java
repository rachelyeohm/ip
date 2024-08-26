package command;

import command.Command;
import components.Storage;
import components.Ui;
import exception.NyabotException;
import task.TaskList;

public class SaveCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws NyabotException {
        storage.save(taskList);
        ui.showMessage("Saved successfully, nya!");
    }
}
