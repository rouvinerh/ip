import java.util.Scanner;
public class Duke {
    public static String horizontalLine = "__________________________________________________\n";

    public static void startUp() {
        String greeting = horizontalLine
                + "Hello! I'm ByteBrew!\n"
                + "What can I do for you?\n"
                + horizontalLine;
        System.out.print(greeting);
    }

    public static void echoInput(String line) {
        String output = horizontalLine
                + line
                + "\n"
                + horizontalLine;
        System.out.print(output);
    }

    public static void shutDown() {
        String farewell = horizontalLine
                + "Bye. Hope to see you again soon!\n"
                + horizontalLine;
        System.out.print(farewell);
    }
    public static void main(String[] args) {
        startUp();

        Scanner in = new Scanner(System.in);
        String inputLine = in.nextLine();

        while (!inputLine.equals("bye")) {
            echoInput(inputLine);
            inputLine = in.nextLine();
        }
        
        shutDown();
    }
}
