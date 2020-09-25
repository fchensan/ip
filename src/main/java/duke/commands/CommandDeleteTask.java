package duke.commands;

import duke.task.Task;
import duke.TaskList;
import duke.TextUi;

public class CommandDeleteTask extends Command {
    private int taskIndex;

    public CommandDeleteTask(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui) {
        Task removedTask = tasks.remove(taskIndex);

        ui.printTaskDeleted(removedTask);
        ui.printNumberOfTasksLeft(tasks.size());
    }
}
