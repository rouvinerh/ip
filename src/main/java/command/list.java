package command;

import tasks.Task;
import utility.constants;
import bytebrew.ByteBrewException;

import java.util.ArrayList;

public class list {
    public static void listTasks(ArrayList<Task> tasks, int taskCount) throws ByteBrewException {
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
}
