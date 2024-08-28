package task;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

/**
 * Represents a type of task called a todo.
 */
public class ToDo extends Task {
    public ToDo(String task) {
        super(task);
    }

    public ToDo(String task, boolean isDone) {
        super(task, isDone);
    }

    /**
     * Returns the type of the task, a to-do.
     *
     * @return TaskType enum object signifying the task type is a to-do.
     */
    @Override
    public TaskType getTaskType() {
        return TaskType.TODO;
    }

    /**
     * Throws an exception when the start time is asked for, as a to-do does not
     * have a start time.
     * @return LocalDateTime object.
     * @throws NoSuchElementException Everytime, since a to-do does not have a start time.
     */
    @Override
    public LocalDateTime getStartTime() throws NoSuchElementException {
        throw new NoSuchElementException("A to-do does not have an start time");
    }

    /**
     * Throws an exception when the start time is asked for, as a to-do does not
     * have an end time.
     * @return LocalDateTime object.
     * @throws NoSuchElementException Everytime, since a to-do does not have an end time.
     */
    @Override
    public LocalDateTime getEndTime() throws NoSuchElementException {
        throw new NoSuchElementException("A to-do does not have an end time");
    }

    /**
     * Throws an exception when trying to set the start time, as a to-do does not
     * have a start time.
     * @param startTime Start time to set for the to-do.
     * @throws NoSuchElementException Everytime, since a to-do does not have a start time.
     */
    @Override
    public void setStartTime(LocalDateTime startTime) throws NoSuchElementException {
        throw new NoSuchElementException("Cannot set start time on a to-do");
    }

    /**
     * Throws an exception when trying to set the end time, as a to-do does not
     * have an end time.
     * @param endTime End time to set for the to-do.
     * @throws NoSuchElementException Everytime, since a to-do does not have an end time.
     */
    @Override
    public void setEndTime(LocalDateTime endTime) throws NoSuchElementException {
        throw new NoSuchElementException("Cannot set end time on a to-do");
    }

    @Override
    public String toString() {
        String status = super.isDone() ? "[T][X]" : "[T][ ]";
        return status + " " + super.getTaskName() ;
    }

}
