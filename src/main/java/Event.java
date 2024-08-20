import java.util.NoSuchElementException;

public class Event extends Task {

    public Event(String task, String startTime, String endTime) {
        super(task);
        super.setStartTime(startTime);
        super.setEndTime(endTime);

    }


    @Override
    public String toString() {
        String status = super.getDone() ? "[E][X]" : "[E][ ]";
        return status + " " + super.getTaskName()
                + " (from: " + super.getStartTime() + " to: "
                + super.getEndTime() + ")";
    }

}
