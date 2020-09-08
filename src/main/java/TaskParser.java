public class TaskParser {
    public TaskParser() {

    }

    private static String generateDescriptionError(String itemType) {
        return "The description of a " + itemType + " cannot be empty.";
    }

    private static String parseDescription(String input) throws DukeNoDescriptionException {
        String description;
        int argumentStartIndex = input.indexOf("/");

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

    private static String parseArgument(String input, String identifier) {
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

    public static Deadline parseDeadline(String input) throws DukeNoDescriptionException {
        String description;

        try {
            description = parseDescription(input);
        } catch (DukeNoDescriptionException e) {
            throw new DukeNoDescriptionException(generateDescriptionError("deadline"));
        }

        String by = parseArgument(input, "/by");

        return new Deadline(description, by);
    }

    public static Event parseEvent(String input) throws DukeNoDescriptionException {
        String description;

        try {
            description = parseDescription(input);
        } catch (DukeNoDescriptionException e) {
            throw new DukeNoDescriptionException(generateDescriptionError("event"));
        }

        String time = parseArgument(input, "/at");

        return new Event(description, time);
    }
}
