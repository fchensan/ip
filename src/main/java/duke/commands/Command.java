package duke.commands;

import duke.TaskList;
import duke.TextUi;

public abstract class Command {
    public abstract void execute(TaskList tasks, TextUi ui);
}
