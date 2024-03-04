package tasks;

/**
 * Represent a general {@code Task} for the ByteBrew bot.
 */
public class Task {
    public boolean isDone;
    private String description;
    private String type = "task";

    /**
     * Constructs a new {@code Task} object.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves the description of a task.
     *
     * @return A string representing the description of a {@code Task} object.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a symbol representing the completion status of a task.
     *
     * @return A string representing completion status of a task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done or not done based on {@code isDone} parameter. If {@code isDone} is {@code true}, task is done.
     *
     * @param isDone A boolean variable representing completion status of task.
     */
    public void setStatus(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Retrieves a string representing the task type.
     *
     * @return A string 'task' representing {@code Task} tasks.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Retrieves a string representing the time within a {@code Task} object.
     *
     * @return An empty string since {@code Task} has no time stored.
     */
    public String getTimes() {
        return "";
    }

    /**
     * Returns a formatted string representing the {@code Task} object.
     *
     * @return A string representing a {@code Task} object.
     */
    public String printTask() {
        return "[" + getStatusIcon() + "]" + getDescription();
    }

}
