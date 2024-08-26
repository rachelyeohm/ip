import java.time.LocalDateTime;

abstract public class Task {

    private boolean isDone;
    private String taskName;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    abstract public Nyabot.TaskType getTaskType();

    public String getTaskName() {
        return this.taskName;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public LocalDateTime getStartTime() {return this.startTime;}

    public LocalDateTime getEndTime() {return this.endTime;}

    public void setStartTime(LocalDateTime startTime) {this.startTime = startTime;}

    public void setEndTime(LocalDateTime endTime) {this.endTime = endTime;}

    public void setIsDone(boolean status) {
        this.isDone = status;
    }

    @Override
    public String toString() {
        return this.isDone ?  "[X] " + this.taskName : "[ ] " + this.taskName;
    }
}
