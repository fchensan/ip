package duke.commands;

import duke.exceptions.DukeException;
import duke.task.TaskList;
import duke.TextUi;

/**
 * Represents a command to mark a Task as done.
 */
public class CommandMarkDone extends Command{
    public static final String DEFAULT_KEYWORD = "done";

    private int taskIndex;

    public CommandMarkDone() {
        this.keyword = DEFAULT_KEYWORD;
    }

    @Override
    public void setup(String input) throws DukeException {
        // Get the second word (the task number), convert to int, then subtract 1 to make the index zero-based.
        taskIndex = Integer.parseInt(input) - 1;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui) {
        tasks.markAsDone(taskIndex);

        ui.printTaskMarkedAsDone(tasks.get(taskIndex));
    }
}
