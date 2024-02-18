import java.util.Scanner;
import java.util.ArrayList;

import command.*;
import tasks.Task;
import utility.*;
import storage.Storage;
import bytebrew.ByteBrewException;
public class ByteBrew {
    public static void main(String[] args) throws ByteBrewException {
        ui.startUp();
        Scanner in = new Scanner(System.in);

        ArrayList<Task> tasks = new ArrayList<>(constants.MAX_TASK_COUNT);
        int taskCount = Storage.processFile(tasks);

        while (true) {
            String inputLine = in.nextLine().trim();
            String[] words = inputLine.split(" ");

            try {
                switch (words[0]) {
                case "bye":
                    Storage.writeFile(tasks);
                    ui.shutDown();
                    return;

                case "help":
                    help.printHelpMessage();
                    continue;

                case "deadline":
                    taskCount = deadline.addDeadline(words, inputLine, tasks, taskCount);
                    continue;

                case "event":
                    taskCount = event.addEvent(words, inputLine, tasks, taskCount);
                    continue;

                case "todo":
                    taskCount = todo.addTodo(words, inputLine, tasks, taskCount);
                    continue;

                case "list":
                    list.listTasks(tasks, taskCount);
                    continue;

                case "unmark":
                    mark.processMarkingCommand("unmark", words, tasks, taskCount);
                    continue;

                case "mark":
                    mark.processMarkingCommand("mark", words, tasks, taskCount);
                    continue;

                case "delete":
                    taskCount = delete.deleteItem(words, tasks, taskCount);
                    continue;

                default:
                    throw new ByteBrewException("Invalid command! Use 'help' to see the commands available!");
                }
            }
            catch (ByteBrewException e) {
                System.out.print(e.getMessage());
            }
        }
    }
}