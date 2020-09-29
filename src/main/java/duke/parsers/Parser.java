package duke.parsers;

import duke.commands.*;
import duke.exceptions.DukeException;

import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Parses the user's input.
 */
public class Parser {

    private Command[] commandList;

    public Parser(Command[] commandList) {
        this.commandList = commandList;
    }

    /**
     * Parses the user input and returns a Command from commandList that should be executed.
     *
     * @param input the raw String input that the user types in
     * @return a Command associated with the user's input
     * @throws DukeException
     */
    public Command parse(String input) throws DukeException {
        int taskIndex;
        String inputFirstWord = input.split(" ")[0];
        String inputWithoutCommand = input.substring(inputFirstWord.length()).trim();

        for (Command command: commandList) {
            if (command.keyword.equals(inputFirstWord)) {
                command.setup(inputWithoutCommand);
                return command;
            }
        }

        return new CommandUnfound();
    }
}
