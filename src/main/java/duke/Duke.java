package duke;

import duke.commands.*;
import duke.exceptions.DukeException;
import duke.parsers.Parser;
import duke.parsers.TaskParser;
import duke.task.TaskList;

import java.time.format.DateTimeFormatter;

public class Duke {
    private static final String STORAGE_FILEPATH = "duke-data.txt";
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm";

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATETIME_FORMAT);
    private static TaskParser taskParser = new TaskParser(dateTimeFormatter);

    private static TextUi ui = new TextUi();
    private static Storage storage = new Storage(ui, STORAGE_FILEPATH, dateTimeFormatter);

    /**
     * A list of commands available to the user.
     */
    private static final Command[] commandList = {
            new CommandAddTodo(taskParser),
            new CommandAddDeadline(taskParser),
            new CommandAddEvent(taskParser),
            new CommandPrintTaskList(),
            new CommandMarkDone(),
            new CommandDeleteTask(),
            new CommandFind(),
            new CommandExit(),
    };

    private static Parser parser = new Parser(commandList);

    private static TaskList tasks = new TaskList();

    private static void performInputLoop() {
        String input;
        Command command = null;

        do {
            ui.printDividerLine();
            input = ui.getUserInput();

            try {
                command = parser.parse(input);
                command.execute(tasks, ui);
            } catch (DukeException e) {
                ui.printErrorMessage(e.getMessage());
            }

        } while (!(command instanceof CommandExit));
    }

    public static void main(String[] args) {
        ui.printDukeLogo();
        ui.printWelcomeMessage();

        if (!storage.performFileSetup(tasks)) {
            return;
        }
        performInputLoop();
        storage.save(tasks);
    }
}
