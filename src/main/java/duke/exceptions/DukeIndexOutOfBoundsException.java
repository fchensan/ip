package duke.exceptions;

/**
 * Thrown when the user inputs an invalid index that is out of bounds.
 */
public class DukeIndexOutOfBoundsException extends DukeException {
    public DukeIndexOutOfBoundsException(String variableName, int startIndex, int lastIndex) {
        super("Index for " + variableName + " should be within the range from " + startIndex + " to " + lastIndex);
    }
}
