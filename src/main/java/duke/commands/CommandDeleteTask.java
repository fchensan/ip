package duke.commands;

import duke.exceptions.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.TextUi;

/**
 * Represents a delete Task command.
 */
public class CommandDeleteTask extends Command {
    public static final String DEFAULT_KEYWORD = "delete";

    private int taskIndex;

    public CommandDeleteTask() {
        this.keyword = DEFAULT_KEYWORD;
    }

    @Override
    public void setup(String input) throws DukeException {
        // Get the second word (the task number), convert to int, then subtract 1 to make the index zero-based.
        taskIndex = Integer.parseInt(input) - 1;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui) {
        Task removedTask = tasks.remove(taskIndex);

        ui.printTaskDeleted(removedTask);
        ui.printNumberOfTasksLeft(tasks.size());
    }
}
