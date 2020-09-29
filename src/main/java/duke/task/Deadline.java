package duke.task;

import java.time.LocalDateTime;

public class Deadline extends Task {
    public static final String IDENTIFIER = "D";

    private LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by);
    }

    public Deadline(boolean isDone, String description, String by) {
        super(isDone, description);
        this.by = LocalDateTime.parse(by);
    }

    @Override
    public String getIdentifier() {
        return IDENTIFIER;
    }

    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[" + IDENTIFIER + "] " + super.toString() + " (by: " + by + ")";
    }
}