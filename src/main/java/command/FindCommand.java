package command;

import bytebrew.ByteBrewException;
import storage.Storage;
import tasks.TaskList;
import utility.Ui;
import tasks.Task;
import utility.Constants;

public class find implements Command{

    public final String[] WORDS;

    public find(String[] words) {
        this.WORDS = words;
    }
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ByteBrewException {
        if (WORDS.length < Constants.MIN_INPUT_LENGTH) {
            throw new ByteBrewException("Please specify a keyword!\n" +
                                        "Usage: find book");
        }
        if (WORDS.length > Constants.MAX_FIND_INPUT_LENGTH) {
            throw new ByteBrewException("Please specifiy ONE keyword!\n" +
                                        "Usage: find book");
        }
        final String KEYWORD = WORDS[1];
        int taskCount = tasks.size();
        if (taskCount == 0) {
            throw new ByteBrewException("There is nothing in the task list to find!");
        }

        System.out.println(Constants.HORIZONTAL_LINE);
        System.out.println("Finding matching tasks in list...");

        int foundTaskCount = 0;
        for (int i = 0; i < taskCount; i++) {
            Task t = tasks.get(i);
            if (t.getDescription().contains(KEYWORD)) {
                System.out.println((i + 1) + ". "  + t);
                foundTaskCount += 1;
            }
        }

        if (foundTaskCount == 0) {
            System.out.println("Found no tasks with keyword of: '" + KEYWORD + "'");
        }
        System.out.println(Constants.HORIZONTAL_LINE);
    }
}
