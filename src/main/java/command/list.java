package command;

import storage.Storage;
import tasks.TaskList;
import utility.Ui;
import utility.constants;
import bytebrew.ByteBrewException;

public class list implements Command{

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ByteBrewException {
        int taskCount = tasks.size();
        if (taskCount == 0) {
            throw new ByteBrewException("There is nothing in the task list!");
        }
        System.out.println(constants.HORIZONTAL_LINE);
        System.out.println("Here's the task list: ");

        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". "  + tasks.get(i));
        }

        System.out.println(constants.HORIZONTAL_LINE);
    }

    @Override
    public boolean isExit(){
        return false;
    }
}
