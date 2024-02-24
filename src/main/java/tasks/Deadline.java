package tasks;

/**
 * Represents the {@code Deadline} task for the ByteBrew bot.
 */
public class Deadline extends Task {
    private String by;
    private String type = "deadline";

    /**
     * Constructs a new {@code Deadline} task object.
     *
     * @param description Description of the {@code Deadline} task.
     * @param by Deadline for the {@code Deadline} task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Retrieves a string representing the task type.
     * @return A string 'deadline' representing {@code Deadline} tasks.
     */
    @Override
    public String getType() {
        return this.type;
    }

    /**
     * Formats the actual deadline within from the {@code Deadline} task.
     * @return A formatted string with the deadline.
     */
    @Override
    public String getTimes() {
        return "by: " + by;
    }

    /**
     * Retrieves the string representation of the {@code Deadline} task.
     * @return A foramtted string representing the {@code Deadline} task, inclusive of the symbol representing completion status, description and the actual deadline.
     */
    @Override
    public String toString() {
        return "[D] [" + getStatusIcon() + "] " + getDescription() + " (by: " + by + ")";
    }
}