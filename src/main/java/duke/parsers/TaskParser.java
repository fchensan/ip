package duke.parsers;

import duke.exceptions.DukeException;
import duke.exceptions.DukeNoArgumentException;
import duke.exceptions.DukeNoDescriptionException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Creates a new instance of a Task with a description and attribute, based on raw user input.
 */
public class TaskParser {
    public TaskParser() {

    }

    /**
     * Given a Task input in the form of "<description> /identifier <argument>", returns the description.
     *
     * @param input
     * @return
     * @throws DukeNoDescriptionException
     */
    private static String parseDescription(String input) throws DukeNoDescriptionException {
        input = input.trim();
        String description;
        int argumentStartIndex = input.indexOf("/");

        // If no /argument is given, the entire string is the description.
        if (argumentStartIndex == -1) {
            description = input;
        } else {
            description = input.substring(0, argumentStartIndex - 1);
        }

        if (description.trim().length() == 0) {
            throw new DukeNoDescriptionException();
        }

        return description;
    }

    /**
     * Given a Task input in the form of "<description> /identifier <argument>", returns the argument.
     *
     * @param input user input String
     * @param identifier the identifier as a string, such as "/at" or "/by"
     * @return the argument as a string
     * @throws DukeNoArgumentException
     */
    private static String parseArgument(String input, String identifier) throws DukeNoArgumentException {
        int identifierIndex = input.indexOf(identifier);

        if (identifierIndex == -1) {
            throw new DukeNoArgumentException();
        }

        int startIndex = input.indexOf(identifier) + identifier.length() + 1;
        return input.substring(startIndex);
    }

    /**
     * Creates a new Task object with the user input as the description.
     *
     * @param input the user input String which will be the description
     * @return a new Task object
     */
    public static Task parseTask(String input) {
        return new Task(input);
    }

    /**
     * Creates a new Todo object with the user input as the description.
     *
     * @param input the user input String which will be the description
     * @return a new Task object
     */
    public static Todo parseTodo(String input) throws DukeNoDescriptionException {
        String description;

        try {
            description = parseDescription(input);
        } catch (DukeNoDescriptionException e) {
            throw new DukeNoDescriptionException("todo");
        }

        return new Todo(description);
    }

    /**
     * Creates a new Deadline object based on user input in the form of "Title /by yyyy-mm-dd".
     *
     * @param input user input String
     * @return a new Deadline object
     * @throws DukeException
     */
    public static Deadline parseDeadline(String input) throws DukeException {
        String description, by;

        try {
            description = parseDescription(input);
        } catch (DukeNoDescriptionException e) {
            throw new DukeNoDescriptionException("deadline");
        }

        try {
            by = parseArgument(input, "/by");
        } catch (DukeNoArgumentException e) {
            throw new DukeNoArgumentException("Please provide /by");
        }

        return new Deadline(description, by);
    }

    /**
     * Creates a new Event object based on user input in the form of "Title /at yyyy-mm-dd".
     *
     * @param input user input String
     * @return a new Event object
     * @throws DukeException
     */
    public static Event parseEvent(String input) throws DukeException {
        String description, time;

        try {
            description = parseDescription(input);
        } catch (DukeNoDescriptionException e) {
            throw new DukeNoDescriptionException("event");
        }

        try {
            time = parseArgument(input, "/at");
        } catch (DukeNoArgumentException e) {
            throw new DukeNoArgumentException("Please provide /at");
        }

        return new Event(description, time);
    }
}
