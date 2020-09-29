package duke.task;

import java.time.LocalDateTime;

public class Event extends Task {
    public static final String IDENTIFIER = "E";

    private LocalDateTime at;

    public Event(String description, String at) {
        super(description);
        this.at = LocalDateTime.parse(at);
    }

    public Event(boolean isDone, String description, String at) {
        super(isDone, description);
        this.at = LocalDateTime.parse(at);
    }

    @Override
    public String getIdentifier() {
        return IDENTIFIER;
    }

    public LocalDateTime getAt() {
        return at;
    }

    @Override
    public String toString() {
        return "[" + IDENTIFIER + "] " + super.toString() + " (at: " + at + ")";
    }
}
