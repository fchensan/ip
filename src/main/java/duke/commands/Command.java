package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.DukeNoDescriptionException;
import duke.task.TaskList;
import duke.TextUi;

/**
 * Represents a user command.
 */
public abstract class Command {
    public static final String DEFAULT_KEYWORD = "keyword";

    public String keyword;

    public Command(){
        this.keyword = DEFAULT_KEYWORD;
    }

    public abstract void setup(String input) throws DukeException;
    public abstract void execute(TaskList tasks, TextUi ui) throws DukeException;
}
