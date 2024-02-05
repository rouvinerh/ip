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
            System.out.println((i + 1) + ". "  + tasks[i]);
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
            System.out.println("Nice! I've marked this task as done: ");
            taskToEdit.setStatus(true);
        }

        else {
            System.out.println("OK, I've marked this task as not done yet: ");
            taskToEdit.setStatus(false);
        }

        System.out.println(taskToEdit);
        System.out.println(HORIZONTAL_LINE);
    }

    public static String removeFirstWord(String input){
        return input.split(" ",2)[1];
    }

    public static String[] getTimings(String input) {

        String[] timings = new String[2];
        String timing = input.split(" /from ", 2)[1];

        timings[0] = timing.split(" /to ", 2)[0];
        timings[1] = timing.split(" /to ", 2)[1];

        return timings;
    }

    public static void printAcknowledgement(String taskType, String description, int taskCount){
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Added " + taskType + ": " + description);
        System.out.println("Total Number of Tasks: " + taskCount);
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

            if (words[0].equals("bye")) {
                shutDown();
                return;
            }

            else if (words[0].equals("deadline")) {
                String inputWithoutTaskType = removeFirstWord(inputLine);
                String description = inputWithoutTaskType.split(" /by ",2)[0];
                String by = inputWithoutTaskType.split(" /by ", 2)[1];
                Deadline deadline = new Deadline(description, by);
                tasks[taskCount] = deadline;
                taskCount += 1;
                printAcknowledgement("Deadline", description, taskCount);
            }

            else if (words[0].equals("event")) {
                String inputWithoutTaskType = removeFirstWord(inputLine);
                String description = inputWithoutTaskType.split(" /from ",2)[0];
                String[] timings = getTimings(inputWithoutTaskType);
                Event event = new Event(description, timings[0], timings[1]);
                tasks[taskCount] = event;
                taskCount += 1;
                printAcknowledgement("Event", description, taskCount);
            }

            else if (words[0].equals("todo")) {
                String inputWithoutTaskType = removeFirstWord(inputLine);
                Todo newTodo = new Todo(inputWithoutTaskType);
                tasks[taskCount] = newTodo;
                taskCount += 1;
                printAcknowledgement("Todo", inputWithoutTaskType, taskCount);
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
                System.out.println(HORIZONTAL_LINE);
                System.out.println("Invalid command! Please try again!");
                System.out.println(HORIZONTAL_LINE);
            }
            inputLine = in.nextLine();
        }
    }
}