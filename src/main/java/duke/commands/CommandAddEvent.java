package duke.commands;

import duke.TextUi;
import duke.task.Event;
import duke.task.TaskList;

public class CommandAddEvent extends CommandAddTask {
    private Event event;

    public CommandAddEvent(Event event) {
        this.event = event;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui) {
        tasks.add(event);
        ui.printMessage("Added: " + event.toString());
    }
}
