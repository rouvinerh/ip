package tasks;
public class Todo extends Task{
    private String type = "todo";
    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "[T] [" + getStatusIcon() + "] " + getDescription();
    }
}