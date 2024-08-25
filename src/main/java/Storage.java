import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

public class Storage {

    private String path;
    public Storage(String path, Ui ui) {
        this.path = path;
        try {
            Files.createDirectories(Paths.get("./data"));
            System.out.println(prettifyString("'./data' directory was successfully created to store the txt file nya."));
        } catch (IOException e) {
            System.out.println(prettifyString("There was an issue with creating the correct directory " +
                    "to save the tasks txt file in, nya."));
        }
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

    public void save(TaskList taskList) throws NyabotFileNotFoundException {
        try {
            PrintWriter writer = new PrintWriter(path);
            writer.println(taskList.displayTasksSaveable());
            writer.close();
        } catch (FileNotFoundException e) {
            throw new NyabotFileNotFoundException("Unyable to save. There was an nyerror in finding the file to save in :(");
        }
    }

}
