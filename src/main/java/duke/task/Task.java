package duke.task;

public class Task {
    private static final String CHECKMARK_DONE = "[DONE]";
    private static final String CHECKMARK_NOT_DONE = "[    ]";

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? CHECKMARK_DONE : CHECKMARK_NOT_DONE); //return tick or X symbols
    }

    public String getIdentifier() {
        return "?";
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}