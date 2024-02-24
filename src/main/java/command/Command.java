package command;

import bytebrew.ByteBrewException;
import storage.Storage;
import utility.Ui;
import tasks.TaskList;

/**
 * Represents a Command for the ByteBrew bot.
 */
public interface Command {
    /**
     * Indicates whether command entered is an exit command for the ByteBrew bot.
     *
     * @return {@code true} If the command is an exit command. Otherwise, {@code false} is returned.
     */
    boolean isExit();

    /**
     * Executes the command entered.
     * @param tasks   The {@code TaskList} representing the current list of tasks.
     * @param ui      The {@code Ui} instance for the user interface of the ByteBrew bot.
     * @param storage The {@code Storage} instance for managing all reading and writing of data from files.
     * @throws ByteBrewException If an error occurs during the execution of the command.
     */
    void execute(TaskList tasks, Ui ui, Storage storage) throws ByteBrewException;
}
