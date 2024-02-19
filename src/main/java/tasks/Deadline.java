package tasks;

public class Deadline extends Task {
    private String by;
    private String type = "deadline";

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getTimes() {
        return "by: " + by;
    }
    @Override
    public String toString() {
        return "[D] [" + getStatusIcon() + "] " + getDescription() + " (by: " + by + ")";
    }
}