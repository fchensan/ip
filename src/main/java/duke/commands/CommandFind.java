package duke.commands;

import duke.TaskList;
import duke.TextUi;
import duke.task.Task;

public class CommandFind extends Command {
    private String searchPhrase;

    public CommandFind(String searchPhrase) {
        this.searchPhrase = searchPhrase;
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
