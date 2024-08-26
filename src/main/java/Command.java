abstract public class Command {

    protected boolean isExit = false;

    abstract public void execute(TaskList taskList, Ui ui, Storage storage) throws NyabotException;


}
