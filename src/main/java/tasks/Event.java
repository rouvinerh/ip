package tasks;


import bytebrew.ByteBrewTimeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

/**
 * Represents the {@code Event} task for the ByteBrew bot.
 */
public class Event extends Task{
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs a new {@code Event} task object.
     *
     * @param description Description of the {@code Event} task.
     * @param stringFrom Start time for the {@code Event} task.
     * @param stringTo End time for the {@code Event} task.
     * @throws ByteBrewTimeException If 'to' time is before 'from' time.
     */
    public Event(String description, String stringFrom, String stringTo) throws ByteBrewTimeException{
        super(description);
        this.from = parseTime(stringFrom);
        this.to = parseTime(stringTo);
        if (to.isBefore(from)) {
            throw new ByteBrewTimeException("'to' time cannot be before 'from' time!");
        }
    }

    /**
     * Retrieves a string representing the task type.
     *
     * @return A string 'event' representing {@code Event} tasks.
     */
    @Override
    public String getType() {
        return "event";
    }

    /**
     * Retrieves the string representation of the {@code Event} task.
     *
     * @return A formatted string representing the {@code Event} task, inclusive of the symbol representing completion status, description, start and end times.
     */
    @Override
    public String getTimes() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String formattedFrom = this.from.format(formatter).replace('T', ' ');
        String formattedTo = this.to.format(formatter).replace('T', ' ');
        return "from: " + formattedFrom + " to: " + formattedTo;
    }

    /**
     * Formats the start and end times from the {@code Event} task.
     *
     * @return A formatted string with the start and end times.
     */
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