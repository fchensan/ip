package duke.exceptions;

public class DukeNoDescriptionException extends DukeException {

    public DukeNoDescriptionException() {

    }

    public DukeNoDescriptionException(String itemType) {
        super("The description of a " + itemType + " cannot be empty.");
    }
}
