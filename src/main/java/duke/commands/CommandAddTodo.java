package duke.commands;

import duke.TextUi;
import duke.exceptions.DukeNoDescriptionException;
import duke.parsers.TaskParser;
import duke.task.TaskList;
import duke.task.Todo;

public class CommandAddTodo extends CommandAddTask {
    public static final String DEFAULT_KEYWORD = "todo";

    private Todo todo;

    private TaskParser taskParser;

    public CommandAddTodo(TaskParser taskParser) {
        this.keyword = DEFAULT_KEYWORD;
        this.taskParser = taskParser;
    }

    @Override
    public void setup(String input) throws DukeNoDescriptionException {
        todo = taskParser.parseTodo(input);
    }

    @Override
    public void execute(TaskList tasks, TextUi ui) {
        tasks.add(todo);
        ui.printMessage("Added: " + todo.toString());
    }
}
