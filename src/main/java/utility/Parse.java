package utility;

import bytebrew.ByteBrewException;
import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.FindCommand;
import command.HelpCommand;
import command.ListCommand;
import command.MarkCommand;
import command.TodoCommand;
import command.ByeCommand;

public class Parse {
    public static String removeFirstWord(String input){
        return input.split(" ",2)[1];
    }

    public static int getTaskIndex(String[] words, int taskCount) throws ByteBrewException {
        try {
            int taskIndex = Integer.parseInt(words[1]) - 1;
            if (taskIndex < 0 || taskIndex >= taskCount) {
                throw new ByteBrewException("Please specify a valid index!\n" +
                        "Current number of tasks: " + taskCount);
            }
            return taskIndex;
        }
        catch (NumberFormatException e) {
            throw new ByteBrewException("Please enter a valid number for the index!");
        }
    }

    public static Command parseCommand(String command) throws ByteBrewException {
        String[] words = command.split(" ");

        switch (words[0]) {
        case "bye":
            return new ByeCommand();

        case "help":
            return new HelpCommand();

        case "find":
            return new FindCommand(words);

        case "deadline":
            return new DeadlineCommand(command, words);

        case "event":
            return new EventCommand(command, words);

        case "todo":
            return new TodoCommand(command, words);

        case "list":
            return new ListCommand();

        case "unmark":
            return new MarkCommand(false, words);

        case "mark":
            return new MarkCommand(true, words);

        case "delete":
            return new DeleteCommand(words);

        default:
            throw new ByteBrewException("Invalid command! Use 'help' to see the commands available!");
        }
    }
}
