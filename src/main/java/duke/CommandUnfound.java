package duke;

public class CommandUnfound extends Command{

    @Override
    public void execute(TaskList tasks, TextUi ui) {
        ui.printMessage("Hmm, I'm not sure what that means...");
    }
}
