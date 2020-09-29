package duke.commands;

import duke.TextUi;
import duke.task.Task;
import duke.task.TaskList;

public class CommandFind extends Command {
    public static final String DEFAULT_KEYWORD = "find";

    private String searchPhrase;

    public CommandFind() {
        this.keyword = DEFAULT_KEYWORD;
    }

    @Override
    public void setup(String input) {
        this.searchPhrase = input;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui) {
        TaskList matchingTasks = new TaskList();

        for (Task task : tasks) {
            if (task.getDescription().contains(searchPhrase)) {
                matchingTasks.add(task);
            }
        }

        ui.printMessage("Here are some matching tasks:");
        ui.printTasksList(matchingTasks);
    }
}
