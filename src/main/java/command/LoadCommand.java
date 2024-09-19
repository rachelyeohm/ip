package command;
import components.Storage;
import components.Ui;
import exception.NyabotException;
import task.Scheduler;
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
    public String execute(TaskList taskList, Ui ui, Storage storage,
                          Scheduler scheduler) throws NyabotException {
        try {
            taskList.replaceTasks(storage.load(scheduler));
            return "Nyabot history has been loaded!";
        } catch (NyabotException e) {
            return e.getMessage();
        }


    }
}
