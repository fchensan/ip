package duke.commands;

import duke.TextUi;
import duke.task.TaskList;

/**
 * Represents an unrecognized command.
 */
public class CommandNotFound extends Command {

    @Override
    public void setup(String input) {

    }

    @Override
    public void execute(TaskList tasks, TextUi ui) {
        ui.printMessage("Hmm, I'm not sure what that means...");
    }
}
