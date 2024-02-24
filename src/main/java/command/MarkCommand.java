package command;

import bytebrew.ByteBrewException;
import tasks.Task;
import utility.Constants;
import utility.Parse;
import storage.Storage;
import utility.Ui;
import tasks.TaskList;

import java.util.ArrayList;

/**
 * Represents the {@code mark} command for the ByteBrew bot.
 */
public class MarkCommand implements Command{
    private final boolean IS_MARK;
    private final String[] WORDS;

    /**
     * Constructs a new {@code mark} command object with user input.
     *
     * @param isMark Boolean variable set to {@code true} if command entered is {@code mark}, and {@code false} if command is {@code unmark}.
     * @param words Array of words obtained from the user input.
     */
    public MarkCommand(boolean isMark, String[] words) {
        this.IS_MARK = isMark;
        this.WORDS = words;
    }

    /**
     * Executes the {@code mark} command.
     * Adds a new deadline to the task list.
     *
     * @param tasks   The {@code TaskList} representing the current list of tasks.
     * @param ui      The {@code Ui} instance for the user interface of the ByteBrew bot.
     * @param storage The {@code Storage} instance for managing all reading and writing of data from files.
     * @throws ByteBrewException If an error occurs during the execution of the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ByteBrewException {
        processMarkingCommand(IS_MARK, WORDS, tasks, tasks.size());
    }

    /**
     * Indicates whether command entered is an exit command for the ByteBrew bot.
     *
     * @return {@code false} Since the {@code mark} command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Marks a task as done or undone depending on {@code isDone}.
     * If {@code isDone} is true, mark as done. Otherwise, mark as not done.
     *
     * @param tasks The {@code TaskList} representing the current list of tasks.
     * @param taskIndex The task element index.
     * @param isDone Boolean variable indicating whether to mark task as done or not done.
     */
    public static void markTask(ArrayList<Task> tasks, int taskIndex, boolean isDone) {
        System.out.println(Constants.HORIZONTAL_LINE);
        Task taskToEdit = tasks.get(taskIndex);

        if (taskToEdit.isDone == isDone) {
            System.out.println("Task is already marked as " + (isDone ? "done!" : "undone!"));
            System.out.println(Constants.HORIZONTAL_LINE);
            return;
        }

        else if (isDone) {
            System.out.println("Nice! I've marked this task as done:");
            taskToEdit.setStatus(true);
        }

        else {
            System.out.println("OK, I've marked this task as not done yet:");
            taskToEdit.setStatus(false);
        }

        System.out.println(taskToEdit);
        System.out.println(Constants.HORIZONTAL_LINE);
    }

    /**
     * Processes {@code mark} command and determines whether to mark or unmark a task as done.
     *
     * @param IS_MARK Boolean variable set to {@code true} if command entered is {@code mark}, and {@code false} if command is {@code unmark}.
     * @param words Array of words obtained from the user input.
     * @param tasks The {@code TaskList} representing the current list of tasks.
     * @param taskCount The number of elements within {@code TaskList}.
     * @throws ByteBrewException If an error occurs during the execution of the command.
     */
    public static void processMarkingCommand(boolean IS_MARK, String[] words, ArrayList<Task> tasks, int taskCount) throws ByteBrewException {
        String action;
        if (IS_MARK) {
            action = "mark";
        }
        else {
            action = "unmark";
        }

        if (words.length < Constants.MIN_INPUT_LENGTH) {
            throw new ByteBrewException("Please specify an index to " + action + "\n" +
                    "Usage: " + action + " 1");
        }
        int taskIndex = Parse.getTaskIndex(words, taskCount);
        markTask(tasks, taskIndex, action.equals("mark"));
    }
}
