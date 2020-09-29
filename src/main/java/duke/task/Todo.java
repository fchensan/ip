package duke.task;

public class Todo extends Task {
    public static final String IDENTIFIER = "T";

    public Todo(String description) {
        super(description);
    }

    public Todo(boolean isDone, String description) {
        super(isDone, description);
    }

    @Override
    public String getIdentifier() {
        return IDENTIFIER;
    }

    @Override
    public String toString() {
        return "[" + IDENTIFIER + "] " + super.toString();
    }
}
