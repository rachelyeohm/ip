package command;

import components.Storage;
import components.Ui;
import task.*;

import java.time.LocalDate;

public class GetScheduleCommand extends Command {

    private LocalDate date;

    public GetScheduleCommand(LocalDate date) {
        super();
        this.date = date;
    }

    /**
     * Returns nothing. Executes adding a task to the TaskList.
     *
     * @param taskList TaskList object to add the task to.
     * @param ui       Ui object for interacting with user.
     * @param storage  Storage object for loading and saving data.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage, Scheduler scheduler) {
        return scheduler.getScheduleOfDay(this.date);

    }

}