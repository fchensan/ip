package duke.parsers;

import duke.commands.*;
import duke.exceptions.DukeException;
import duke.task.Task;

public class Parser {
    public Command parse(String input) throws DukeException {
        Task task = null;
        int taskIndex;
        String inputFirstWord = input.split(" ")[0];
        String inputWithoutCommand = input.substring(inputFirstWord.length());

        switch (inputFirstWord) {
        case "bye":
            return new CommandExit();
        case "done":
            // Get the second word (the task number), convert to int, then subtract 1 to make the index zero-based.
            taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            return new CommandMarkDone(taskIndex);
        case "delete":
            taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            return new CommandDeleteTask(taskIndex);
        case "list":
            return new CommandPrintTaskList();
        case "todo":
            task = TaskParser.parseTodo(inputWithoutCommand);
            break;
        case "deadline":
            task = TaskParser.parseDeadline(inputWithoutCommand);
            break;
        case "event":
            task = TaskParser.parseEvent(inputWithoutCommand);
            break;
        default:
            return new CommandUnfound();
        }

        return new CommandAddTask(task);
    }
}
