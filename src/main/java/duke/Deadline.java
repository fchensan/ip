package duke;

public class Deadline extends Task {
    public static final String IDENTIFIER = "D";

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(boolean isDone, String description, String by) {
        super(isDone, description);
        this.by = by;
    }

    @Override
    public String getIdentifier() {
        return IDENTIFIER;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[" + IDENTIFIER + "] " + super.toString() + " (by: " + by + ")";
    }
}