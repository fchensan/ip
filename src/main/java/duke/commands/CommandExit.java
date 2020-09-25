package duke.commands;

import duke.TaskList;
import duke.TextUi;

public class CommandExit extends Command {
    @Override
    public void execute(TaskList tasks, TextUi ui) {
        ui.printByeMessage();
    }
}
