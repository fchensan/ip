package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.DukeIndexOutOfBoundsException;
import duke.exceptions.DukeNoArgumentException;
import duke.task.Task;
import duke.task.TaskList;
import duke.TextUi;

/**
 * Represents a command to delete a Task.
 */
public class CommandDeleteTask extends Command {
    public static final String DEFAULT_KEYWORD = "delete";

    private int taskIndex;

    public CommandDeleteTask() {
        this.keyword = DEFAULT_KEYWORD;
    }

    @Override
    public void setup(String input) throws DukeException {
        try {
            // Get the second word (the task number), convert to int, then subtract 1 to make the index zero-based.
            taskIndex = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            throw new DukeNoArgumentException("task number");
        }
    }

    @Override
    public void execute(TaskList tasks, TextUi ui) throws DukeIndexOutOfBoundsException {
        if (tasks.size() == 0) {
            ui.printMessage("You have no tasks.");
            return;
        }

        Task removedTask;

        try {
            removedTask = tasks.remove(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeIndexOutOfBoundsException("task number", 1, tasks.size());
        }

        ui.printTaskDeleted(removedTask);
        ui.printNumberOfTasksLeft(tasks.size());
    }
}
