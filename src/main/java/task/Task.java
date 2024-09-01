package task;

import java.time.LocalDateTime;

/**
 * Represents a task that the user adds in the chatbot.
 */
abstract public class Task {

    public enum TaskType {
        TODO, DEADLINE, EVENT;
    }

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

    /**
     * Returns the type of the task.
     *
     * @return TaskType enum object signifying the task type.
     */
    abstract public TaskType getTaskType();

    /**
     * Returns the name of the task.
     *
     * @return String of the name of the task.
     */
    public String getTaskName() {

        return this.taskName;
    }

    /**
     * Returns whether the task has been marked as done.
     *
     * @return Boolean representing whether the task has been marked as done.
     */
    public boolean isDone() {

        return this.isDone;
    }

    /**
     * Returns the start time of the task if applicable.
     *
     * @return LocalDateTime object representing the start time of the task.
     */
    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    /**
     * Returns the end time of the task if applicable.
     *
     * @return LocalDateTime object representing the end time of the task.
     */
    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    /**
     * Returns nothing. Sets a new start time for the task.
     *
     * @param startTime LocalDateTime object representing the new start time for the task.
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Returns nothing. Sets a new end time for the task.
     *
     * @param endTime LocalDateTime object representing the new end time for the task.
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Marks whether the task is done or not.
     *
     * @param status New status of whether the task is done or not.
     */
    public void setDone(boolean status) {

        this.isDone = status;
    }

    @Override
    public String toString() {
        return this.isDone ?  "[X] " + this.taskName : "[ ] " + this.taskName;
    }
}
