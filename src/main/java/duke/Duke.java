package duke;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private static final String STORAGE_FILEPATH = "duke-data.txt";

    private final static int EXIT = 0;
    private final static int OK = 1;

    private static TextUi ui;
    private static Storage storage;
    private static Parser parser;

    private static TaskList tasks;

    private static void performInputLoop() {
        String input;
        Command command;

        do {
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
        ui.printDividerLine();

        if (!storage.performFileSetup(tasks)) {
            return;
        }
        performInputLoop();
        storage.performSavingOperations(tasks);
    }
}
