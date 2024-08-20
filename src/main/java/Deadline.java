import java.util.NoSuchElementException;

public class Deadline extends Task {

    public Deadline(String task, String deadline) {
        super(task);
        super.setEndTime(deadline);

    }

    @Override
    public String getStartTime() throws NoSuchElementException {
        throw new NoSuchElementException("A deadline does not have a start time");
    }
    @Override
    public void setStartTime(String startTime) throws NoSuchElementException {
        throw new NoSuchElementException("Cannot set start time on a deadline");
    }

    @Override
    public String toString() {
        String status = super.getDone() ? "[D][X]" : "[D][ ]";
        return status + " " + super.getTaskName() + " (by: " + super.getEndTime() + ")";
    }
}
