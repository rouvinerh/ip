package command;

import storage.Storage;
import tasks.TaskList;
import utility.Ui;
import utility.Constants;
import bytebrew.ByteBrewException;

/**
 * Represents the {@code list} command for the ByteBrew bot.
 */
public class ListCommand implements Command{

    /**
     * Executes the {@code list} command.
     * Prints out all tasks within the {@code TaskList}.
     *
     * @param tasks   The {@code TaskList} representing the current list of tasks.
     * @param ui      The {@code Ui} instance for the user interface of the ByteBrew bot.
     * @param storage The {@code Storage} instance for managing all reading and writing of data from files.
     * @throws ByteBrewException If an error occurs during the execution of the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ByteBrewException {
        int taskCount = tasks.size();
        if (taskCount == 0) {
            throw new ByteBrewException("There is nothing in the task list!");
        }
        System.out.println(Constants.HORIZONTAL_LINE);
        System.out.println("Here's the task list:");

        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". "  + tasks.get(i));
        }

        System.out.println(Constants.HORIZONTAL_LINE);
    }

    /**
     * Indicates whether command entered is an exit command for the ByteBrew bot.
     *
     * @return {@code false} Since the {@code list} command is not an exit command.
     */
    @Override
    public boolean isExit(){
        return false;
    }
}
