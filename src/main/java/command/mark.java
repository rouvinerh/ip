package command;

import bytebrew.ByteBrewException;
import tasks.Task;
import utility.constants;
import utility.parse;

import java.util.ArrayList;

public class mark {
    public static void markTask(ArrayList<Task> tasks, int taskIndex, boolean isDone) {
        System.out.println(constants.HORIZONTAL_LINE);
        Task taskToEdit = tasks.get(taskIndex);

        if (taskToEdit.isDone == isDone) {
            System.out.println("Task is already marked as " + (isDone ? "done!" : "undone!"));
            System.out.println(constants.HORIZONTAL_LINE);
            return;
        }

        else if (isDone) {
            System.out.println("Nice! I've marked this task as done: ");
            taskToEdit.setStatus(true);
        }

        else {
            System.out.println("OK, I've marked this task as not done yet: ");
            taskToEdit.setStatus(false);
        }

        System.out.println(taskToEdit);
        System.out.println(constants.HORIZONTAL_LINE);
    }

    public static void processMarkingCommand(String action, String[] words, ArrayList<Task> tasks, int taskCount) throws ByteBrewException {
        if (words.length < constants.MIN_INPUT_LENGTH) {
            throw new ByteBrewException("Please specify an index to " + action + "\n" +
                    "Usage: " + action + " 1");
        }
        int taskIndex = parse.getTaskIndex(words, taskCount);
        markTask(tasks, taskIndex, action.equals("mark"));
    }

}
