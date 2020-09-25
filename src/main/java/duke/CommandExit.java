package duke;

public class CommandExit extends Command {
    @Override
    public void execute(TaskList tasks, TextUi ui) {
        ui.printByeMessage();
    }
}
