package command;

import bytebrew.ByteBrewException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import utility.Ui;
import utility.Constants;
import utility.Parse;

import java.util.ArrayList;

/**
 * Represents the {@code delete} command for the ByteBrew bot.
 */
public class DeleteCommand implements Command {

    private final String[] WORDS;

    /**
     * Constructs a new {@code delete} command object with user input.
     *
     * @param words Array of words obtained from the user input.
     */
    public DeleteCommand(String[] words) {
        this.WORDS = words;
    }

    /**
     * Executes the {@code delete} command.
     * Adds a new deadline to the task list.
     *
     * @param tasks   The {@code TaskList} representing the current list of tasks.
     * @param ui      The {@code Ui} instance for the user interface of the ByteBrew bot.
     * @param storage The {@code Storage} instance for managing all reading and writing of data from files.
     * @throws ByteBrewException If an error occurs during the execution of the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ByteBrewException {
        deleteItem( WORDS, tasks, tasks.size());
    }

    /**
     * Indicates whether command entered is an exit command for the ByteBrew bot.
     *
     * @return {@code false} as {@code delete} command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Deletes a task from the task list.
     *
     * @param words Array of words obtained from the user input.
     * @param tasks The {@code TaskList} representing the current list of tasks.
     * @param taskCount The number of elements within {@code TaskList}.
     * @throws ByteBrewException If an error occurs during the execution of the command.
     */
    public static void deleteItem(String[] words, ArrayList<Task> tasks, int taskCount) throws ByteBrewException {
        if (words.length < Constants.MIN_INPUT_LENGTH) {
            throw new ByteBrewException("Task index to delete must be specified!\n" +
                    "Usage: delete 1");
        }
        try {
            int taskIndex = Parse.getTaskIndex(words, taskCount);
            taskCount -= 1;

            System.out.println(Constants.HORIZONTAL_LINE);
            System.out.println("Deleted task " + (taskIndex + 1) + ": " + tasks.get(taskIndex));
            System.out.println("Number of tasks left: " + taskCount);
            System.out.println(Constants.HORIZONTAL_LINE);
            tasks.remove(taskIndex);
            Storage.writeFile(tasks);
        }
        catch (NumberFormatException e) {
            throw new ByteBrewException("Please enter a valid number for the index!");
        }
    }
}
