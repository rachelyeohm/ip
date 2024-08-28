package command;
import components.Storage;
import components.Ui;
import exception.NyabotException;
import task.TaskList;

/**
 * Represents a command to load chatbot data from the txt file.
 */
public class LoadCommand extends Command {

    /**
     * Returns nothing. Executes loading chatbot data from the txt file.
     * @param taskList TaskList object to add the task to.
     * @param ui Ui object for interacting with user.
     * @param storage Storage object for loading and saving data.
     * @throws NyabotException If unexpected exception occurs when loading.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws NyabotException {
        taskList = storage.load();
        ui.showMessage("Nyabot history has been loaded!");

    }
}
