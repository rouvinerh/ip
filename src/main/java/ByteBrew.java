import tasks.TaskList;
import utility.Ui;
import storage.Storage;
import utility.Parse;
import bytebrew.ByteBrewException;
import command.Command;

public class ByteBrew{
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public ByteBrew(){
        this.tasks = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage();
    }

    public void run() throws ByteBrewException {
        ui.startUp();
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

    public static void main(String[] args) throws ByteBrewException{
        new ByteBrew().run();
    }
}