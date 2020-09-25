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

    private static ArrayList<Task> tasks = new ArrayList<>();
    private static File storageFile;

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

        tasks.get(taskIndex).markAsDone();

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

    private static Task deserializeTask(String inputLine) {
        Task task = null;

        String[] lineSegments = inputLine.split(";");
        boolean isDone = lineSegments[1].equals("1");

        switch (lineSegments[0]) {
        case Todo.IDENTIFIER:
            task = new Todo(isDone, lineSegments[2]);
            break;
        case Deadline.IDENTIFIER:
            task = new Deadline(isDone, lineSegments[2], lineSegments[3]);
            break;
        case Event.IDENTIFIER:
            task = new Event(isDone, lineSegments[2], lineSegments[3]);
            break;
        default:
            break;
        }

        return task;
    }

    private static String serializeTask(Task task) {
        String line = "";

        line += task.getIdentifier() + ";";
        line += (task.isDone() ? 1 : 0) + ";";
        line += task.getDescription();

        if (task instanceof Deadline) {
            line += ";" + ((Deadline) task).getBy();
        } else if (task instanceof Event) {
            line += ";" + ((Event) task).getAt();
        }

        return line;
    }

    private static void readDataFromFile() throws FileNotFoundException {
        Scanner s = new Scanner(storageFile);
        String currLine;

        while (s.hasNext()) {
            currLine = s.nextLine();

            if (currLine == "") {
                return;
            }

            tasks.add(deserializeTask(currLine));
        }
    }

    private static void saveDataToFile() throws IOException {
        FileWriter fw = new FileWriter(storageFile);

        Task task;
        String textToWrite = "";

        for (int i = 0; i < tasks.size(); i++) {
            task = tasks.get(i);
            textToWrite += serializeTask(task) + System.lineSeparator();
        }

        fw.write(textToWrite);
        fw.close();
    }

    private static boolean performFileSetup() {
        try {
            storageFile = new File(STORAGE_FILEPATH);
            storageFile.createNewFile();

            readDataFromFile();
        } catch (IOException e) {
            ui.printErrorMessage("Unable to perform file setup.");
            return false;
        }

        return true;
    }

    private static void performSavingOperations() {
        try {
            saveDataToFile();
        } catch (IOException e) {
            ui.printErrorMessage("Unable to save file.");
        }
    }

    private static void performInputLoop() {
        String input;

        do {
            input = ui.getUserInput();
        } while (handleInput(input) != EXIT);
    }

    public static void main(String[] args) {
        ui = new TextUi();

        ui.printDukeLogo();
        ui.printWelcomeMessage();
        ui.printDividerLine();

        if (!performFileSetup()) {
            return;
        }
        performInputLoop();
        performSavingOperations();
    }
}
