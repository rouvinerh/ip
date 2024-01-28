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

    public static void shutDown() {
        String farewell = horizontalLine
                + "Bye. Hope to see you again soon!\n"
                + horizontalLine;
        System.out.print(farewell);
    }

    public static void printTasks(String[] taskArray, int taskCounter) {
        System.out.print(horizontalLine);
        System.out.println("Here's the task list: ");

        for (int i = 0; i < taskCounter; i++) {
            System.out.println((i + 1) + ". " + taskArray[i]);
        }
        System.out.print(horizontalLine);
    }
    public static void main(String[] args) {
        startUp();

        Scanner in = new Scanner(System.in);
        String inputLine = in.nextLine();
        String[] taskArray = new String[100];
        int taskCounter = 0;

        while (true) {
            if (inputLine.equals("bye")) {
                shutDown();
                return;
            }

            else if (inputLine.equals("list")) {
                printTasks(taskArray, taskCounter);
            }

            else {
                taskArray[taskCounter] = inputLine;
                taskCounter += 1;
                System.out.print(horizontalLine);
                System.out.println("Added: " + inputLine);
                System.out.println("Number of Tasks: " + taskCounter);
                System.out.print(horizontalLine);
            }
            inputLine = in.nextLine();
        }
    }
}
