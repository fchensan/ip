package duke.commands;

import duke.TaskList;
import duke.TextUi;

/**
 * Represents an exit command.
 */
public class CommandExit extends Command {
    @Override
    public void execute(TaskList tasks, TextUi ui) {
        ui.printByeMessage();
    }
}
