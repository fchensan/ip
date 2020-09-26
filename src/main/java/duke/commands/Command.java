package duke.commands;

import duke.TaskList;
import duke.TextUi;

/**
 * Represents a user command.
 */
public abstract class Command {
    public abstract void execute(TaskList tasks, TextUi ui);
}
