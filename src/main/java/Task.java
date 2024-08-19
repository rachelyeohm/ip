public class Task {

    private boolean done;
    private String taskname;

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

    public void setDone(boolean status) {
        this.done = status;
    }

    @Override
    public String toString() {
        return this.done ?  "[X] " + this.taskname : "[ ] " + this.taskname;
    }
}
