public class TaskParser {
    public TaskParser(){

    }

    private static String parseDescription(String input) throws DukeException {
        String description;
        int argumentStartIndex = input.indexOf("/");

        if (argumentStartIndex == -1) {
            description = input;
        } else {
            description = input.substring(0, argumentStartIndex - 1);
        }

        if (description.trim().length() == 0) {
            throw new DukeException();
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

    public static Todo parseTodo(String input) throws DukeException {
        String description = parseDescription(input);

        return new Todo(description);
    }

    public static Deadline parseDeadline(String input) throws DukeException {
        String description = parseDescription(input);
        String by = parseArgument(input, "/by");

        return new Deadline(description, by);
    }

    public static Event parseEvent(String input) throws DukeException {
        String description = parseDescription(input);
        String time = parseArgument(input, "/at");

        return new Event(description, time);
    }
}