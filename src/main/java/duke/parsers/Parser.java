package duke.parsers;

import duke.commands.Command;
import duke.commands.CommandNotFound;
import duke.exceptions.DukeException;

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
     * @return the Command associated with the user's input
     * @throws DukeException if an error occurs when calling the <code>setup</code> function of the Command instance.
     */
    public Command parse(String input) throws DukeException {
        String inputFirstWord = input.split(" ")[0];
        String inputWithoutCommand = input.substring(inputFirstWord.length()).trim();

        for (Command command: commandList) {
            if (command.keyword.equals(inputFirstWord)) {
                command.setup(inputWithoutCommand);
                return command;
            }
        }

        return new CommandNotFound();
    }
}
