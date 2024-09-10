package task;

import components.Parser;
import exception.NyabotParseException;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

/**
 * Represents a type of task called a deadline.
 */

public class Deadline extends Task {

    public Deadline(String task, LocalDateTime deadline) throws NoSuchElementException {
        super(task);
        super.setEndTime(deadline);

    }

    public Deadline(String task, boolean isDone, LocalDateTime deadline) {
        super(task, isDone);
        super.setEndTime(deadline);
    }

    /**
     * Returns the type of the task, a deadline.
     *
     * @return TaskType enum object signifying the task type is a deadline.
     */
    @Override
    public TaskType getTaskType() {
        return TaskType.DEADLINE;
    }

    /**
     * Throws an exception when the start time is asked for, as a deadline does not
     * have a start time.
     * @return LocalDateTime object.
     * @throws NoSuchElementException Everytime, since a deadline does not have a start time.
     */
    @Override
    public LocalDateTime getStartTime() throws NoSuchElementException {
        throw new NoSuchElementException("A deadline does not have a start time");
    }

    /**
     * Throws an exception when trying to set the start time, as a deadline does not
     * have a start time.
     * @param startTime Start time to set for the deadline.
     * @throws NoSuchElementException Everytime, since a deadline does not have a start time.
     */
    @Override
    public void setStartTime(LocalDateTime startTime) throws NoSuchElementException {
        throw new NoSuchElementException("Cannot set start time on a deadline");
    }

    @Override
    public String toString() {
        String status = super.isDone() ? "[D][X]" : "[D][ ]";
        try {
            return status + " " + super.getTaskName() + " (by: " +
                    Parser.convertDateTimeToOutput(super.getEndTime()) + ")";
        } catch (NyabotParseException e) {
            return status + " " + super.getTaskName() + " (by: " +
                    super.getEndTime() + ")";
        }

    }
}
