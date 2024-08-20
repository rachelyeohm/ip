public class Task {

    private boolean done;
    private String taskname;

    private String startTime;
    private String endTime;

    public Task(String taskname) {
        this.taskname = taskname;
        this.done = false;
    }

    public String getTaskName() {
        return this.taskname;
    }

    public boolean getDone() {
        return this.done;
    }

    public String getStartTime() {return this.startTime;}

    public String getEndTime() {return this.endTime;}

    public void setStartTime(String startTime) {this.startTime = startTime;}

    public void setEndTime(String endTime) {this.endTime = endTime;}

    public void setDone(boolean status) {
        this.done = status;
    }

    @Override
    public String toString() {
        return this.done ?  "[X] " + this.taskname : "[ ] " + this.taskname;
    }
}
