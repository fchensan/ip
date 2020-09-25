package duke;

import org.w3c.dom.Text;

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
