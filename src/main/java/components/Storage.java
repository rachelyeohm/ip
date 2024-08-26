package components;

import exception.NyabotException;
import exception.NyabotFileNotFoundException;
import exception.NyabotIOException;
import task.Deadline;
import task.Event;
import task.TaskList;
import task.ToDo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Storage {

    private String path;
    public Storage(String path, Ui ui) throws NyabotIOException {
        this.path = path;
        try {
            Files.createDirectories(Paths.get("./data"));
            ui.showMessage("'./data' directory was successfully created to store the txt file nya.");
        } catch (IOException e) {
            throw new NyabotIOException("There was an issue with creating the correct directory " +
                    "to save the tasks txt file in, nya.");
        }


    }

    public TaskList load() throws NyabotException {
        TaskList taskList = new TaskList();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = br.readLine();

            while (line != null) {
                String[] taskArray = line.split(" \\| ");
                switch(taskArray[0].trim()) {
                case "T":
                    taskList.addTask(new ToDo(taskArray[2], taskArray[1].equals("1")));
                    break;
                case "D":
                    taskList.addTask(new Deadline(taskArray[2],
                            taskArray[1].equals("1"),
                            Parser.convertTxtInputToDate(taskArray[3].trim())));
                    break;
                case "E":
                    taskList.addTask(new Event(taskArray[2], taskArray[1].equals("1"),
                            Parser.convertTxtInputToDate(taskArray[3].trim()),
                            Parser.convertTxtInputToDate(taskArray[4].trim())));
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
        return taskList;

    }

    public void save(TaskList taskList) throws NyabotException {
        try {
            PrintWriter writer = new PrintWriter(path);
            writer.println(taskList.displayTasksSaveable());
            writer.close();
        } catch (FileNotFoundException e) {
            throw new NyabotFileNotFoundException("Unyable to save. There was an nyerror in finding the file to save in :(");
        }
    }

}
