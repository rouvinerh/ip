import java.util.Scanner;

public class Duke {
    public static String HORIZONTAL_LINE = "__________________________________________________";

    public static void startUp() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm ByteBrew!");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void shutDown() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void listTasks(Task[] tasks, int taskCount) {
        if (taskCount == 0) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("There are no tasks!");
            System.out.println(HORIZONTAL_LINE);
            return;
        }

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here's the task list: ");

        for (int i = 0; i < taskCount; i++) {
            Task t = tasks[i];
            System.out.println((i + 1) + ".[" + t.getStatusIcon()+ "] " + t.getDescription());
        }

        System.out.println(HORIZONTAL_LINE);
    }

    public static void markTask(Task[] tasks, int taskIndex, boolean isDone) {
        System.out.println(HORIZONTAL_LINE);
        Task taskToEdit = tasks[taskIndex];

        if (taskToEdit.isDone == isDone) {
            System.out.println("Task is already marked as " + (isDone ? "done!" : "undone!"));
            System.out.println(HORIZONTAL_LINE);
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
        System.out.println(HORIZONTAL_LINE);
    }
    public static void main(String[] args) {
        startUp();

        Scanner in = new Scanner(System.in);
        String inputLine = in.nextLine();
        Task[] tasks = new Task[100];
        int taskCount = 0;

        while (true) {
            String[] words = inputLine.split(" ");

            if (inputLine.equals("bye")) {
                shutDown();
                return;
            }

            else if (words[0].equals("list")) {
                listTasks(tasks, taskCount);
            }

            else if (words[0].equals("unmark")) {
                int taskIndex = Integer.parseInt(words[1]);
                markTask(tasks, taskIndex - 1, false);
            }

            else if (words[0].equals("mark")) {
                int taskIndex = Integer.parseInt(words[1]);
                markTask(tasks, taskIndex - 1, true);
            }

            else {
                Task taskToAdd = new Task(inputLine);
                tasks[taskCount] = taskToAdd;
                taskCount += 1;

                System.out.println(HORIZONTAL_LINE);
                System.out.println("Added task: " + taskToAdd.getDescription());
                System.out.println("Number of Tasks: " + taskCount);
                System.out.println(HORIZONTAL_LINE);
            }
            inputLine = in.nextLine();
        }
    }
}
