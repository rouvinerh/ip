package utility;
import java.util.Scanner;

/**
 * Represents the user interface of the ByteBrew bot.
 */
public class Ui {
    Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Prints the startup message.
     */
    public static void startUp() {
        System.out.println(Constants.HORIZONTAL_LINE);
        System.out.println("Hello! I'm ByteBrew!");
        System.out.println("What can I do for you?");
        System.out.println(Constants.HORIZONTAL_LINE);
    }

    /**
     * Prints the shutdown message.
     */
    public static void shutDown() {
        System.out.println(Constants.HORIZONTAL_LINE);
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println(Constants.HORIZONTAL_LINE);
    }

    /**
     * Prints the acknowledgement to indicate a successful adding of a task to the task list.
     *
     * @param taskType A string representing the type of task added.
     * @param description A string representing the description of the task added.
     * @param taskCount An integer representing the number of tasks there are within the task list.
     */
    public static void printAcknowledgement(String taskType, String description, int taskCount){
        taskCount += 1;
        System.out.println(Constants.HORIZONTAL_LINE);
        System.out.println("Added " + taskType + ": " + description);
        System.out.println("Total Number of Tasks: " + taskCount);
        System.out.println(Constants.HORIZONTAL_LINE);
    }

    /**
     * Reads one line from user input and removes excess empty spaces.
     *
     * @return A string representing the line the user entered
     */
    public String readCommand() {
        return this.in.nextLine().trim();
    }

    /**
     * Prints out the error message if any.
     * 
     * @param error A string representing the error message to print.
     */
    public void showError(String error) {
        System.out.println(error);
    }
}