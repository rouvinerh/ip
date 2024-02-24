package command;

import bytebrew.ByteBrewException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import tasks.Todo;
import utility.Constants;
import utility.Parse;
import utility.Ui;

import java.util.ArrayList;

/**
 * Represents the {@code todo} command for the ByteBrew bot.
 */
public class TodoCommand implements Command{
    private final String INPUTLINE;
    private final String[] WORDS;

    /**
     * Constructs a new {@code todo} command object with user input.
     *
     * @param input User input for the {@code todo} command.
     * @param words Array of words obtained from the user input.
     */
    public TodoCommand(String input, String[] words) {
        this.INPUTLINE = input;
        this.WORDS = words;
    }

    /**
     * Executes the {@code todo} command.
     * Adds a new todo to the task list.
     *
     * @param tasks   The {@code TaskList} representing the current list of tasks.
     * @param ui      The {@code Ui} instance for the user interface of the ByteBrew bot.
     * @param storage The {@code Storage} instance for managing all reading and writing of data from files.
     * @throws ByteBrewException If an error occurs during the execution of the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ByteBrewException {
        addTodo(this.WORDS, this.INPUTLINE, tasks, tasks.size());
    }

    /**
     * Indicates whether command entered is an exit command for the ByteBrew bot.
     *
     * @return {@code false} as {@code help} command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Adds a {@code todo} task to the task list.
     * @param words Array of words obtained from the user input.
     * @param inputLine User input for the {@code todo} command.
     * @param tasks The {@code TaskList} representing the current list of tasks.
     * @param taskCount The number of elements within {@code TaskList}.
     * @throws ByteBrewException If an error occurs during the execution of the command.
     */
    public static void addTodo(String[] words, String inputLine, ArrayList<Task> tasks, int taskCount) throws ByteBrewException {
        if (words.length < Constants.MIN_INPUT_LENGTH) {
            throw new ByteBrewException("Description of a todo cannot be empty!\n" +
                    "Usage: todo borrow book");
        }
        try {
            Todo newTodo = new Todo(Parse.removeFirstWord(inputLine).trim());
            tasks.add(taskCount, newTodo);
            Ui.printAcknowledgement("Todo", Parse.removeFirstWord(inputLine), taskCount);
        }
        catch (Exception e) {
            throw new ByteBrewException("Invalid syntax for todo!\n" +
                    "Usage: todo borrow book");
        }
    }
}
