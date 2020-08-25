import java.util.Scanner;

public class Duke {

    private final static int EXIT = 0;
    private final static int OK = 1;

    private static String[] tasks = new String[100];
    private static int numTasks = 0;

    private static void printWelcomeMessage(){
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    private static void printDividerLine(){
        System.out.println("____________________________________________________________");
    }

    private static void printByeMessage() {
        System.out.println("Good bye!");
    }

    private static void addTask(String task){
        tasks[numTasks] = task;
        numTasks++;
    }

    private static void printTasksList(){
        for(int i = 0; i<numTasks; i++){
            System.out.println(i+1 + ". " + tasks[i]);
        }
    }

    private static int handleInput(String input){
        switch (input){
        case "list":
            printTasksList();
            break;
        case "bye":
            printByeMessage();
            return EXIT;
        default:
            addTask(input);
            System.out.println("Added: " + input);
            break;
        }

        printDividerLine();
        return OK;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printWelcomeMessage();
        printDividerLine();

        Scanner in = new Scanner(System.in);
        String input;

        do {
            System.out.print(">> ");
            input = in.nextLine();
        } while(handleInput(input) != EXIT);

    }
}
