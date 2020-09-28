package duke.commands;

import duke.TextUi;
import duke.exceptions.DukeException;
import duke.parsers.TaskParser;
import duke.task.Deadline;
import duke.task.TaskList;

public class CommandAddDeadline extends CommandAddTask {
    public static final String DEFAULT_KEYWORD = "deadline";

    private Deadline deadline;

    public CommandAddDeadline() {
        this.keyword = DEFAULT_KEYWORD;
    }

    @Override
    public void setup(String input) throws DukeException {
        deadline = TaskParser.parseDeadline(input);
    }

    @Override
    public void execute(TaskList tasks, TextUi ui) {
        tasks.add(deadline);
        ui.printMessage("Added: " + deadline.toString());
    }
}
