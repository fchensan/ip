package duke.commands;

import duke.TextUi;
import duke.task.Deadline;
import duke.task.TaskList;

public class CommandAddDeadline extends CommandAddTask {
    private Deadline deadline;

    public CommandAddDeadline(Deadline deadline) {
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui) {
        tasks.add(deadline);
        ui.printMessage("Added: " + deadline.toString());
    }
}
