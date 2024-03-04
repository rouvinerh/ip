package tasks;

/**
 * Represents the {@code Todo} task for the ByteBrew bot.
 */
public class Todo extends Task{
    private String type = "todo";

    /**
     * Constructs a new {@code Todo} task object.
     *
     * @param description Description of the {@code Todo} task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Retrieves a string representing the task type.
     *
     * @return A string 'todo' representing {@code Todo} tasks.
     */
    @Override
    public String getType() {
        return this.type;
    }


    /**
     * Retrieves a string representing the time within a {@code Todo} object.
     *
     * @return An empty string since {@code Todo} has no time stored.
     */
    @Override
    public String getTimes() {
        return "";
    }

    /**
     * Retrieves the string representation of the {@code Todo} task.
     *
     * @return A formatted string representing the {@code Todo} task, inclusive of the symbol representing completion status and description.
     */
    @Override
    public String toString() {
        return "[T] [" + getStatusIcon() + "] " + getDescription();
    }
}