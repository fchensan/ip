package duke.task;

import duke.exceptions.DukeInvalidDateTimeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    public static final String IDENTIFIER = "E";

    private LocalDateTime at;
    private DateTimeFormatter dateTimeFormatter;

    public Event(String description, LocalDateTime at, DateTimeFormatter dateTimeFormatter) {
        super(description);
        this.at = at;
        this.dateTimeFormatter = dateTimeFormatter;
    }

    public Event(boolean isDone, String description, LocalDateTime at, DateTimeFormatter dateTimeFormatter) {
        super(isDone, description);
        this.at = at;
        this.dateTimeFormatter = dateTimeFormatter;
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
        return "[" + IDENTIFIER + "] " + super.toString() + " (at: " + at.format(dateTimeFormatter) + ")";
    }
}
