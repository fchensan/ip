package duke.commands;

import duke.task.Task;
import duke.TaskList;
import duke.TextUi;

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
