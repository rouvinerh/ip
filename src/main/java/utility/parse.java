package utility;

import bytebrew.ByteBrewException;

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
}
