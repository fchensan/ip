package duke.commands;

import duke.TextUi;
import duke.exceptions.DukeException;
import duke.parsers.TaskParser;
import duke.task.Event;
import duke.task.TaskList;

/**
 * Represents a command to add an Event.
 */
public class CommandAddEvent extends CommandAddTask {
    public static final String DEFAULT_KEYWORD = "event";

    private Event event;

    private TaskParser taskParser;

    public CommandAddEvent(TaskParser taskParser) {
        this.keyword = DEFAULT_KEYWORD;
        this.taskParser = taskParser;
    }

    public void setup(String input) throws DukeException {
        event = taskParser.parseEvent(input);
    }

    @Override
    public void execute(TaskList tasks, TextUi ui) {
        tasks.add(event);
        ui.printMessage("Added: " + event.toString());
    }
}
