import java.util.NoSuchElementException;

public class ToDo extends Task {
    public ToDo(String task) {
        super(task);

    }

    @Override
    public String getStartTime() throws NoSuchElementException {
        throw new NoSuchElementException("A to-do does not have an start time");
    }

    @Override
    public String getEndTime() throws NoSuchElementException {
        throw new NoSuchElementException("A to-do does not have an end time");
    }

    @Override
    public void setStartTime(String startTime) throws NoSuchElementException {
        throw new NoSuchElementException("Cannot set start time on a to-do");
    }

    @Override
    public void setEndTime(String endTime) throws NoSuchElementException {
        throw new NoSuchElementException("Cannot set end time on a to-do");
    }

    @Override
    public String toString() {
        String status = super.getDone() ? "[T][X]" : "[T][ ]";
        return status + " " + super.getTaskName() ;
    }

}
