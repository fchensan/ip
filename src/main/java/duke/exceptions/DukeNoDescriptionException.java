package duke.exceptions;

/**
 * Thrown when the user does not input the description of a Task.
 */
public class DukeNoDescriptionException extends DukeException {

    public DukeNoDescriptionException() {

    }

    public DukeNoDescriptionException(String itemType) {
        super("The description of a " + itemType + " cannot be empty.");
    }
}
