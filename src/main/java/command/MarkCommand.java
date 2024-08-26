package command;
import components.Storage;
import components.Ui;
import task.Task;
import task.TaskList;

public class MarkCommand extends Command {

    private int taskNumber;
    private boolean isMark;
    public MarkCommand(int taskNumber, boolean isMark) {
        super();
        this.taskNumber = taskNumber;
        this.isMark = isMark;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.getTask(taskNumber-1);
        task.setIsDone(isMark);
        ui.showMessage("I've marked this " + (isMark ? "" : "un") +
                "done for you nya. " +
                "\n" + task.toString());
    }
}
