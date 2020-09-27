package duke.commands;

import duke.task.TaskList;
import duke.TextUi;

/**
 * Represents a command to print the task list.
 */
public class CommandPrintTaskList extends Command{
    @Override
    public void execute(TaskList tasks, TextUi ui) {
        ui.printTasksList(tasks);
    }
}
