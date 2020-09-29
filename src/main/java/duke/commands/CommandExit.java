package duke.commands;

import duke.TextUi;
import duke.task.TaskList;

/**
 * Represents an exit command.
 */
public class CommandExit extends Command {
    public static final String DEFAULT_KEYWORD = "bye";

    public CommandExit() {
        this.keyword = DEFAULT_KEYWORD;
    }

    @Override
    public void setup(String input) {

    }

    @Override
    public void execute(TaskList tasks, TextUi ui) {
        ui.printByeMessage();
    }
}
