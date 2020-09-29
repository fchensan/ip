package duke;

import duke.commands.*;
import duke.exceptions.DukeException;
import duke.parsers.Parser;
import duke.parsers.TaskParser;
import duke.task.TaskList;

import java.time.LocalDate;
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

    private static void showTasksForToday() {
        TaskList tasksForToday = tasks.getTasksByDate(LocalDate.now());

        if (tasksForToday.size() == 0) {
            ui.printMessage("You have no deadlines or events today!");
        } else {
            ui.printMessage("Here are your deadlines or events for today: ");
            ui.printTasksList(tasksForToday, false);
        }
    }

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

            storage.save(tasks);
        } while (!(command instanceof CommandExit));
    }

    public static void main(String[] args) {
        ui.printDukeLogo();
        ui.printWelcomeMessage();

        if (!storage.performFileSetup(tasks)) {
            return;
        }

        showTasksForToday();

        performInputLoop();
    }
}
