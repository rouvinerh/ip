package command;

import bytebrew.ByteBrewException;
import bytebrew.ByteBrewTimeException;
import tasks.Deadline;
import tasks.Task;
import utility.Parse;
import utility.Ui;
import utility.Constants;
import storage.Storage;
import tasks.TaskList;

import java.util.ArrayList;

public class DeadlineCommand implements Command{

    private final String INPUTLINE;
    private final String[] WORDS;

    public DeadlineCommand(String input, String[] words) {
        this.INPUTLINE = input;
        this.WORDS = words;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ByteBrewException {
        addDeadline(this.WORDS, this.INPUTLINE, tasks, tasks.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }
    public static void addDeadline(String[] words, String inputLine, ArrayList<Task> tasks, int taskCount) throws ByteBrewException {
        if (words.length < Constants.MIN_INPUT_LENGTH) {
            throw new ByteBrewException("Description of a deadline cannot be empty!\n" +
                    "Usage: deadline return book /by 2024-08-05 1500");
        }
        try {
            String[] deadlineInformation = getDeadlineInformation(inputLine);
            String deadlineDescription = deadlineInformation[0];
            String by = deadlineInformation[1];
            Deadline deadline = new Deadline(deadlineDescription, by);
            tasks.add(taskCount, deadline);
            Ui.printAcknowledgement("Deadline", deadlineDescription, taskCount);
        }
        catch (ByteBrewTimeException e) {
            System.out.println(e.getMessage());
        }

        catch (Exception e) {
            throw new ByteBrewException("Invalid syntax for deadline!\n" +
                                        "Usage: deadline return book /by 2024-08-05 1500");
        }
    }
    public static String[] getDeadlineInformation(String input) {
        String inputWithoutTaskType = Parse.removeFirstWord(input);
        String[] deadlineInformation = new String[2];
        deadlineInformation[0] = inputWithoutTaskType.split(" /by ",2)[0];
        deadlineInformation[1] = inputWithoutTaskType.split(" /by ",2)[1];
        return deadlineInformation;
    }
}
