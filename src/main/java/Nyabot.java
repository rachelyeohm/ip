
import java.util.ArrayList;
import java.util.Scanner;

public class Nyabot {

    private static ArrayList<String> textlist = new ArrayList<String>();
    public static void main(String[] args) {

        String hellostatement = "Hello! I'm Nyabot. What can I do for you, nya?";
        String byestatement = "Bye. Hope to see you again, nya!";
        System.out.println(Nyabot.prettifyString(hellostatement));

        Scanner scanner = new Scanner(System.in);
        while(true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println(displaylist());
                continue;
            }
            Nyabot.textlist.add(input);
            System.out.println(Nyabot.prettifyString("added: " + input));
        }
        System.out.println(Nyabot.prettifyString(byestatement));
    }

    public static String prettifyString(String str) {
        String horizontalline = "_".repeat(70);
        return "\t" + horizontalline + "\n\t" + str + "\n\t" +
                horizontalline;
    }

    public static String displaylist() {
        StringBuilder textliststring = new StringBuilder();
        for (int i = 0 ; i < Nyabot.textlist.size(); i++) {
            if (i > 0) {
                textliststring.append("\t");
            }
            textliststring.append((i + 1)).append(". ").append(Nyabot.textlist.get(i));
            if (i < Nyabot.textlist.size()-1) {
                textliststring.append("\n");
            }

        }
        return prettifyString(textliststring.toString());
    }
}
