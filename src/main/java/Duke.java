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

    public static void printTasks(Task[] taskArray, int taskCounter) {
        System.out.print(horizontalLine);
        System.out.println("Here's the task list: ");

        for (int i = 0; i < taskCounter; i++) {
            Task t = taskArray[i];
            System.out.println((i + 1) + ".[" + t.getStatusIcon()+ "]" + t.getDescription());
        }
        System.out.print(horizontalLine);
    }

    public static void markTask(Task[] taskArray, int taskNumber, boolean isDone) {
        System.out.print(horizontalLine);
        Task taskToEdit = taskArray[taskNumber];
        if (taskToEdit.isDone == isDone) {
            System.out.println("Task is already marked as " + (isDone ? "done!" : "undone!"));
            System.out.print(horizontalLine);
            return;
        }
        else if (isDone) {
            System.out.println("Nice! I've marked this task as done:");
            taskToEdit.markStatus(true);
        }

        else {
            System.out.println("OK, I've marked this task as not done yet: ");
            taskToEdit.markStatus(false);
        }
        System.out.println("[" + taskToEdit.getStatusIcon() + "]" + taskToEdit.getDescription());
        System.out.print(horizontalLine);
    }
    public static void main(String[] args) {
        startUp();

        Scanner in = new Scanner(System.in);
        String inputLine = in.nextLine();
        Task[] taskArray = new Task[100];
        int taskCounter = 0;

        while (true) {
            String[] words = inputLine.split(" ");
            if (inputLine.equals("bye")) {
                shutDown();
                return;
            }

            else if (words[0].equals("list")) {
                printTasks(taskArray, taskCounter);
            }

            else if (words[0].equals("unmark")) {
                int taskNumber = Integer.parseInt(words[1]);
                markTask(taskArray, taskNumber - 1, false);
            }

            else if (words[0].equals("mark")) {
                int taskNumber = Integer.parseInt(words[1]);
                markTask(taskArray, taskNumber - 1, true);
            }

            else {
                Task t = new Task(inputLine);
                taskArray[taskCounter] = t;
                taskCounter += 1;
                System.out.print(horizontalLine);
                System.out.println("Added task: " + inputLine);
                System.out.println("Number of Tasks: " + taskCounter);
                System.out.print(horizontalLine);
            }
            inputLine = in.nextLine();
        }
    }
}
