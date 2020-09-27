package duke.commands;

import duke.TextUi;
import duke.task.TaskList;
import duke.task.Todo;

public class CommandAddTodo extends CommandAddTask {
    private Todo todo;

    public CommandAddTodo(Todo todo) {
        this.todo = todo;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui) {
        tasks.add(todo);
        ui.printMessage("Added: " + todo.toString());
    }
}
