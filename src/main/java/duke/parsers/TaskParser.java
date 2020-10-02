package duke.parsers;

import duke.exceptions.DukeInvalidDateTimeException;
import duke.exceptions.DukeNoArgumentException;
import duke.exceptions.DukeNoDescriptionException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Returns a new instance of a Task with a description and attribute, based on raw user input.
 */
public class TaskParser {
    private DateTimeFormatter dateTimeFormatter;

    public TaskParser(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }

    /**
     * Given a Task input in the form of "DESCRIPTION /identifier ARGUMENT", returns the description.
     *
     * @param input    user input string
     * @param itemType the name of the Task type to be used in error messages
     * @return the description part of the user input
     * @throws DukeNoDescriptionException if the input is missing the description part
     */
    private static String parseDescription(String input, String itemType) throws DukeNoDescriptionException {
        String description;
        int argumentStartIndex = input.indexOf("/");

        if (argumentStartIndex == 0 || input.length() == 0) {
            throw new DukeNoDescriptionException(itemType);
        } else if (argumentStartIndex != -1) {
            description = input.substring(0, argumentStartIndex - 1);
        } else {
            description = input;
        }

        return description;
    }

    /**
     * Given a Task input in the form of "DESCRIPTION /identifier ARGUMENT", returns the argument.
     *
     * @param input      user input string
     * @param identifier the identifier, such as "/at" or "/by"
     * @return the argument part of the user input
     * @throws DukeNoArgumentException if the input is missing the argument part
     */
    private static String parseArgument(String input, String identifier) throws DukeNoArgumentException {
        int identifierIndex = input.indexOf(identifier);

        if (identifierIndex == -1) {
            throw new DukeNoArgumentException(identifier);
        }

        int startIndex = input.indexOf(identifier) + identifier.length() + 1;
        return input.substring(startIndex);
    }

    private LocalDateTime parseDateTimeArgument(String input, String identifier)
            throws DukeNoArgumentException, DukeInvalidDateTimeException {

        String timeInString = parseArgument(input, identifier);
        LocalDateTime dateTime;

        try {
            dateTime = LocalDateTime.parse(timeInString, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateTimeException();
        }

        return dateTime;
    }

    /**
     * Creates a new Todo object with the user input as the description.
     *
     * @param input the user input string, which will be the description
     * @return a new Task object
     * @throws DukeNoDescriptionException if the input does not contain the description
     */
    public Todo parseTodo(String input) throws DukeNoDescriptionException {

        String description = parseDescription(input, "todo");

        return new Todo(description);
    }

    /**
     * Creates a new Deadline object based on user input in the form of "DESCRIPTION /by DATETIME".
     *
     * @param input user input string
     * @return a new Deadline object
     * @throws DukeNoArgumentException      if the input does not contain the argument
     * @throws DukeInvalidDateTimeException if the input contains invalid datetime format
     * @throws DukeNoDescriptionException   if the input does not contain the description
     */
    public Deadline parseDeadline(String input) throws DukeNoArgumentException, DukeInvalidDateTimeException,
            DukeNoDescriptionException {

        String description = parseDescription(input, "deadline");
        LocalDateTime dateTime = parseDateTimeArgument(input, "/by");

        return new Deadline(description, dateTime, dateTimeFormatter);
    }

    /**
     * Creates a new Event object based on user input in the form of "DESCRIPTION /at DATETIME".
     *
     * @param input user input string
     * @return a new Event object
     * @throws DukeNoArgumentException      if the input does not contain the argument
     * @throws DukeInvalidDateTimeException if the input contains invalid datetime format
     * @throws DukeNoDescriptionException   if the input does not contain the description
     */
    public Event parseEvent(String input) throws DukeNoArgumentException, DukeInvalidDateTimeException,
            DukeNoDescriptionException {

        String description = parseDescription(input, "event");
        LocalDateTime dateTime = parseDateTimeArgument(input, "/at");

        return new Event(description, dateTime, dateTimeFormatter);
    }
}
