package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    public boolean isDone;
    private String description;
    private String type = "task";
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setStatus(boolean isDone) {
        this.isDone = isDone;
    }

    public String getType() {
        return this.type;
    }

    public String getTimes() {
        return "";
    }
}
