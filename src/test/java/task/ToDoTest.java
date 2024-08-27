package task;

import components.Storage;
import components.Ui;
import exception.NyabotException;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ToDoTest {
    @Test
    public void createDirectory_correct() throws NyabotException {
        Ui ui = new Ui();
        Storage storage = new Storage("./data/Nyabot.txt", ui);
        File directory = new File(storage.getDirectory());
        assertTrue(directory.isDirectory());
    }

    @Test
    public void createDirectory_wrongDirectory() {
        try {
            Ui ui = new Ui();
            Storage storage = new Storage("", ui);
            File directory = new File(storage.getDirectory());
        } catch (NyabotException e) {
            assertEquals("There was an issue with creating the " +
                    "correct directory to save the tasks txt file in, nya.", e.getMessage());
        }

    }
}
