package duke;

public class CommandPrintTaskList extends Command{
    @Override
    public void execute(TaskList tasks, TextUi ui) {
        ui.printTasksList(tasks);
    }
}
