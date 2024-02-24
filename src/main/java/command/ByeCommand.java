package command;

import bytebrew.ByteBrewException;
import storage.Storage;
import utility.Ui;
import tasks.TaskList;

/**
 * Represents the {@code bye} command for the ByteBrew bot.
 */
public class ByeCommand implements Command{

    /**
     * Executes the {@code bye} command.
     * Writes all task data to a file, then displays the shutdown message.
     *
     * @param tasks   The {@code TaskList} representing the current list of tasks.
     * @param ui      The {@code Ui} instance for the user interface of the ByteBrew bot.
     * @param storage The {@code Storage} instance for managing all reading and writing of data from files.
     * @throws ByteBrewException If an error occurs during the execution of the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ByteBrewException {
        Storage.writeFile(tasks);
        Ui.shutDown();
    }

    /**
     * Returns {@code true} if command entered is {@code bye}, exiting the ByteBrew bot.
     *
     * @return {@code true} as {@code bye} is an exit command.
     */
    @Override
    public boolean isExit(){
        return true;
    }
}
