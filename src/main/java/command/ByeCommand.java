package command;

import bytebrew.ByteBrewException;
import storage.Storage;
import utility.Ui;
import tasks.TaskList;
public class ByeCommand implements Command{

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ByteBrewException {
        Storage.writeFile(tasks);
        Ui.shutDown();
    }

    @Override
    public boolean isExit(){
        return true;
    }
}
