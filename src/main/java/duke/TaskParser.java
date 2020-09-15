package duke;

public class TaskParser {
    public TaskParser() {

    }

    private static String generateDescriptionError(String itemType) {
        return "The description of a " + itemType + " cannot be empty.";
    }

    private static String parseDescription(String input) throws DukeNoDescriptionException {
        input = input.trim();
        String description;
        int argumentStartIndex = input.indexOf("/");

        // If no /argument is given, the entire string is the description.
        if (argumentStartIndex == -1) {
            description = input;
        } else {
            description = input.substring(0, argumentStartIndex - 1);
        }

        if (description.trim().length() == 0) {
            throw new DukeNoDescriptionException();
        }

        return description;
    }

    private static String parseArgument(String input, String identifier) throws DukeNoArgumentException {
        int identifierIndex = input.indexOf(identifier);

        if (identifierIndex == -1) {
            throw new DukeNoArgumentException();
        }

        int startIndex = input.indexOf(identifier) + identifier.length() + 1;
        return input.substring(startIndex);
    }

    public static Task parseTask(String input) {
        return new Task(input);
    }

    public static Todo parseTodo(String input) throws DukeNoDescriptionException {
        String description;

        try {
            description = parseDescription(input);
        } catch (DukeNoDescriptionException e) {
            throw new DukeNoDescriptionException(generateDescriptionError("todo"));
        }

        return new Todo(description);
    }

    public static Deadline parseDeadline(String input) throws DukeException {
        String description, by;

        try {
            description = parseDescription(input);
        } catch (DukeNoDescriptionException e) {
            throw new DukeNoDescriptionException(generateDescriptionError("deadline"));
        }

        try {
            by = parseArgument(input, "/by");
        } catch (DukeNoArgumentException e) {
            throw new DukeNoArgumentException("Please provide /by");
        }

        return new Deadline(description, by);
    }

    public static Event parseEvent(String input) throws DukeException {
        String description, time;

        try {
            description = parseDescription(input);
        } catch (DukeNoDescriptionException e) {
            throw new DukeNoDescriptionException(generateDescriptionError("event"));
        }

        try {
            time = parseArgument(input, "/at");
        } catch (DukeNoArgumentException e) {
            throw new DukeNoArgumentException("Please provide /at");
        }

        return new Event(description, time);
    }
}
