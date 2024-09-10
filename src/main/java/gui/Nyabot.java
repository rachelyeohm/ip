package gui;

import command.Command;
import command.LoadCommand;
import components.Parser;
import components.Storage;
import components.Ui;
import exception.NyabotException;
import task.Scheduler;
import task.TaskList;

/**
 * Represents a chatbot instance.
 *
 */
public class Nyabot {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    private Scheduler scheduler;

    public Nyabot() {
        Ui ui = new Ui();
        try {
            TaskList taskList = new TaskList();
            Storage storage = new Storage("./data/Nyabot.txt", ui);
            this.scheduler = new Scheduler();
            this.taskList = taskList;
            this.ui = ui;
            this.storage = storage;
        } catch (NyabotException e) {
            ui.showMessage(e.getMessage());
        }

    }
    public static void main(String[] args) {
        Nyabot nyabot = new Nyabot();
        nyabot.run();

    }

    public Ui getUi() {
        return this.ui;
    }

    /**
     * Returns nothing.
     */
    public void run() {

        ui.showMessage(ui.showWelcome());
        try {
            new LoadCommand().execute(this.taskList, ui, storage, scheduler);
        } catch (NyabotException e) {
            ui.showMessage(e.getMessage());
        }
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                ui.showMessage(c.execute(this.taskList, ui, storage, scheduler));
                isExit = c.isExit();
            } catch (NyabotException e) {
                ui.showMessage(e.getMessage());
            } finally {
                ui.getLine();
            }
        }

    }

    public String tryLoad() {
        try {
            return new LoadCommand().execute(this.taskList, ui, storage, scheduler);
        } catch (NyabotException e) {
            return e.getMessage();
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(this.taskList, ui, storage, scheduler);

        } catch (NyabotException e) {
            return ui.showMessage(e.getMessage());
        }
    }

    public boolean isExit(String input) {
        try {
            Command c = Parser.parse(input);
            return c.isExit();

        } catch (NyabotException e) {
            return false;
        }
    }
}
