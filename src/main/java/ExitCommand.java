public class ExitCommand extends Command {
    public ExitCommand() {
        super.isExit = true;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showMessage("Byebye, nya!");
    }
}
