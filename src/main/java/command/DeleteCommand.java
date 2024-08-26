package command;
import components.Storage;
import components.Ui;
import task.TaskList;

public class DeleteCommand extends Command {
    private int taskNumber;
    private String taskDescription;
    private int taskSize;
    public DeleteCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.deleteTask(this.taskNumber);
        this.taskDescription = taskList.getTask(taskNumber-1).toString();
        this.taskSize = taskList.getNumTasks();
        ui.showMessage("I've deleted! this task nya!" + "\n" + this.taskDescription
                + " \nNyow you have " + this.taskSize + " task(s) in the list.");
    }
}
