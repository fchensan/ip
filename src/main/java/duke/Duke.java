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

    private static TaskList tasks;

    private static void handleAddTaskInput(String input) throws DukeException {
        Task task;
        String inputFirstWord = input.split(" ")[0];
        String inputWithoutCommand = input.substring(inputFirstWord.length());

        switch (inputFirstWord) {
        case "todo":
            task = TaskParser.parseTodo(inputWithoutCommand);
            break;
        case "deadline":
            task = TaskParser.parseDeadline(inputWithoutCommand);
            break;
        case "event":
            task = TaskParser.parseEvent(inputWithoutCommand);
            break;
        default:
            ui.printMessage("Hmm, I'm not sure what that means...");
            return;
        }

        tasks.add(task);

        ui.printMessage("Added: " + input);
    }

    private static void handleMarkTaskDone(String input) {
        // Get the second word (the task number), convert to int, then subtract 1 to make the index zero-based.
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;

        tasks.markAsDone(taskIndex);

        ui.printTaskMarkedAsDone(tasks.get(taskIndex));
    }

    private static void handleDeleteTask(String input) {
        // Get the second word (the task number), convert to int, then subtract 1 to make the index zero-based.
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;

        Task removedTask = tasks.remove(taskIndex);

        ui.printTaskDeleted(removedTask);
        ui.printNumberOfTasksLeft(tasks.size());
    }

    private static int handleInput(String input) {
        if (input.equals("bye")) {
            ui.printByeMessage();
            return EXIT;
        } else if (input.startsWith("done")) {
            handleMarkTaskDone(input);
        } else if (input.startsWith("delete")) {
            handleDeleteTask(input);
        } else if (input.equals("list")) {
            ui.printTasksList(tasks);
        } else {
            try {
                handleAddTaskInput(input);
            } catch (DukeException e) {
                ui.printErrorMessage(e.getMessage());
            }
        }

        ui.printDividerLine();
        return OK;
    }

    private static void performInputLoop() {
        String input;

        do {
            input = ui.getUserInput();
        } while (handleInput(input) != EXIT);
    }

    public static void main(String[] args) {
        ui = new TextUi();
        storage = new Storage(ui, STORAGE_FILEPATH);
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
