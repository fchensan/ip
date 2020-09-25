package duke.task;

import duke.task.Task;

public class Event extends Task {
    public static final String IDENTIFIER = "E";

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(boolean isDone, String description, String at) {
        super(isDone, description);
        this.at = at;
    }

    @Override
    public String getIdentifier() {
        return IDENTIFIER;
    }

    public String getAt() {
        return at;
    }

    @Override
    public String toString() {
        return "[" + IDENTIFIER + "] " + super.toString() + " (at: " + at + ")";
    }
}
