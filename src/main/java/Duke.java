import java.util.Scanner;

public class Duke {

    private final static String DONE = "DONE";

    private final static int EXIT = 0;
    private final static int OK = 1;

    private static String[][] tasks = new String[100][];
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
        tasks[numTasks] = new String[]{task, null};
        numTasks++;
    }

    private static void printTasksList(){
        for(int i = 0; i<numTasks; i++){
            System.out.print(i+1 + ".");
            if (tasks[i][1] == DONE){
                System.out.print("[DONE] ");
            }else{
                System.out.print("[    ] ");
            }
            System.out.println(tasks[i][0]);
        }
    }

    private static void markAsDone(int taskIndex){
        tasks[taskIndex][1] = DONE;
    }

    private static int handleInput(String input){
        if(input.startsWith("done")){
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            markAsDone(taskIndex);

            System.out.println("Ok! \"" + tasks[taskIndex][0] + "\" is marked as done!");
            return OK;
        }else if(input.equals("list")){
            printTasksList();
        }else if(input.equals("bye")){
            printByeMessage();
            return EXIT;
        }else {
            addTask(input);
            System.out.println("Added: " + input);
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
