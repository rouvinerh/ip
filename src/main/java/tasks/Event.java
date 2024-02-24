package tasks;

/**
 * Represents the {@code Event} task for the ByteBrew bot.
 */
public class Event extends Task{
    private String from;
    private String to;
    private String type = "event";

    /**
     * Constructs a new {@code Event} task object.
     *
     * @param description Description of the {@code Event} task.
     * @param from Start time for the {@code Event} task.
     * @param to End time for the {@code Event} task.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Retrieves a string representing the task type.
     * @return A string 'event' representing {@code Event} tasks.
     */
    @Override
    public String getType() {
        return this.type;
    }

    /**
     * Retrieves the string representation of the {@code Event} task.
     * @return A foramtted string representing the {@code Event} task, inclusive of the symbol representing completion status, description, start and end times.
     */
    @Override
    public String toString() {
        return "[E] [" + getStatusIcon() + "] " + getDescription() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Formats the start and end times from the {@code Event} task.
     * @return A formatted string with the start and end times.
     */
    @Override
    public String getTimes() {
        return "from: " + from + " to: " + to;
    }
}