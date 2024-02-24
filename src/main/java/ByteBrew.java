import tasks.TaskList;
import utility.Ui;
import storage.Storage;
import utility.parse;
import bytebrew.ByteBrewException;
import command.Command;

/**
 * Represents the ByteBrew bot.
 */
public class ByteBrew{
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new {@code ByteBrew} object.
     */
    public ByteBrew(){
        this.tasks = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage();
    }

    /**
     * Runs the ByteBrew bot.
     * @throws ByteBrewException If an error occurs during the execution of the command.
     */
    public void run() throws ByteBrewException {
        ui.startUp();
        Storage.processFile(this.tasks);
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = parse.parseCommand(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            }
            catch (ByteBrewException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Starts the ByteBrew bot by creating a {@code ByteBrew} object and calling {@code run}.
     * @param args Command-line arguments from the user.
     * @throws ByteBrewException If an error occurs during the execution of the command.
     */
    public static void main(String[] args) throws ByteBrewException{
        new ByteBrew().run();
    }
}