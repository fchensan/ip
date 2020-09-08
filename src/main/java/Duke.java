import java.util.Scanner;

public class Duke {
    private final static int EXIT = 0;
    private final static int OK = 1;

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

    private static String removeCommandWord(String input) {
        String inputWithoutCommand = input;

        int firstSpaceIndex = input.indexOf(" ");
        if (firstSpaceIndex != -1) {
            inputWithoutCommand = input.substring(firstSpaceIndex + 1);
        }

        return inputWithoutCommand;
    }

    private static void handleAddTaskInput(String input) {
        Task task;
        String inputFirstWord = input.split(" ")[0];
        String inputWithoutCommand = removeCommandWord(input);

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
            task = TaskParser.parseTask(inputWithoutCommand);
            break;
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
            handleAddTaskInput(input);
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
