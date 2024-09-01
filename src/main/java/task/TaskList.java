package task;

import components.Parser;
import exception.NyabotParseException;

import java.util.ArrayList;


public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<Task>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void replaceTasks(TaskList taskList) {
        this.tasks = taskList.tasks;
    }

    public void deleteTask(Task task) {
        tasks.remove(task);
    }
    public void deleteTask(int taskNumber) {

        tasks.remove(taskNumber);
    }

    public String displayTasks() {
        StringBuilder textListString = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            textListString.append((i + 1)).append(". ").append(tasks.get(i));
            if (i < tasks.size()-1) {
                textListString.append("\n");
            }

        }
        return textListString.toString();
    }

    public String displayTasksSaveable() throws NyabotParseException {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            switch (task.getTaskType()) {
            case TODO:
                sb.append("T | ").append((task.isDone() ? "1" : "0") + " | " + task.getTaskName() + "\n");
                break;
            case DEADLINE:
                String endTime = Parser.convertDateToOutput(task.getEndTime());
                sb.append("D | ").append((task.isDone() ? "1" : "0") + " | " + task.getTaskName())
                        .append(" | ").append(endTime).append("\n");
                break;
            case EVENT:
                String start = Parser.convertDateToOutput(task.getStartTime());
                String end = Parser.convertDateToOutput(task.getEndTime());
                sb.append("E | ").append((task.isDone() ? "1" : "0") + " | " + task.getTaskName())
                        .append(" | ").append(start)
                        .append(" | ").append(end).append("\n");
                break;
            }
        }
        return sb.toString();
    }

    public int getNumTasks() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }
}
