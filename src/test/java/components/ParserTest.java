package components;
import command.AddCommand;
import exception.NyabotException;
import org.junit.jupiter.api.Test;
import task.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void parseNewTask_parseTodoTask_Correct() throws NyabotException {
        AddCommand command = (AddCommand) Parser.parseNewTask("todo 1", Task.TaskType.TODO);
        assertEquals("[T][ ] 1", command.getTask().toString());
    }
    @Test
    public void parseNewTask_parseTodoTask_WithSpaceInBetweenTaskName() throws NyabotException {
        AddCommand command = (AddCommand) Parser.parseNewTask("todo 1 2 3", Task.TaskType.TODO);
        assertEquals("[T][ ] 1 2 3", command.getTask().toString());
    }

    @Test
    public void parseNewTask_parseTodoTask_WithSpaceBeforeAndAfter() throws NyabotException {
        AddCommand command = (AddCommand) Parser.parseNewTask("todo    1 2 3  ", Task.TaskType.TODO);
        assertEquals("[T][ ] 1 2 3", command.getTask().toString());
    }

    @Test
    public void parseNewTask_parseDeadlineTask_Correct() throws NyabotException {
        AddCommand command = (AddCommand) Parser.parseNewTask(
                "deadline 1 /by 2019-12-27 18:00", Task.TaskType.DEADLINE);
        assertEquals("[D][ ] 1 (by: 12/27/2019 06:00PM)", command.getTask().toString());
    }
    @Test
    public void parseNewTask_parseDeadlineTask_WithSpaceInBetweenTaskName() throws NyabotException {
        AddCommand command = (AddCommand) Parser.parseNewTask(
                "deadline 123 456 /by 2019-12-27 18:00",
                Task.TaskType.DEADLINE);
        assertEquals("[D][ ] 123 456 (by: 12/27/2019 06:00PM)", command.getTask().toString());
    }

    @Test
    public void parseNewTask_parseDeadlineTask_WithSpaceBeforeAndAfter() throws NyabotException {
        AddCommand command = (AddCommand) Parser.parseNewTask(
                "deadline   123   /by   2019-12-27 18:00", Task.TaskType.DEADLINE);
        assertEquals("[D][ ] 123 (by: 12/27/2019 06:00PM)",
                command.getTask().toString());
    }

    @Test
    public void parseNewTask_parseDeadlineTask_NoByCommand() throws NyabotException {
        try {
            AddCommand command = (AddCommand) Parser.parseNewTask("deadline 123", Task.TaskType.DEADLINE);

        } catch (Exception e) {
            assertEquals("A valid /by command is required, nya!", e.getMessage());
        }

    }

    @Test
    public void parseNewTask_parseDeadlineTask_DateWronglyFormatted() throws NyabotException {
        try {
            AddCommand command = (AddCommand) Parser.parseNewTask(
                    "deadline 123 /by qo0q874", Task.TaskType.DEADLINE);

        } catch (Exception e) {
            assertEquals("Nya, we can't parse your date!", e.getMessage());
        }
    }


    @Test
    public void parseNewTask_parseEventTask_Correct() throws NyabotException {
        AddCommand command = (AddCommand) Parser.parseNewTask(
                "event 1 /from 2019-12-27 18:00 /to 2019-12-27 19:00", Task.TaskType.EVENT);
        assertEquals("[E][ ] 1 (from: 12/27/2019 06:00PM to: 12/27/2019 07:00PM)",
                command.getTask().toString());
    }
    @Test
    public void parseNewTask_parseEventTask_WithSpaceInBetweenTaskName() throws NyabotException {
        AddCommand command = (AddCommand) Parser.parseNewTask(
                "event 1 2 3 /from 2019-12-27 18:00 /to 2019-12-27 19:00",
                Task.TaskType.EVENT);
        assertEquals("[E][ ] 1 2 3 (from: 12/27/2019 06:00PM " +
                "to: 12/27/2019 07:00PM)", command.getTask().toString());
    }

    @Test
    public void parseNewTask_parseEventTask_WithSpaceBeforeAndAfter() throws NyabotException {
        AddCommand command = (AddCommand) Parser.parseNewTask(
                "event   1   /from   2019-12-27 18:00   /to   2019-12-27 19:00",
                Task.TaskType.EVENT);
        assertEquals("[E][ ] 1 (from: 12/27/2019 06:00PM " +
                "to: 12/27/2019 07:00PM)", command.getTask().toString());
    }

    @Test
    public void parseNewTask_parseEventTask_NoFromCommand() throws NyabotException {
        try {
            AddCommand command = (AddCommand) Parser.parseNewTask(
                    "event 1 /to 2019-12-27 19:00",
                    Task.TaskType.EVENT);
        } catch (Exception e) {
            assertEquals("A valid /from and /to command is required, nya!", e.getMessage());
        }

    }

    @Test
    public void parseNewTask_parseEventTask_NoToCommand() throws NyabotException {
        try {
            AddCommand command = (AddCommand) Parser.parseNewTask(
                    "event 1 /from 2019-12-27 19:00",
                    Task.TaskType.EVENT);
        } catch (Exception e) {
            assertEquals("A valid /from and /to command is required, nya!", e.getMessage());
        }

    }

    @Test
    public void parseNewTask_parseEventTask_DateWronglyFormatted() throws NyabotException {
        try {
            AddCommand command = (AddCommand) Parser.parseNewTask(
                    "event 123 /from qo0q874 /to kdlk", Task.TaskType.EVENT);

        } catch (Exception e) {
            assertEquals("Nya, we can't parse your date!", e.getMessage());
        }
    }

    @Test
    public void parseNewTask_parseEventTask_FromAndToInverted() throws NyabotException {

        AddCommand command = (AddCommand) Parser.parseNewTask(
                "event 123 /to 2019-12-27 20:00 /from 2019-12-27 19:00", Task.TaskType.EVENT);
        assertEquals("[E][ ] 123 (from: 12/27/2019 07:00PM " +
                "to: 12/27/2019 08:00PM)", command.getTask().toString());

    }

    @Test
    public void parseNewTask_parseEventTask_StartDateAfterEndDate() throws NyabotException {
        try {
            AddCommand command = (AddCommand) Parser.parseNewTask(
                    "event 1 /from 2019-12-27 19:00 /to 2019-12-27 18:00",
                    Task.TaskType.EVENT);
        } catch (Exception e) {
            assertEquals("Start date cannot be after end date!", e.getMessage());
        }


    }



}
