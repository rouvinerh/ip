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

/**
 * Represents the {@code event} command for the ByteBrew bot.
 */
public class EventCommand implements Command {
    private final String INPUT;
    private final String[] WORDS;

    /**
     * Constructs a new {@code event} command object with user input.
     *
     * @param input User input for the deadline command.
     * @param words Array of words obtained from the user input.
     */
    public EventCommand(String input, String[] words) {
        this.INPUT = input;
        this.WORDS = words;
    }

    /**
     * Executes the {@code event} command.
     * Adds a new event to the task list.
     *
     * @param tasks   The {@code TaskList} representing the current list of tasks.
     * @param ui      The {@code Ui} instance for the user interface of the ByteBrew bot.
     * @param storage The {@code Storage} instance for managing all reading and writing of data from files.
     * @throws ByteBrewException If an error occurs during the execution of the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ByteBrewException {
        addEvent(this.WORDS, this.INPUT, tasks, tasks.size());
    }

    /**
     * Indicates whether command entered is an exit command for the ByteBrew bot.
     *
     * @return {@code false} Since the {@code event} command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }


    /**
     * Extracts and return event description from user input.
     *
     * @param inputLine User input for the {@code event} command.
     * @return A string containing the event description.
     */
    public static String getEventDescription(String inputLine) {
        String inputWithoutTaskType = Parse.removeFirstWord(inputLine);
        return inputWithoutTaskType.split(" /from ",2)[0];
    }

    /**
     * Extracts and return event times from user input.
     *
     * @param inputLine User input for the {@code event} command.
     * @return An array containing the {@code from} and {@code to} time for the event.
     */
    public static String[] getEventTimings(String inputLine) {
        String inputWithoutTaskType = Parse.removeFirstWord(inputLine);
        String[] timings = new String[2];
        String timing = inputWithoutTaskType.split(" /from ", 2)[1];

        timings[0] = timing.split(" /to ", 2)[0];
        timings[1] = timing.split(" /to ", 2)[1];
        return timings;
    }

    /**
     * Adds an {@code event} task to the task list.
     *
     * @param words Array of words obtained from the user input.
     * @param inputLine User input for the deadline command.
     * @param tasks The {@code TaskList} representing the current list of tasks.
     * @param taskCount The number of elements within {@code TaskList}.
     * @throws ByteBrewException If an error occurs during the execution of the command.
     */
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
            Storage.writeFile(tasks);
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
