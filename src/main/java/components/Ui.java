package components;

import java.util.Scanner;

/**
 * Represents a handler for actions that involves interacting with the user.
 */
public class Ui {

    private final Scanner scanner;
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns a long horizontal line to display.
     *
     * @return String of long horizontal line.
     */
    public String getLine() {
        return "----------------------------" +
                "--------------------";
    }

    /**
     * Returns a string of the output message with horizontal lines above and below.
     *
     * @param str Message to be outputted to user.
     * @return Message with horizontal lines above and below.
     */
    public String prettifyString(String str) {
        return getLine() + "\n" + str + "\n" + getLine();
    }

    /**
     * Outputs a welcome message to the user.
     */
    public String showWelcome() {
        return
                "Hello! I'm Nyabot. What can I do for you, nya?.\n" +
                        "For deadlines and events, dates should be written in dd/MM/yyyy HH:mm. " +
                        "For example, you can write 20/09/2024 13:00.\n" +
                        "For schedules, dates should be written in dd/MM/yyyy (without the time)."
                ;
    }

    /**
     * Outputs message to the user with horizontal lines above and below.
     *
     * @param str Message to be shown to user.
     */
    public String showMessage(String str) {
        System.out.println(prettifyString(str));
        return str;
    }

    /**
     * Returns string of user input.
     * @return User input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }
}

