package command;

import bytebrew.ByteBrewException;
import tasks.Task;
import utility.Constants;
import utility.Parse;
import storage.Storage;
import utility.Ui;
import tasks.TaskList;

import java.util.ArrayList;

public class MarkCommand implements Command{
    private final boolean IS_MARK;
    private final String[] WORDS;

    public MarkCommand(boolean isMark, String[] words) {
        this.IS_MARK = isMark;
        this.WORDS = words;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ByteBrewException {
        processMarkingCommand(IS_MARK, WORDS, tasks, tasks.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }
    public static void markTask(ArrayList<Task> tasks, int taskIndex, boolean isDone) {
        System.out.println(Constants.HORIZONTAL_LINE);
        Task taskToEdit = tasks.get(taskIndex);

        if (taskToEdit.isDone == isDone) {
            System.out.println("Task is already marked as " + (isDone ? "done!" : "undone!"));
            System.out.println(Constants.HORIZONTAL_LINE);
            return;
        }

        else if (isDone) {
            System.out.println("Nice! I've marked this task as done:");
            taskToEdit.setStatus(true);
        }

        else {
            System.out.println("OK, I've marked this task as not done yet:");
            taskToEdit.setStatus(false);
        }

        System.out.println(taskToEdit);
        System.out.println(Constants.HORIZONTAL_LINE);
    }

    public static void processMarkingCommand(boolean isMark, String[] words, ArrayList<Task> tasks, int taskCount) throws ByteBrewException {
        String action;
        if (isMark) {
            action = "mark";
        }
        else {
            action = "unmark";
        }

        if (words.length < Constants.MIN_INPUT_LENGTH) {
            throw new ByteBrewException("Please specify an index to " + action + "\n" +
                    "Usage: " + action + " 1");
        }
        int taskIndex = Parse.getTaskIndex(words, taskCount);
        markTask(tasks, taskIndex, action.equals("mark"));
    }

}
