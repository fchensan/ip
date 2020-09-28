package duke.commands;

import duke.exceptions.DukeException;
import duke.task.TaskList;
import duke.TextUi;
import duke.task.Task;

public class CommandFind extends Command {
    public static final String DEFAULT_KEYWORD = "find";

    private String searchPhrase;

    public CommandFind() {
        this.keyword = DEFAULT_KEYWORD;
    }

    @Override
    public void setup(String input) throws DukeException {
        this.searchPhrase = input;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui) {
        TaskList matchingTasks = new TaskList();

        for (Task task : tasks) {
            if (task.getDescription().indexOf(searchPhrase) != -1) {
                matchingTasks.add(task);
            }
        }

        ui.printMessage("Here are some matching tasks:");
        ui.printTasksList(matchingTasks);
    }
}
