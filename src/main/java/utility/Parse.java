package utility;

import bytebrew.ByteBrewException;
import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.HelpCommand;
import command.ListCommand;
import command.MarkCommand;
import command.TodoCommand;
import command.FindCommand;
import command.ByeCommand;

/**
 * Represents the user input parser of the ByteBrew bot.
 */
public class Parse {
    /**
     * Removes the first word from a string.
     *
     * @param input The input string from which the first word will be removed.
     * @return The {@code input} string after the first word is removed.
     */
    public static String removeFirstWord(String input){
        return input.split(" ",2)[1];
    }


    /**
     * Retrieves the task index from the user input when using the {@code mark}, {@code unmark} or {@code delete} commands.
     *
     * @param words Array of words obtained from the user input.
     * @param taskCount The number of elements within {@code TaskList}.
     * @return An integer representing the task index specified in the user input.
     * @throws ByteBrewException If an error occurs during the execution of the command.
     */
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

    /**
     * Parses user input into words, then creates {@code Command} object based on details entered.
     * Verifies user input to ensure that only valid commands are entered.
     *
     * @param userInput A string representing the user input.
     * @return A {@code Command} object to be created.
     * @throws ByteBrewException If there is any error during the execution.
     */
    public static Command parseCommand(String userInput) throws ByteBrewException {
        String[] words = userInput.split(" ");

        switch (words[0]) {
        case "bye":
            return new ByeCommand();

        case "help":
            return new HelpCommand();

        case "find":
            return new FindCommand(words);

        case "deadline":
            return new DeadlineCommand(userInput, words);

        case "event":
            return new EventCommand(userInput, words);

        case "todo":
            return new TodoCommand(userInput, words);

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
