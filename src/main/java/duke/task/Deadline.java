package duke.task;

import duke.Duke;
import duke.exceptions.DukeInvalidDateTimeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    public static final String IDENTIFIER = "D";

    private LocalDateTime by;
    private DateTimeFormatter dateTimeFormatter;

    public Deadline(String description, LocalDateTime by, DateTimeFormatter dateTimeFormatter) {
        super(description);
        this.by = by;
        this.dateTimeFormatter = dateTimeFormatter;
    }

    public Deadline(boolean isDone, String description, LocalDateTime by, DateTimeFormatter dateTimeFormatter) {
        super(isDone, description);
        this.by = by;
        this.dateTimeFormatter = dateTimeFormatter;
    }

    @Override
    public String getIdentifier() {
        return IDENTIFIER;
    }

    public LocalDateTime getBy() {
        return by;
    }

    public String toString() {
        return "[" + IDENTIFIER + "] " + super.toString() + " (by: " + by.format(dateTimeFormatter) + ")";
    }
}