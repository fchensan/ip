package duke;

public class CommandMarkDone extends Command{
    private int taskIndex;

    public CommandMarkDone(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui) {
        tasks.markAsDone(taskIndex);

        ui.printTaskMarkedAsDone(tasks.get(taskIndex));
    }
}
