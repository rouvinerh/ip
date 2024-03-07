import tasks.TaskList;
import utility.Ui;
import storage.Storage;
import utility.Parse;
import bytebrew.ByteBrewException;
import command.Command;

/**
 * Represents the ByteBrew bot.
 */
public class ByteBrew{
    protected Storage storage;
    protected TaskList tasks;
    protected Ui ui;

    /**
     * Constructs a new {@code ByteBrew} object.
     */
    public ByteBrew(){
        this.tasks = new TaskList();
        this.ui = new Ui();
    }

    /**
     * Runs the ByteBrew bot.
     */
    public void run() {
        Ui.startUp();
        Storage.processFile(this.tasks);
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parse.parseCommand(fullCommand);
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
     *
     * @param args Command-line arguments from the user.
     */
    public static void main(String[] args) {
        new ByteBrew().run();
    }
}