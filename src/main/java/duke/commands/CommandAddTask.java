package duke.commands;

import duke.exceptions.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.TextUi;

/**
 * Represents an add Task command.
 */
public class CommandAddTask extends Command{
    public static final String DEFAULT_KEYWORD = "task";

    private Task task;

    public CommandAddTask(Task task) {
        this.task = task;
    }

    public CommandAddTask() {

    }

    @Override
    public void setup(String input) throws DukeException {

    }

    @Override
    public void execute(TaskList tasks, TextUi ui) {
        tasks.add(task);
        ui.printMessage("Added: " + task.toString());
    }
}
