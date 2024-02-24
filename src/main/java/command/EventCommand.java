package command;

import bytebrew.ByteBrewException;
import bytebrew.ByteBrewTimeException;
import storage.Storage;
import tasks.Task;
import tasks.Event;
import tasks.TaskList;
import utility.Constants;
import utility.Parse;
import utility.Ui;
import java.util.ArrayList;


public class EventCommand implements Command {
    private final String INPUTLINE;
    private final String[] WORDS;

    public EventCommand(String input, String[] words) {
        this.INPUTLINE = input;
        this.WORDS = words;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ByteBrewException {
        addEvent(this.WORDS, this.INPUTLINE, tasks, tasks.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public static String getEventDescription(String input) {
        String inputWithoutTaskType = Parse.removeFirstWord(input);
        return inputWithoutTaskType.split(" /from ",2)[0];
    }

    public static String[] getEventTimings(String input) {
        String inputWithoutTaskType = Parse.removeFirstWord(input);
        String[] timings = new String[2];
        String timing = inputWithoutTaskType.split(" /from ", 2)[1];

        timings[0] = timing.split(" /to ", 2)[0];
        timings[1] = timing.split(" /to ", 2)[1];
        return timings;
    }

    public static void addEvent(String[] words, String inputLine, ArrayList<Task> tasks, int taskCount) throws ByteBrewException {
        if (words.length < Constants.MIN_INPUT_LENGTH) {
            throw new ByteBrewException("Description of an event cannot be empty!\n" +
                    "Usage: event project meeting /from 2024-08-24 1500 /to 2024-08-24 1700");
        }
        try {
            String[] timings = getEventTimings(inputLine);
            String eventDescription = getEventDescription(inputLine);
            Event event = new Event(eventDescription, timings[0], timings[1]);
            tasks.add(taskCount, event);
            Ui.printAcknowledgement("Event", eventDescription, taskCount);
        }
        catch (ByteBrewTimeException e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            throw new ByteBrewException("Invalid syntax for event!\n" +
                        "Usage: event project meeting /from 2024-08-24 1500 /to 2024-08-24 1700");
        }
    }
}
