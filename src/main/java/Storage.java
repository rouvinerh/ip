import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public static final String HORIZONTAL_LINE = "__________________________________________________";

    public static void checkIsDone(ArrayList<Task> tasks, String symbol, int taskCount) throws ByteBrewException {
        if (symbol.equals("X")) {
            tasks.get(taskCount).setStatus(true);
        }
        else if (symbol.isEmpty()){
            tasks.get(taskCount).setStatus(false);
        }
        else {
            throw new ByteBrewException("Invalid symbol for isDone!");
        }
    }
    public static int processFile(ArrayList<Task> tasks) throws ByteBrewException {
        int taskCount = 0;
        try {
            File dataFile = new File("bytebrew_data.txt");

            if (dataFile.createNewFile()) {
                System.out.println(HORIZONTAL_LINE);
                System.out.println("Data file NOT present. Creating one now!");
                System.out.println(HORIZONTAL_LINE);
            } else {
                System.out.println(HORIZONTAL_LINE);
                System.out.println("Data file present!");
                System.out.println(HORIZONTAL_LINE);
            }

            final Scanner READ_FILE = new Scanner(dataFile);
            while (READ_FILE.hasNext()) {
                String[] task = READ_FILE.nextLine().split("\\|");
                switch (task[0].trim()) {
                case "todo": {
                    String toDoDescription = task[2];
                    Todo newTodo = new Todo(toDoDescription);
                    tasks.add(newTodo);
                    checkIsDone(tasks, task[1].trim(), taskCount);
                    break;
                }

                case "deadline": {
                    String[] deadlineInfo = task[2].split("by:");
                    String by;
                    if (deadlineInfo.length > 1) {
                        by = deadlineInfo[1].trim().substring(0, deadlineInfo[1].length() - 1);
                    }
                    else {
                        throw new ByteBrewException("Invalid deadline format!");
                    }
                    Deadline deadline = new Deadline(deadlineInfo[0].trim(), by);
                    tasks.add(deadline);
                    checkIsDone(tasks, task[1].trim(), taskCount);
                    break;
                }

                case "event": {
                    String[] eventInfo = task[2].split("from:");

                    if (eventInfo.length < 2) {
                        throw new ByteBrewException("Invalid event format");
                    }
                    String eventDescription = eventInfo[0].trim();

                    String[] eventTimes = eventInfo[1].split(" to:");
                    if (eventTimes.length < 2) {
                        throw new ByteBrewException("Invalid event format");
                    }

                    String from = eventTimes[0].trim();
                    String to = eventTimes[1].trim();

                    Event event = new Event(eventDescription, from, to);
                    tasks.add(event);
                    checkIsDone(tasks, task[1].trim(), taskCount);
                    break;
                }
                default: {
                    throw new ByteBrewException("Invalid input from file!\n" +
                            "Errored at taskCount = " + taskCount);
                }
                }
                taskCount += 1;
            }
            return taskCount;
        } catch (Exception e) {
            throw new ByteBrewException("File read error occurred!\n" +
                    "Errored at taskCount = " + taskCount);
        }
    }

    public static void writeFile (ArrayList<Task> tasks) throws ByteBrewException {
        try {
            FileWriter dataFile = new FileWriter("bytebrew_data.txt");

            for (Task t : tasks) {
                dataFile.write(t.getType() + "|" + t.getStatusIcon() + "|" + t.getDescription() + " " +
                                   t.getTimes() + '\n');
            }
            System.out.println(HORIZONTAL_LINE);
            System.out.println("Wrote tasks to bytebrew_data.txt!");
            System.out.println(HORIZONTAL_LINE);
            dataFile.close();
        }
        catch (IOException e) {
            throw new ByteBrewException("File write error occurred!");
        }
    }
}
