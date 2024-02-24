package utility;

import bytebrew.ByteBrewException;

import command.Command;
import command.deadline;
import command.delete;
import command.find;
import command.event;
import command.help;
import command.list;
import command.mark;
import command.todo;
import command.bye;

public class parse {
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
            return new bye();

        case "help":
            return new help();

        case "find":
            return new find(words);

        case "deadline":
            return new deadline(command, words);

        case "event":
            return new event(command, words);

        case "todo":
            return new todo(command, words);

        case "list":
            return new list();

        case "unmark":
            return new mark(false, words);

        case "mark":
            return new mark(true, words);

        case "delete":
            return new delete(words);

        default:
            throw new ByteBrewException("Invalid command! Use 'help' to see the commands available!");
        }
    }
}
