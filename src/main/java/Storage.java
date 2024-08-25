import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class Storage {

    private String path;
    public Storage(String path) {
        this.path = path;
    }


    public String displayTasksSaveable(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            switch (task.getTaskType()) {
            case TODO:
                sb.append("T | ").append((task.isDone() ? "1" : "0") + " | " + task.getTaskName() + "\n");
                break;
            case DEADLINE:
                sb.append("D | ").append((task.isDone() ? "1" : "0") + " | " + task.getTaskName())
                        .append(" | ").append(task.getEndTime()).append("\n");
                break;
            case EVENT:
                sb.append("E | ").append((task.isDone() ? "1" : "0") + " | " + task.getTaskName())
                        .append(" | ").append(task.getStartTime())
                        .append(" | ").append(task.getEndTime()).append("\n");
                break;
            }
        }
        return sb.toString();
    }

    public ArrayList<Task> load() throws NyabotFileNotFoundException, NyabotIOException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = br.readLine();

            while (line != null) {
                String[] taskArray = line.split(" \\| ");
                switch(taskArray[0].trim()) {
                case "T":
                    tasks.add(new ToDo(taskArray[2], taskArray[1].equals("1")));
                    break;
                case "D":
                    tasks.add(new Deadline(taskArray[2], taskArray[1].equals("1"), taskArray[3]));
                    break;
                case "E":
                    tasks.add(new Event(taskArray[2], taskArray[1].equals("1"), taskArray[3], taskArray[4]));
                    break;
                }
                line = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException e) {
            throw new NyabotFileNotFoundException("Unyable to load history (there was no txt file history)");
        } catch (IOException e) {
            throw new NyabotIOException("Error! Unyable to read the text file.");
        }
        return tasks;

    }

    public String save(ArrayList<Task> tasks) throws NyabotFileNotFoundException {
        try {
            PrintWriter writer = new PrintWriter(path);
            writer.println(displayTasksSaveable(tasks));
            writer.close();
        } catch (FileNotFoundException e) {
            throw new NyabotFileNotFoundException("Unyable to save. There was an nyerror in finding the file to save in :(");
        }
        return "Saved successfully, nya!";

    }

}
