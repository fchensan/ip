package duke.commands;

import duke.TaskList;
import duke.TextUi;

public class CommandPrintTaskList extends Command{
    @Override
    public void execute(TaskList tasks, TextUi ui) {
        ui.printTasksList(tasks);
    }
}
