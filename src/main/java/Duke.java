public class Duke {

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

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printWelcomeMessage();
        printDividerLine();
        printByeMessage();
    }
}
