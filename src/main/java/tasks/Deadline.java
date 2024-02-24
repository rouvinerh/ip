package tasks;

import bytebrew.ByteBrewTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDateTime by;
    private String type = "deadline";

    public Deadline(String description, String strBy) throws ByteBrewTimeException {
        super(description);
        this.by = parseTime(strBy);
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getTimes() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String formattedDateTime = this.by.format(formatter).replace('T', ' ');
        return "by: " + formattedDateTime;
    }
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