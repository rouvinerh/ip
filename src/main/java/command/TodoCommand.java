package command;

import bytebrew.ByteBrewException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import tasks.Todo;
import utility.Constants;
import utility.Parse;
import utility.Ui;

import java.util.ArrayList;

public class TodoCommand implements Command{
    private final String INPUTLINE;
    private final String[] WORDS;

    public TodoCommand(String input, String[] words) {
        this.INPUTLINE = input;
        this.WORDS = words;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ByteBrewException {
        addTodo(this.WORDS, this.INPUTLINE, tasks, tasks.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public static void addTodo(String[] words, String inputLine, ArrayList<Task> tasks, int taskCount) throws ByteBrewException {
        if (words.length < Constants.MIN_INPUT_LENGTH) {
            throw new ByteBrewException("Description of a todo cannot be empty!\n" +
                    "Usage: todo borrow book");
        }
        try {
            Todo newTodo = new Todo(Parse.removeFirstWord(inputLine).trim());
            tasks.add(taskCount, newTodo);
            Ui.printAcknowledgement("Todo", Parse.removeFirstWord(inputLine), taskCount);
        }
        catch (Exception e) {
            throw new ByteBrewException("Invalid syntax for todo!\n" +
                    "Usage: todo borrow book");
        }
    }
}
