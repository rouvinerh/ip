package command;

import bytebrew.ByteBrewException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import utility.Ui;
import utility.constants;
import utility.parse;

import java.util.ArrayList;

public class delete implements Command {

    private final String[] WORDS;

    public delete(String[] words) {
        this.WORDS = words;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ByteBrewException {
        deleteItem( WORDS, tasks, tasks.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public static void deleteItem(String[] words, ArrayList<Task> tasks, int taskCount) throws ByteBrewException {
        if (words.length < constants.MIN_INPUT_LENGTH) {
            throw new ByteBrewException("Task index to delete must be specified!\n" +
                    "Usage: delete 1");
        }
        try {
            int taskIndex = parse.getTaskIndex(words, taskCount);
            taskCount -= 1;

            System.out.println(constants.HORIZONTAL_LINE);
            System.out.println("Deleted task " + (taskIndex + 1) + ": " + tasks.get(taskIndex));
            System.out.println("Number of tasks left: " + taskCount);
            System.out.println(constants.HORIZONTAL_LINE);
            tasks.remove(taskIndex);
        }
        catch (NumberFormatException e) {
            throw new ByteBrewException("Please enter a valid number for the index!");
        }
    }
}
