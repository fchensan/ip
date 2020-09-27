package duke.commands;

import duke.task.Task;
import duke.task.TaskList;
import duke.TextUi;

/**
 * Represents an add Task command.
 */
public class CommandAddTask extends Command{
    private Task task;

    public CommandAddTask(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui) {
        tasks.add(task);
        ui.printMessage("Added: " + task.toString());
    }
}
