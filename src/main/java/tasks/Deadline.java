package tasks;

import bytebrew.ByteBrewTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the {@code Deadline} task for the ByteBrew bot.
 */
public class Deadline extends Task {
    private LocalDateTime by;
    private String type = "deadline";

    /**
     * Constructs a new {@code Deadline} task object.
     *
     * @param description Description of the {@code Deadline} task.
     * @param by Deadline for the {@code Deadline} task.
     */
    public Deadline(String description, String strBy) throws ByteBrewTimeException {
        super(description);
        this.by = parseTime(strBy);
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String formattedDateTime = this.by.format(formatter).replace('T', ' ');
        return "by: " + formattedDateTime;
    }

    /**
     * Retrieves the string representation of the {@code Deadline} task.
     * @return A foramtted string representing the {@code Deadline} task, inclusive of the symbol representing completion status, description and the actual deadline.
     */
    @Override
    public String toString() {
        return "[D] [" + getStatusIcon() + "] " + getDescription() + " (" + getTimes() + ")";
    }


    public static LocalDateTime parseTime(String dateTime) throws ByteBrewTimeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime formattedDateTime;
        try {
            formattedDateTime = LocalDateTime.parse(dateTime, formatter);
        }
        catch (DateTimeParseException e) {
            throw new ByteBrewTimeException("Invalid deadline time format!\n" +
                                            "Format: deadline <description> /by yyyy-mm-dd HHmm\n" +
                                            "Example: deadline return book /by 2024-08-05 1500");
        }
        LocalDateTime currentTime = LocalDateTime.now();
        if (formattedDateTime.isBefore(currentTime)) {
            throw new ByteBrewTimeException("Time specified BEFORE current time!");
        }
        return formattedDateTime;
    }
}