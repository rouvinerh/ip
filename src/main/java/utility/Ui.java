package utility;
import java.util.Scanner;

public class Ui {
    Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    public static void startUp() {
        System.out.println(Constants.HORIZONTAL_LINE);
        System.out.println("Hello! I'm ByteBrew!");
        System.out.println("What can I do for you?");
        System.out.println(Constants.HORIZONTAL_LINE);
    }

    public static void shutDown() {
        System.out.println(Constants.HORIZONTAL_LINE);
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println(Constants.HORIZONTAL_LINE);
    }

    public static void printAcknowledgement(String taskType, String description, int taskCount){
        taskCount += 1;
        System.out.println(Constants.HORIZONTAL_LINE);
        System.out.println("Added " + taskType + ": " + description);
        System.out.println("Total Number of Tasks: " + taskCount);
        System.out.println(Constants.HORIZONTAL_LINE);
    }

    public String readCommand() {
        return this.in.nextLine().trim();
    }

    public void showError(String error) {
        System.out.println(error);
    }
}