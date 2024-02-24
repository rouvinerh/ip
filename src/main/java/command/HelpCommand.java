package command;

import bytebrew.ByteBrewException;
import utility.constants;
import storage.Storage;
import utility.Ui;
import tasks.TaskList;

/**
 * Represents the {@code help} command for the ByteBrew bot.
 */
public class HelpCommand implements Command {

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
     * Executes the 'exit' command.
     * Writes all task data to a file, then displays the shutdown message.
     *
     * @param tasks   The {@code TaskList} representing the current list of tasks.
     * @param ui      The {@code Ui} instance for the user interface of the ByteBrew bot.
     * @param storage The {@code Storage} instance for managing all reading and writing of data from files.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(Constants.HORIZONTAL_LINE);
        System.out.println("Use either 'todo', 'event' or 'deadline' to add an item to the task list!");
        System.out.println("Deadline Usage: deadline return book /by 2024-08-05 1500");
        System.out.println("Event Usage: event project meeting /from 2024-08-24 1500 /to 2024-08-24 1700");
        System.out.println("Todo Usage: todo borrow book");
        System.out.println("Use 'bye' to end the bot!");
        System.out.println(Constants.HORIZONTAL_LINE);
    }
}
