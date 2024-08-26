public class SaveCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws NyabotException {
        storage.save(taskList);
        ui.showMessage("Saved successfully, nya!");
    }
}
