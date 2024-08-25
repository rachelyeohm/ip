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
        System.out.println(prettifyString("Hello! I'm Nyabot. What can I do for you, nya?"));
    }

    public void showMessage(String str) {
        System.out.println(prettifyString(str));
    }

    public String readMessage() {
        return scanner.nextLine();
    }
}

