package duke;

import duke.commands.Command;
import duke.commands.CommandExit;
import duke.exceptions.DukeException;
import duke.parsers.Parser;
import duke.task.TaskList;

public class Duke {
    private static final String STORAGE_FILEPATH = "duke-data.txt";

    private static TextUi ui;
    private static Storage storage;
    private static Parser parser;

    private static TaskList tasks;

    private static void performInputLoop() {
        String input;
        Command command;

        do {
            ui.printDividerLine();
            input = ui.getUserInput();

            try {
                command = parser.parse(input);
            } catch (DukeException e) {
                ui.printErrorMessage(e.getMessage());
                command = new CommandExit();
            }

            command.execute(tasks, ui);
        } while (!(command instanceof CommandExit));
    }

    public static void main(String[] args) {
        ui = new TextUi();
        storage = new Storage(ui, STORAGE_FILEPATH);
        parser = new Parser();
        tasks = new TaskList();

        ui.printDukeLogo();
        ui.printWelcomeMessage();

        if (!storage.performFileSetup(tasks)) {
            return;
        }
        performInputLoop();
        storage.performSavingOperations(tasks);
    }
}
