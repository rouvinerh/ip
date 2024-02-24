package tasks;

import bytebrew.ByteBrewTimeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDateTime from;
    private LocalDateTime to;
    private String type = "event";
    public Event(String taskName, String strFrom, String strTo) throws ByteBrewTimeException {
        super(taskName);
        this.from = parseTime(strFrom);
        this.to = parseTime(strTo);
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getTimes() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String formattedFrom = this.from.format(formatter).replace('T', ' ');;
        String formattedTo = this.to.format(formatter).replace('T', ' ');
        return "from: " + formattedFrom + " to: " + formattedTo;
    }

    @Override
    public String toString() {
        return "[E] [" + getStatusIcon() + "] " + getDescription() + " (" + getTimes() + ")";
    }

    public static LocalDateTime parseTime(String dateTime) throws ByteBrewTimeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime formattedDateTime;
        try {
            formattedDateTime = LocalDateTime.parse(dateTime, formatter);
        }
        catch (DateTimeParseException e) {
            throw new ByteBrewTimeException("Invalid event time format!\n" +
                    "Format: event <description> /from yyyy-mm-dd HHmm /to yyyy-mm-dd HHmm\n" +
                    "Example: event project meeting /from 2024-08-24 1500 /to 2024-08-24 1700");
        }
        LocalDateTime currentTime = LocalDateTime.now();
        if (formattedDateTime.isBefore(currentTime)) {
            throw new ByteBrewTimeException("Time specified BEFORE current time!");
        }
        return formattedDateTime;
    }
}