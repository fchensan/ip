package duke.commands;

import duke.exceptions.DukeException;
import duke.task.TaskList;
import duke.TextUi;

/**
 * Represents a command to print the task list.
 */
public class CommandPrintTaskList extends Command {
    public static final String DEFAULT_KEYWORD = "list";

    public CommandPrintTaskList() {
        this.keyword = DEFAULT_KEYWORD;
    }

    @Override
    public void setup(String input) {

    }

    @Override
    public void execute(TaskList tasks, TextUi ui) {
        ui.printTasksList(tasks, true);
    }
}
