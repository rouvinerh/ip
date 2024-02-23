package command;

import bytebrew.ByteBrewException;
import storage.Storage;
import utility.Ui;
import tasks.TaskList;

public interface Command {
    boolean isExit();
    void execute(TaskList tasks, Ui ui, Storage storage) throws ByteBrewException;
}
