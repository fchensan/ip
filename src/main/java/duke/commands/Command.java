package duke.commands;

import duke.TextUi;
import duke.exceptions.DukeException;
import duke.task.TaskList;

/**
 * Represents a user command.
 */
public abstract class Command {
    public static final String DEFAULT_KEYWORD = "keyword";

    public String keyword;

    public Command(){
        this.keyword = DEFAULT_KEYWORD;
    }

    /**
     * Perform all the necessary setup and input parsing for the command.
     *
     * @param input raw user input
     * @throws DukeException if there user input is invalid or an error occurs while executing command
     */
    public abstract void setup(String input) throws DukeException;
    public abstract void execute(TaskList tasks, TextUi ui) throws DukeException;
}
