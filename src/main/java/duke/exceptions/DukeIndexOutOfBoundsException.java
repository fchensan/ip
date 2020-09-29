package duke.exceptions;

public class DukeIndexOutOfBoundsException extends DukeException {
    public DukeIndexOutOfBoundsException(String variableName, int startIndex, int lastIndex) {
        super("Index for " + variableName + " should be within the range from " + startIndex + " to " + lastIndex);
    }
}
