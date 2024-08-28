package command;
import components.Storage;
import components.Ui;
import task.TaskList;
import exception.NyabotException;


/**
 * Represents command from user input to execute.
 */
abstract public class Command {

    protected boolean isExit = false;

    /**
     * Returns whether the command is an exit command.
     *
     * @return Boolean whether the command is an exit command.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Returns nothing. Executes the command.
     * @param taskList TaskList object to add the task to.
     * @param ui Ui object for interacting with user.
     * @param storage Storage object for loading and saving data.
     */
    abstract public void execute(TaskList taskList, Ui ui, Storage storage) throws NyabotException;


}
