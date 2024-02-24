package command;

import bytebrew.ByteBrewException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import utility.Ui;
import utility.Constants;
import utility.Parse;

import java.util.ArrayList;

public class DeleteCommand implements Command {

    private final String[] WORDS;

    public DeleteCommand(String[] words) {
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
        if (words.length < Constants.MIN_INPUT_LENGTH) {
            throw new ByteBrewException("Task index to delete must be specified!\n" +
                    "Usage: delete 1");
        }
        try {
            int taskIndex = Parse.getTaskIndex(words, taskCount);
            taskCount -= 1;

            System.out.println(Constants.HORIZONTAL_LINE);
            System.out.println("Deleted task " + (taskIndex + 1) + ": " + tasks.get(taskIndex));
            System.out.println("Number of tasks left: " + taskCount);
            System.out.println(Constants.HORIZONTAL_LINE);
            tasks.remove(taskIndex);
        }
        catch (NumberFormatException e) {
            throw new ByteBrewException("Please enter a valid number for the index!");
        }
    }
}
