package duke.exceptions;

public class DukeInvalidDateTimeException extends DukeException {
    public DukeInvalidDateTimeException() {
        super("Please insert the date and time in this format: yyyy-MM-dd HH:mm");
    }
}
