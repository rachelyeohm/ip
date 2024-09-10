package task;

import components.Parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

public class Scheduler {

    private HashMap<String, TaskList> schedule = new HashMap<>();

    public void addTask(Task task, LocalDateTime date, boolean isStart) {
        String key = date.toLocalDate().toString() + (isStart ? " s" : " e");
        if (schedule.containsKey(key)) {
            schedule.get(key).addTask(task);
        } else {
            TaskList tasks = new TaskList();
            tasks.addTask(task);
            schedule.put(key, tasks);
        }
    }

    public String getScheduleOfDay(LocalDate date) {
        String startKey = date.toString() + " s";
        String endKey = date.toString() + " e";
        String output = "The following tasks are starting today: \n";
        TaskList tasksStartingOnDay = schedule.get(startKey) == null ? new TaskList()
                : schedule.get(startKey);
        TaskList tasksEndingOnDay = schedule.get(endKey) == null ? new TaskList()
                : schedule.get(endKey);
        output += tasksStartingOnDay.displayTasks() + "\n";
        output += "The following tasks are ending today: \n";
        output += tasksEndingOnDay.displayTasks() + "\n";
        return output;
    }
}
