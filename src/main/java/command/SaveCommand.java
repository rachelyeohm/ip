package command;

import components.Storage;
import components.Ui;
import exception.NyabotException;
import task.TaskList;

/**
 * Represents a command to save the chatbot data in a txt file.
 */
public class SaveCommand extends Command {

    /**
     * Returns nothing. Executes saving chatbot data into a txt file.
     * @param taskList TaskList object to add the task to.
     * @param ui Ui object for interacting with user.
     * @param storage Storage object for loading and saving data.
     * @throws NyabotException If unexpected exception occurs during
     * saving.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage)
            throws NyabotException {
        storage.save(taskList);
        return "Saved successfully, nya!";
    }
}
