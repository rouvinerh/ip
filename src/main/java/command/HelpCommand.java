package command;

import utility.Constants;
import storage.Storage;
import utility.Ui;
import tasks.TaskList;
public class HelpCommand implements Command {

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(Constants.HORIZONTAL_LINE);
        System.out.println("Use either 'todo', 'event' or 'deadline' to add an item to the task list!");
        System.out.println("Deadline Usage: deadline return book /by 2024-08-05 1500");
        System.out.println("Event Usage: event project meeting /from 2024-08-24 1500 /to 2024-08-24 1700");
        System.out.println("Todo Usage: todo borrow book");
        System.out.println("Use 'bye' to end the bot!");
        System.out.println(Constants.HORIZONTAL_LINE);
    }
}
