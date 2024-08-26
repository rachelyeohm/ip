import java.time.LocalDateTime;
import java.util.NoSuchElementException;

public class Event extends Task {

    public Event(String task, LocalDateTime startTime, LocalDateTime endTime) {
        super(task);
        super.setStartTime(startTime);
        super.setEndTime(endTime);
    }

    public Event(String task, boolean isDone, LocalDateTime startTime, LocalDateTime endTime) {
        super(task, isDone);
        super.setStartTime(startTime);
        super.setEndTime(endTime);
    }


    @Override
    public Nyabot.TaskType getTaskType() {
        return Nyabot.TaskType.EVENT;
    }


    @Override
    public String toString() {
        String status = super.isDone() ? "[E][X]" : "[E][ ]";
        try {
            return status + " " + super.getTaskName()
                    + " (from: " + Parser.convertDateToOutput(super.getStartTime()) + " to: "
                    + Parser.convertDateToOutput(super.getEndTime()) + ")";
        } catch (NyabotParseException e) {
            return status + " " + super.getTaskName()
                    + " (from: " + super.getStartTime() + " to: "
                    + super.getEndTime() + ")";
        }

    }

}
