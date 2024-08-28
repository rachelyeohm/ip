package components;

import exception.NyabotException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {
    @Test
    public void createDirectory_expectedBehavior() throws NyabotException {
        Ui ui = new Ui();
        Storage storage = new Storage("./data/Nyabot.txt", ui);
        File directory = new File(storage.getDirectory());
        assertTrue(directory.isDirectory());
    }

    @Test
    public void getStartTime_exceptionThrown() {
        try {
            Task task = new ToDo("1");
            task.getStartTime();
        } catch (NoSuchElementException e) {
            assertEquals("A to-do does not have a start time", e.getMessage());
        }

    }

    @Test
    public void getEndTime_exceptionThrown() {
        try {
            Task task = new ToDo("1");
            task.getEndTime();
        } catch (NoSuchElementException e) {
            assertEquals("A to-do does not have an end time", e.getMessage());
        }

    }

    @Test
    public void setStartTime_exceptionThrown() {
        try {
            Task task = new ToDo("1");
            task.getStartTime();
        } catch (NoSuchElementException e) {
            assertEquals("A to-do does not have an start time", e.getMessage());
        }

    }

    @Test
    public void setEndTime_exceptionThrown() {
        try {
            Task task = new ToDo("1");
            task.getStartTime();
        } catch (NoSuchElementException e) {
            assertEquals("A to-do does not have an start time", e.getMessage());
        }

    }

}
