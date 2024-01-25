public class Duke {
    public static void main(String[] args) {
        String line = "____________________________________________________________\n";
        String greeting = line
                + "Hello! I'm ByteBrew!\n"
                + "What can I do for you?\n"
                + line;
        String farewell = "Bye. Hope to see you again soon!\n"
                + line;
        System.out.println(greeting + farewell);
    }
}
