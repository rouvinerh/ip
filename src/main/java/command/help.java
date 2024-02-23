package command;

import utility.constants;
import storage.Storage;
import utility.Ui;
import tasks.TaskList;
public class help implements Command {

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(constants.HORIZONTAL_LINE);
        System.out.println("Use either 'todo', 'event' or 'deadline' to add an item to the task list!");
        System.out.println("Deadline Usage: deadline return book /by Sunday");
        System.out.println("Event Usage: event project meeting /from Mon 2pm /to 4pm");
        System.out.println("Todo Usage: todo borrow book");
        System.out.println("Use 'bye' to end the bot!");
        System.out.println(constants.HORIZONTAL_LINE);
    }
}
