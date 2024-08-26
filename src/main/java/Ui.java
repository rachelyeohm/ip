import java.util.Scanner;

public class Ui {

    private Scanner scanner;
    public Ui() {
        this.scanner = new Scanner(System.in);
    }
    public String getLine() {
        return "------------------------------------------------";
    }

    public String prettifyString(String str) {
        return getLine() + "\n" + str + "\n" + getLine();
    }

    public void showWelcome() {
        System.out.println(prettifyString(
                "Hello! I'm Nyabot. What can I do for you, nya?.\n" +
                        "For deadlines and events, dates should be written in yyyy-MM-dd HH:mm. " +
                        "For example, you can write 2019-12-27 18:00."));
    }

    public void showMessage(String str) {
        System.out.println(prettifyString(str));
    }

    public String readCommand() {
        return scanner.nextLine();
    }
}

