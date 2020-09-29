package duke.exceptions;

public class DukeNoArgumentException extends DukeException {
    public DukeNoArgumentException(String identifier) {
        super("Please provide " + identifier);
    }
}
