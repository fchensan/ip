package duke.task;

import java.time.LocalDate;

public class Deadline extends Task {
    public static final String IDENTIFIER = "D";

    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    public Deadline(boolean isDone, String description, String by) {
        super(isDone, description);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String getIdentifier() {
        return IDENTIFIER;
    }

    public LocalDate getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[" + IDENTIFIER + "] " + super.toString() + " (by: " + by + ")";
    }
}