package duke;

public abstract class Command {
    public abstract void execute(TaskList tasks, TextUi ui);
}
