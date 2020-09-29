package duke.exceptions;

/**
 * Thrown when the user inputs an invalid index that is out of bounds.
 */
public class DukeNoArgumentException extends DukeException {
    public DukeNoArgumentException(String identifier) {
        super("Please provide " + identifier);
    }
}
