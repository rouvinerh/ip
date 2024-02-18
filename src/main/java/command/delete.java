package command;

import bytebrew.ByteBrewException;
import tasks.Task;
import utility.constants;
import utility.parse;

import java.util.ArrayList;

public class delete {
    public static int deleteItem(String[] words, ArrayList<Task> tasks, int taskCount) throws ByteBrewException {
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
            return taskCount;
        }
        catch (NumberFormatException e) {
            throw new ByteBrewException("Please enter a valid number for the index!");
        }
    }
}
