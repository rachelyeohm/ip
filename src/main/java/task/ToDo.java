package task;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

public class ToDo extends Task {
    public ToDo(String task) {
        super(task);
    }

    public ToDo(String task, boolean isDone) {
        super(task, isDone);
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.TODO;
    }

    @Override
    public LocalDateTime getStartTime() throws NoSuchElementException {
        throw new NoSuchElementException("A to-do does not have an start time");
    }

    @Override
    public LocalDateTime getEndTime() throws NoSuchElementException {
        throw new NoSuchElementException("A to-do does not have an end time");
    }

    @Override
    public void setStartTime(LocalDateTime startTime) throws NoSuchElementException {
        throw new NoSuchElementException("Cannot set start time on a to-do");
    }

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
