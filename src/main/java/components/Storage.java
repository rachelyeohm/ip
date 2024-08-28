package components;

import exception.NyabotException;
import exception.NyabotFileNotFoundException;
import exception.NyabotIOException;
import exception.NyabotParseException;
import task.Deadline;
import task.Event;
import task.TaskList;
import task.ToDo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Represents a handler that loads and saves user data.
 */
public class Storage {

    private String path;
    private String directory;
    public Storage(String path, Ui ui) throws NyabotIOException {
        this.path = path;
        createDirectory(path, ui);
    }

    /**
     * Returns nothing. File path in variable path should end in .txt
     *
     * @param path File path for text file for chatbot history.
     * @param ui Ui object to output messages to user.
     * @throws NyabotIOException If there are unexpected issues in creating the correct directory
     * to store the text file in.
     */
    public void createDirectory(String path, Ui ui) throws NyabotIOException {
        try {
            Files.createDirectories(Paths.get(path.substring(0, path.lastIndexOf("/"))));
            this.directory = path.substring(0, path.lastIndexOf("/"));
            ui.showMessage("Directory was successfully created to store the txt file nya.");
        } catch (IOException | StringIndexOutOfBoundsException e) {
            throw new NyabotIOException("There was an issue with creating the correct directory " +
                    "to save the tasks txt file in, nya.");
        }
    }

    /**
     * Returns the path of the folder where the txt file is stored in, without the .txt part.
     * @return Path of directory where chatbot history txt file is stored.
     */
    public String getDirectory() {
        return this.directory;
    }

    /**
     * Returns a TaskList object consisting of tasks from the txt file chatbot history.
     *
     * @return TaskList object of tasks from the loaded chatbot history.
     * @throws NyabotException If unexpected exceptions occur. For example, NyabotFileNotFoundException
     * is thrown if there is no txt file history. NyabotIOException is thrown if the txt
     * file is unreadable.
     */
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

    /**
     * Returns nothing. Takes in a TaskList object of tasks and saves it into a txt file, in the
     * directory specified earlier.
     *
     * @param taskList TaskList object of tasks the user has inputted into the chatbot.
     * @throws NyabotFileNotFoundException If the file to save in cannot be found.
     * @throws NyabotParseException If dates in tasks cannot be converted to strings.
     */
    public void save(TaskList taskList) throws NyabotFileNotFoundException, NyabotParseException {
        try {
            PrintWriter writer = new PrintWriter(path);
            writer.print(taskList.displayTasksSaveable());
            writer.close();
        } catch (FileNotFoundException e) {
            throw new NyabotFileNotFoundException("Unyable to save. There was an nyerror in finding the file to save in :(");
        }
    }

}
