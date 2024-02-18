package command;

import bytebrew.ByteBrewException;
import tasks.Deadline;
import tasks.Task;
import utility.parse;
import utility.ui;
import utility.constants;

import java.util.ArrayList;

public class deadline {
    public static int addDeadline(String[] words, String inputLine, ArrayList<Task> tasks, int taskCount) throws ByteBrewException {
        if (words.length < constants.MIN_INPUT_LENGTH) {
            throw new ByteBrewException("Description of a deadline cannot be empty!\n" +
                    "Usage: deadline return book /by Sunday");
        }
        try {
            String[] deadlineInformation = getDeadlineInformation(inputLine);
            String deadlineDescription = deadlineInformation[0];
            String by = deadlineInformation[1];
            Deadline deadline = new Deadline(deadlineDescription, by);
            tasks.add(taskCount, deadline);
            taskCount += 1;
            ui.printAcknowledgement("Deadline", deadlineDescription, taskCount);
            return taskCount;
        }
        catch (Exception e) {
            throw new ByteBrewException("Invalid syntax for deadline!\n" +
                    "Usage: deadline return book /by Sunday");
        }
    }
    public static String[] getDeadlineInformation(String input) {
        String inputWithoutTaskType = parse.removeFirstWord(input);
        String[] deadlineInformation = new String[2];
        deadlineInformation[0] = inputWithoutTaskType.split(" /by ",2)[0];
        deadlineInformation[1] = inputWithoutTaskType.split(" /by ",2)[1];
        return deadlineInformation;
    }
}
