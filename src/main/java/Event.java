public class Event extends Task{
    private String from;
    private String to;
    private String type = "event";
    public Event(String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "[E] [" + getStatusIcon() + "] " + getDescription() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String getTimes() {
        return "from: " + from + " to: " + to;
    }
}