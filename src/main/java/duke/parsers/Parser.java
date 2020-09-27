package duke.parsers;

import duke.commands.*;
import duke.exceptions.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Parses the user's input.
 */
public class Parser {
    /**
     * Parses the user input and returns a Command that should be executed.
     *
     * @param input the raw String input that the user types in
     * @return a Command associated with the user's input
     * @throws DukeException
     */
    public Command parse(String input) throws DukeException {
        int taskIndex;
        String inputFirstWord = input.split(" ")[0];
        String inputWithoutCommand = input.substring(inputFirstWord.length()).trim();

        switch (inputFirstWord) {
        case "bye":
            return new CommandExit();
        case "done":
            // Get the second word (the task number), convert to int, then subtract 1 to make the index zero-based.
            taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            return new CommandMarkDone(taskIndex);
        case "delete":
            taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            return new CommandDeleteTask(taskIndex);
        case "list":
            return new CommandPrintTaskList();
        case "find":
            return new CommandFind(inputWithoutCommand);
        case "todo":
            Todo todo = TaskParser.parseTodo(inputWithoutCommand);
            return new CommandAddTodo(todo);
        case "deadline":
            Deadline deadline = TaskParser.parseDeadline(inputWithoutCommand);
            return new CommandAddDeadline(deadline);
        case "event":
            Event event = TaskParser.parseEvent(inputWithoutCommand);
            return new CommandAddEvent(event);
        default:
            return new CommandUnfound();
        }
    }
}
