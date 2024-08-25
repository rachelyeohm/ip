import java.util.NoSuchElementException;

public class Event extends Task {

    public Event(String task, String startTime, String endTime) {
        super(task);
        super.setStartTime(startTime);
        super.setEndTime(endTime);
    }

    public Event(String task, boolean isDone, String startTime, String endTime) {
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
        return status + " " + super.getTaskName()
                + " (from: " + super.getStartTime() + " to: "
                + super.getEndTime() + ")";
    }

}
