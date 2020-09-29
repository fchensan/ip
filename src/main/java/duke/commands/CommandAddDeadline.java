package duke.commands;

import duke.TextUi;
import duke.exceptions.DukeException;
import duke.parsers.TaskParser;
import duke.task.Deadline;
import duke.task.TaskList;

public class CommandAddDeadline extends CommandAddTask {
    public static final String DEFAULT_KEYWORD = "deadline";

    private Deadline deadline;

    private TaskParser taskParser;

    public CommandAddDeadline(TaskParser taskParser) {
        this.keyword = DEFAULT_KEYWORD;
        this.taskParser = taskParser;
    }

    @Override
    public void setup(String input) throws DukeException {
        deadline = taskParser.parseDeadline(input);
    }

    @Override
    public void execute(TaskList tasks, TextUi ui) {
        tasks.add(deadline);
        ui.printMessage("Added: " + deadline.toString());
    }
}
