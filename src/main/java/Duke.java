import java.util.Scanner;

public class Duke {
    public static String horizontalLine = "__________________________________________________";

    public static void startUp() {
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm ByteBrew!");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);
    }

    public static void shutDown() {
        System.out.println(horizontalLine);
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println(horizontalLine);
    }

    public static void listTasks(Task[] taskArray, int taskCounter) {
        if (taskCounter == 0) {
            System.out.println(horizontalLine);
            System.out.println("There are no tasks!");
            System.out.println(horizontalLine);
            return;
        }

        System.out.println(horizontalLine);
        System.out.println("Here's the task list: ");

        for (int i = 0; i < taskCounter; i++) {
            Task t = taskArray[i];
            System.out.println((i + 1) + ".[" + t.getStatusIcon()+ "] " + t.getDescription());
        }
        
        System.out.println(horizontalLine);
    }

    public static void markTask(Task[] taskArray, int taskNumber, boolean isDone) {
        System.out.println(horizontalLine);
        Task taskToEdit = taskArray[taskNumber];

        if (taskToEdit.isDone == isDone) {
            System.out.println("Task is already marked as " + (isDone ? "done!" : "undone!"));
            System.out.println(horizontalLine);
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

        System.out.println("[" + taskToEdit.getStatusIcon() + "] " + taskToEdit.getDescription());
        System.out.println(horizontalLine);
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
                listTasks(taskArray, taskCounter);
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
                Task taskToAdd = new Task(inputLine);
                taskArray[taskCounter] = taskToAdd;
                taskCounter += 1;

                System.out.println(horizontalLine);
                System.out.println("Added task: " + taskToAdd.getDescription());
                System.out.println("Number of Tasks: " + taskCounter);
                System.out.println(horizontalLine);
            }

            inputLine = in.nextLine();
        }
    }
}
