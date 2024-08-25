
abstract public class Task {

    private boolean isDone;
    private String taskName;

    private String startTime;
    private String endTime;

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

    public String getStartTime() {return this.startTime;}

    public String getEndTime() {return this.endTime;}

    public void setStartTime(String startTime) {this.startTime = startTime;}

    public void setEndTime(String endTime) {this.endTime = endTime;}

    public void setIsDone(boolean status) {
        this.isDone = status;
    }

    @Override
    public String toString() {
        return this.isDone ?  "[X] " + this.taskName : "[ ] " + this.taskName;
    }
}
