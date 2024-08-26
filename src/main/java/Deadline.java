import java.time.LocalDateTime;
import java.util.NoSuchElementException;


public class Deadline extends Task {

    public Deadline(String task, LocalDateTime deadline) throws NoSuchElementException {
        super(task);
        super.setEndTime(deadline);

    }

    public Deadline(String task, boolean isDone, LocalDateTime deadline) {
        super(task, isDone);
        super.setEndTime(deadline);
    }

    @Override
    public Nyabot.TaskType getTaskType() {
        return Nyabot.TaskType.DEADLINE;
    }

    @Override
    public LocalDateTime getStartTime() throws NoSuchElementException {
        throw new NoSuchElementException("A deadline does not have a start time");
    }
    @Override
    public void setStartTime(LocalDateTime startTime) throws NoSuchElementException {
        throw new NoSuchElementException("Cannot set start time on a deadline");
    }

    @Override
    public String toString() {
        String status = super.isDone() ? "[D][X]" : "[D][ ]";
        try {
            return status + " " + super.getTaskName() + " (by: " +
                    Parser.convertDateToOutput(super.getEndTime()) + ")";
        } catch (NyabotParseException e) {
            return status + " " + super.getTaskName() + " (by: " +
                    super.getEndTime() + ")";
        }

    }
}
