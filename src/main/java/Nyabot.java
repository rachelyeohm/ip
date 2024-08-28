
import command.Command;
import command.LoadCommand;
import components.Parser;
import components.Storage;
import components.Ui;
import exception.NyabotException;
import task.TaskList;

/**
 * Represents a chatbot instance.
 *
 */
public class Nyabot {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    private Parser parser;

    public Nyabot(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }
    public static void main(String[] args) {
        Ui ui = new Ui();
        try {
            TaskList taskList = new TaskList();

            Storage storage = new Storage("./data/Nyabot.txt", ui);
            Nyabot nyabot = new Nyabot(taskList, ui, storage);
            nyabot.run();
        } catch (NyabotException e) {
            ui.showMessage(e.getMessage());
        }

    }

    /**
     * Returns nothing.
     */
    public void run() {

        ui.showWelcome();
        try {
            new LoadCommand().execute(this.taskList, ui, storage);
        } catch (NyabotException e) {
            ui.showMessage(e.getMessage());
        }
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(this.taskList, ui, storage);
                isExit = c.isExit();
            } catch (NyabotException e) {
                ui.showMessage(e.getMessage());
            } finally {
                ui.getLine();
            }
        }

    }







}
