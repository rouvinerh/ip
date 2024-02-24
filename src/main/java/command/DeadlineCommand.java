package command;

import bytebrew.ByteBrewException;
import bytebrew.ByteBrewTimeException;
import tasks.Deadline;
import tasks.Task;
import utility.Parse;
import utility.Ui;
import utility.Constants;
import storage.Storage;
import tasks.TaskList;
import java.util.ArrayList;

/**
 * Represents the {@code deadline} command for the ByteBrew bot.
 */
public class DeadlineCommand implements Command{

    private final String INPUTLINE;
    private final String[] WORDS;

    /**
     * Constructs a new {@code deadline} command object with user input.
     *
     * @param input User input for the {@code deadline} command.
     * @param words Array of words obtained from the user input.
     */
    public DeadlineCommand(String input, String[] words) {
        this.INPUTLINE = input;
        this.WORDS = words;
    }

    /**
     * Executes the {@code deadline} command.
     * Adds a new deadline to the task list.
     *
     * @param tasks   The {@code TaskList} representing the current list of tasks.
     * @param ui      The {@code Ui} instance for the user interface of the ByteBrew bot.
     * @param storage The {@code Storage} instance for managing all reading and writing of data from files.
     * @throws ByteBrewException If an error occurs during the execution of the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ByteBrewException {
        addDeadline(this.WORDS, this.INPUTLINE, tasks, tasks.size());
    }

    /**
     * Indicates whether command entered is an exit command for the ByteBrew bot.
     *
     * @return {@code false} as {@code deadline} command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Adds a {@code deadline} task to the task list.
     *
     * @param words Array of words obtained from the user input.
     * @param inputLine User input for the {@code deadline} command.
     * @param tasks The {@code TaskList} representing the current list of tasks.
     * @param taskCount The number of elements within {@code TaskList}.
     * @throws ByteBrewException If an error occurs during the execution of the command.
     */
    public static void addDeadline(String[] words, String inputLine, ArrayList<Task> tasks, int taskCount) throws ByteBrewException {
        if (words.length < Constants.MIN_INPUT_LENGTH) {
            throw new ByteBrewException("Description of a deadline cannot be empty!\n" +
                    "Usage: deadline return book /by 2024-08-05 1500");
        }
        try {
            String[] deadlineInformation = getDeadlineInformation(inputLine);
            String deadlineDescription = deadlineInformation[0];
            String by = deadlineInformation[1];
            Deadline deadline = new Deadline(deadlineDescription, by);
            tasks.add(taskCount, deadline);
            Ui.printAcknowledgement("Deadline", deadlineDescription, taskCount);
            Storage.writeFile(tasks);
        }
        catch (ByteBrewTimeException e) {
            System.out.println(e.getMessage());
        }

        catch (Exception e) {
            throw new ByteBrewException("Invalid syntax for deadline!\n" +
                                        "Usage: deadline return book /by 2024-08-05 1500");
        }
    }

    /**
     * Extracts and returns the deadline description and time from user input.
     *
     * @param inputLine User input for the {@code deadline} command.
     * @return An array containing the description and deadline time.
     */
    public static String[] getDeadlineInformation(String inputLine) {
        String inputWithoutTaskType = Parse.removeFirstWord(inputLine);
        String[] deadlineInformation = new String[2];
        deadlineInformation[0] = inputWithoutTaskType.split(" /by ",2)[0];
        deadlineInformation[1] = inputWithoutTaskType.split(" /by ",2)[1];
        return deadlineInformation;
    }
}
