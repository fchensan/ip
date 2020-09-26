package duke.task;

import java.time.LocalDate;

public class Event extends Task {
    public static final String IDENTIFIER = "E";

    protected LocalDate at;

    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at);
    }

    public Event(boolean isDone, String description, String at) {
        super(isDone, description);
        this.at = LocalDate.parse(at);
    }

    @Override
    public String getIdentifier() {
        return IDENTIFIER;
    }

    public LocalDate getAt() {
        return at;
    }

    @Override
    public String toString() {
        return "[" + IDENTIFIER + "] " + super.toString() + " (at: " + at + ")";
    }
}
