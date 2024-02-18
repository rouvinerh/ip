package utility;

public class ui {


    public static void startUp() {
        System.out.println(constants.HORIZONTAL_LINE);
        System.out.println("Hello! I'm ByteBrew!");
        System.out.println("What can I do for you?");
        System.out.println(constants.HORIZONTAL_LINE);
    }

    public static void shutDown() {
        System.out.println(constants.HORIZONTAL_LINE);
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println(constants.HORIZONTAL_LINE);
    }

    public static void printAcknowledgement(String taskType, String description, int taskCount){
        System.out.println(constants.HORIZONTAL_LINE);
        System.out.println("Added " + taskType + ": " + description);
        System.out.println("Total Number of Tasks: " + taskCount);
        System.out.println(constants.HORIZONTAL_LINE);
    }
}
