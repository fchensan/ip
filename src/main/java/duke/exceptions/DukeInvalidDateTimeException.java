package duke.exceptions;

/**
 * Thrown when the user inputs date and time in an invalid format.
 */
public class DukeInvalidDateTimeException extends DukeException {
    public DukeInvalidDateTimeException() {
        super("Please insert the date and time in this format: yyyy-MM-dd HH:mm");
    }
}
