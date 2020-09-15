package duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final static int EXIT = 0;
    private final static int OK = 1;

    private static ArrayList<Task> tasks = new ArrayList<>();

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

        tasks.add(task);

        System.out.println("Added: " + input);
    }

    private static void printTasksList() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print(i + 1 + ". ");
            System.out.println(tasks.get(i).toString());
        }
    }

    private static void handleMarkTaskDone(String input) {
        // Get the second word (the task number), convert to int, then subtract 1 to make the index zero-based.
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;

        tasks.get(taskIndex).markAsDone();

        System.out.println("Ok! \"" + tasks.get(taskIndex).getDescription() + "\" is marked as done!");
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

    public static void main(String[] args) {
        printDukeLogo();
        printWelcomeMessage();
        printDividerLine();

        Scanner in = new Scanner(System.in);
        String input;

        do {
            System.out.print(">> ");
            input = in.nextLine();
        } while (handleInput(input) != EXIT);
    }
}
