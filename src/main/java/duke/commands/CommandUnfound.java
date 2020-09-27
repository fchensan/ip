package duke.commands;

import duke.exceptions.DukeException;
import duke.task.TaskList;
import duke.TextUi;

/**
 * Represents an unrecognized command.
 */
public class CommandUnfound extends Command{

    @Override
    public void setup(String input) throws DukeException {

    }

    @Override
    public void execute(TaskList tasks, TextUi ui) {
        ui.printMessage("Hmm, I'm not sure what that means...");
    }
}
