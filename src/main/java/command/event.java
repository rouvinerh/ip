package command;

import bytebrew.ByteBrewException;
import tasks.Task;
import tasks.Event;
import utility.*;

import java.util.ArrayList;


public class event {
    public static String getEventDescription(String input) {
        String inputWithoutTaskType = parse.removeFirstWord(input);
        return inputWithoutTaskType.split(" /from ",2)[0];
    }

    public static String[] getEventTimings(String input) {
        String inputWithoutTaskType = parse.removeFirstWord(input);
        String[] timings = new String[2];
        String timing = inputWithoutTaskType.split(" /from ", 2)[1];

        timings[0] = timing.split(" /to ", 2)[0];
        timings[1] = timing.split(" /to ", 2)[1];

        return timings;
    }

    public static int addEvent(String[] words, String inputLine, ArrayList<Task> tasks, int taskCount) throws ByteBrewException {
        if (words.length < constants.MIN_INPUT_LENGTH) {
            throw new ByteBrewException("Description of an event cannot be empty!\n" +
                    "Usage: event project meeting /from Mon 2pm /to 4pm");
        }
        try {
            String[] timings = getEventTimings(inputLine);
            String eventDescription = getEventDescription(inputLine);
            Event event = new Event(eventDescription, timings[0], timings[1]);
            tasks.add(taskCount, event);
            taskCount += 1;
            ui.printAcknowledgement("Event", eventDescription, taskCount);
            return taskCount;
        }
        catch (Exception e) {
            throw new ByteBrewException("Invalid syntax for event!\n" +
                    "Usage: event project meeting /from Mon 2pm /to 4pm");
        }
    }
}
