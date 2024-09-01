package command;
import components.Storage;
import components.Ui;
import task.TaskList;

/**
 * Represents a commmand to exit the chatbot.
 */
public class ExitCommand extends Command {
    public ExitCommand() {
        super.isExit = true;
    }

    /**
     * Returns nothing. Executes exiting the chatbot.
     * @param taskList TaskList object to add the task to.
     * @param ui Ui object for interacting with user.
     * @param storage Storage object for loading and saving data.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {

        return "Byebye, nya!";
    }
}
