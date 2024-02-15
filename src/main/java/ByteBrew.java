import java.util.Scanner;

public class ByteBrew {
    public static final String HORIZONTAL_LINE = "__________________________________________________";
    public static final int MAX_TASK_COUNT = 100;
    public static final int MIN_INPUT_LENGTH = 2;


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

    public static void listTasks(Task[] tasks, int taskCount) throws ByteBrewException {
        if (taskCount == 0) {
            throw new ByteBrewException("There is nothing in the task list!");
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

    public static void printAcknowledgement(String taskType, String description, int taskCount){
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Added " + taskType + ": " + description);
        System.out.println("Total Number of Tasks: " + taskCount);
        System.out.println(HORIZONTAL_LINE);
    }

    public static String[] getDeadlineInformation(String input) {
        String inputWithoutTaskType = removeFirstWord(input);
        String[] deadlineInformation = new String[2];
        deadlineInformation[0] = inputWithoutTaskType.split(" /by ",2)[0];
        deadlineInformation[1] = inputWithoutTaskType.split(" /by ",2)[1];
        return deadlineInformation;
    }

    public static String getEventDescription(String input) {
        String inputWithoutTaskType = removeFirstWord(input);
        return inputWithoutTaskType.split(" /from ",2)[0];
    }

    public static String[] getEventTimings(String input) {
        String inputWithoutTaskType = removeFirstWord(input);
        String[] timings = new String[2];
        String timing = inputWithoutTaskType.split(" /from ", 2)[1];

        timings[0] = timing.split(" /to ", 2)[0];
        timings[1] = timing.split(" /to ", 2)[1];

        return timings;
    }

    public static void printHelpMessage() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Use either 'todo', 'event' or 'deadline' to add an item to the task list!");
        System.out.println("Deadline Usage: deadline return book /by Sunday");
        System.out.println("Event Usage: event project meeting /from Mon 2pm /to 4pm");
        System.out.println("Todo Usage: todo borrow book");
        System.out.println("Use 'bye' to end the bot!");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void processMarkingCommand(String action, String[] words, Task[] tasks, int taskCount) throws ByteBrewException {
        if (words.length < MIN_INPUT_LENGTH) {
            throw new ByteBrewException("Please specify an index to " + action + "\n" +
                                        "Usage: " + action + " 1");
        }
        int taskIndex = Integer.parseInt(words[1]) - 1;
        if (taskIndex < 0 || taskIndex >= taskCount) {
            throw new ByteBrewException("Please specify a valid index!\n" +
                    "Current number of tasks: " + taskCount);
        }
        markTask(tasks, taskIndex, action.equals("mark"));
    }

    public static int addTodo(String[] words, String inputLine, Task[] tasks, int taskCount) throws ByteBrewException {
        if (words.length < MIN_INPUT_LENGTH) {
            throw new ByteBrewException("Description of a todo cannot be empty!\n" +
                    "Usage: todo borrow book");
        }
        Todo newTodo = new Todo(removeFirstWord(inputLine));
        tasks[taskCount] = newTodo;
        taskCount += 1;
        printAcknowledgement("Todo", removeFirstWord(inputLine), taskCount);
        return taskCount;
    }

    public static int addEvent(String[] words, String inputLine, Task[] tasks, int taskCount) throws ByteBrewException {
        if (words.length < MIN_INPUT_LENGTH) {
            throw new ByteBrewException("Description of an event cannot be empty!\n" +
                    "Usage: event project meeting /from Mon 2pm /to 4pm");
        }
        String[] timings = getEventTimings(inputLine);
        String eventDescription = getEventDescription(inputLine);
        Event event = new Event(eventDescription, timings[0], timings[1]);
        tasks[taskCount] = event;
        taskCount += 1;
        printAcknowledgement("Event", eventDescription, taskCount);
        return taskCount;
    }

    public static int addDeadline(String[] words, String inputLine, Task[] tasks, int taskCount) throws ByteBrewException {
        if (words.length < MIN_INPUT_LENGTH) {
            throw new ByteBrewException("Description of a deadline cannot be empty!\n" +
                    "Usage: deadline return book /by Sunday");
        }
        if 
        String[] deadlineInformation = getDeadlineInformation(inputLine);
        String deadlineDescription = deadlineInformation[0];
        String by = deadlineInformation[1];
        Deadline deadline = new Deadline(deadlineDescription, by);
        tasks[taskCount] = deadline;
        taskCount += 1;
        printAcknowledgement("Deadline", deadlineDescription, taskCount);
        return taskCount;
    }

    public static void main(String[] args) {
        startUp();
        Scanner in = new Scanner(System.in);

        Task[] tasks = new Task[MAX_TASK_COUNT];
        int taskCount = 0;

        while (true) {
            String inputLine = in.nextLine().trim();
            String[] words = inputLine.split(" ");

            try {
                switch (words[0]) {
                case "bye":
                    shutDown();
                    return;

                case "help":
                    printHelpMessage();
                    continue;

                case "deadline":
                    taskCount = addDeadline(words, inputLine, tasks, taskCount);
                    continue;

                case "event":
                    taskCount = addEvent(words, inputLine, tasks, taskCount);
                    continue;

                case "todo":
                    taskCount = addTodo(words, inputLine, tasks, taskCount);
                    continue;

                case "list":
                    listTasks(tasks, taskCount);
                    continue;

                case "unmark":
                    processMarkingCommand("unmark", words, tasks, taskCount);
                    continue;

                case "mark":
                    processMarkingCommand("mark", words, tasks, taskCount);
                    continue;

                default:
                    throw new ByteBrewException("Invalid command! Use 'help' to see the commands available!");
                }
            }
            catch (ByteBrewException e) {
                System.out.print(e.getMessage());
            }
        }
    }
}