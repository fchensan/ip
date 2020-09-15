package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private static final String STORAGE_FILEPATH = "duke-data.txt";

    private final static int EXIT = 0;
    private final static int OK = 1;

    private static File storageFile;

    private static Task[] tasks = new Task[100];
    private static int numTasks = 0;

    private static void printDukeLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    private static void printWelcomeMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    private static void printDividerLine() {
        System.out.println("____________________________________________________________");
    }

    private static void printByeMessage() {
        System.out.println("Good bye!");
    }

    private static void addTask(Task task) {
        tasks[numTasks] = task;
        numTasks++;
    }

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
            System.out.println("Hmm, I'm not sure what that means...");
            return;
        }

        addTask(task);

        System.out.println("Added: " + input);
    }

    private static void printTasksList() {
        for (int i = 0; i < numTasks; i++) {
            System.out.print(i + 1 + ". ");
            System.out.println(tasks[i].toString());
        }
    }

    private static void handleMarkTaskDone(String input) {
        // Get the second word (the task number), convert to int, then subtract 1 to make the index zero-based.
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;

        tasks[taskIndex].markAsDone();

        System.out.println("Ok! \"" + tasks[taskIndex].getDescription() + "\" is marked as done!");
    }

    private static int handleInput(String input) {
        if (input.equals("bye")) {
            printByeMessage();
            return EXIT;
        } else if (input.startsWith("done")) {
            handleMarkTaskDone(input);
        } else if (input.equals("list")) {
            printTasksList();
        } else {
            try {
                handleAddTaskInput(input);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }

        printDividerLine();
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

            addTask(deserializeTask(currLine));
        }
    }

    private static void saveDataToFile() throws IOException {
        FileWriter fw = new FileWriter(storageFile);

        Task task;
        String textToWrite = "";

        for (int i = 0; i < numTasks; i++) {
            task = tasks[i];
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
            System.out.println("Unable to perform file setup.");
            return false;
        }

        return true;
    }

    private static void performSavingOperations() {
        try {
            saveDataToFile();
        } catch (IOException e) {
            System.out.println("Error when saving file.");
        }
    }

    private static void performInputLoop() {
        Scanner in = new Scanner(System.in);
        String input;

        do {
            System.out.print(">> ");
            input = in.nextLine();
        } while (handleInput(input) != EXIT);
    }

    public static void main(String[] args) {
        printDukeLogo();
        printWelcomeMessage();
        printDividerLine();

        if (!performFileSetup()) {
            return;
        }
        performInputLoop();
        performSavingOperations();
    }
}
