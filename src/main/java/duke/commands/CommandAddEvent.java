package duke.commands;

import duke.TextUi;
import duke.exceptions.DukeException;
import duke.parsers.TaskParser;
import duke.task.Event;
import duke.task.TaskList;

public class CommandAddEvent extends CommandAddTask {
    public static final String DEFAULT_KEYWORD = "event";

    private Event event;

    public CommandAddEvent() {
        this.keyword = DEFAULT_KEYWORD;
    }

    public void setup(String input) throws DukeException {
        event = TaskParser.parseEvent(input);
    }

    @Override
    public void execute(TaskList tasks, TextUi ui) {
        tasks.add(event);
        ui.printMessage("Added: " + event.toString());
    }
}
