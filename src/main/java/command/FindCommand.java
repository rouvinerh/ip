package command;

import bytebrew.ByteBrewException;
import storage.Storage;
import tasks.TaskList;
import utility.Ui;
import tasks.Task;
import utility.Constants;


/**
 * Represents the {@code find} command for the ByteBrew bot.
 */
public class FindCommand implements Command{

    public final String[] WORDS;

    /**
     * Constructs a new {@code find} command object with user input.
     *
     * @param words Array of words obtained from the user input.
     */
    public FindCommand(String[] words) {
        this.WORDS = words;
    }

    /**
     * Indicates whether command entered is an exit command for the ByteBrew bot.
     *
     * @return {@code false} Since the {@code find} command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the {@code event} command.
     * Finds a task based on the given keyword.
     *
     * @param tasks   The {@code TaskList} representing the current list of tasks.
     * @param ui      The {@code Ui} instance for the user interface of the ByteBrew bot.
     * @param storage The {@code Storage} instance for managing all reading and writing of data from files.
     * @throws ByteBrewException If an error occurs during the execution of the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ByteBrewException {
        if (WORDS.length < Constants.MIN_INPUT_LENGTH) {
            throw new ByteBrewException("Please specify a keyword!\n" +
                                        "Usage: find book");
        }
        if (WORDS.length > Constants.MAX_FIND_INPUT_LENGTH) {
            throw new ByteBrewException("Please specify ONE keyword!\n" +
                                        "Usage: find book");
        }
        final String KEYWORD = WORDS[1];
        int taskCount = tasks.size();

        System.out.println(Constants.HORIZONTAL_LINE);
        System.out.println("Finding matching tasks in list...");

        int foundTaskCount = 0;
        for (int i = 0; i < taskCount; i++) {
            Task t = tasks.get(i);
            if (t.getDescription().contains(KEYWORD)) {
                System.out.println((i + 1) + ". "  + t);
                foundTaskCount += 1;
            }
        }

        if (foundTaskCount == 0) {
            System.out.println("Found no tasks with keyword of: '" + KEYWORD + "'");
        }
        System.out.println(Constants.HORIZONTAL_LINE);
    }
}
