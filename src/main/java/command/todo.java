package command;

import bytebrew.ByteBrewException;
import tasks.Task;
import tasks.Todo;
import utility.*;

import java.util.ArrayList;

public class todo {
    public static int addTodo(String[] words, String inputLine, ArrayList<Task> tasks, int taskCount) throws ByteBrewException {
        if (words.length < constants.MIN_INPUT_LENGTH) {
            throw new ByteBrewException("Description of a todo cannot be empty!\n" +
                    "Usage: todo borrow book");
        }
        try {
            Todo newTodo = new Todo(parse.removeFirstWord(inputLine));
            tasks.add(taskCount, newTodo);
            taskCount += 1;
            ui.printAcknowledgement("Todo", parse.removeFirstWord(inputLine), taskCount);
            return taskCount;
        }
        catch (Exception e) {
            throw new ByteBrewException("Invalid syntax for todo!\n" +
                    "Usage: todo borrow book");
        }
    }
}
