package duke;

public class DukeException extends Exception {
    public String message;

    public DukeException() {

    }

    public DukeException(String message) {
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

}
