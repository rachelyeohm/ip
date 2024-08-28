package task;

import components.Parser;
import exception.NyabotException;
import exception.NyabotParseException;

import java.util.ArrayList;


public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<Task>();

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(Task task) {
        taskList.remove(task);
    }
    public void deleteTask(int taskNumber) {
        taskList.remove(taskNumber);
    }

    public String displayTasks() {
        StringBuilder textliststring = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            textliststring.append((i + 1)).append(". ").append(taskList.get(i));
            if (i < taskList.size()-1) {
                textliststring.append("\n");
            }

        }
        return textliststring.toString();
    }

    public String displayTasksSaveable() throws NyabotParseException {
        StringBuilder sb = new StringBuilder();
        for (Task task : taskList) {
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
        return taskList.size();
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }
}
