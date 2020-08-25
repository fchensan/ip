import java.util.Scanner;

public class Duke {

    private final static int EXIT = 0;
    private final static int OK = 1;

    private static void printWelcomeMessage(){
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    private static void printDividerLine(){
        System.out.println("____________________________________________________________");
    }

    private static void printByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static int handleInput(String input){
        if(input.equals("bye")){
            printByeMessage();
            return EXIT;
        }

        System.out.println(input);
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
