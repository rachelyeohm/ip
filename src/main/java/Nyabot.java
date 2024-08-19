
import java.util.Scanner;

public class Nyabot {
    public static void main(String[] args) {

        String hellostatement = "Hello! I'm Nyabot. What can I do for you, nya?";
        String byestatement = "Bye. Hope to see you again, nya!";
        System.out.println(Nyabot.prettifyString(hellostatement));

        Scanner scanner = new Scanner(System.in);
        while(true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }
            System.out.println(Nyabot.prettifyString(input));
        }
        System.out.println(Nyabot.prettifyString(byestatement));
    }

    public static String prettifyString(String str) {
        String horizontalline = "_".repeat(Math.max(40, str.length()));
        return "\t" + horizontalline + "\n\t" + str + "\n\t" +
                horizontalline;
    }
}
