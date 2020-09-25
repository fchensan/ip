package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class TextUi {
    public Scanner in;

    public TextUi() {
        in = new Scanner(System.in);
    }

    public void printDukeLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public void printWelcomeMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public void printDividerLine() {
        System.out.println("____________________________________________________________");
    }

    public void printByeMessage() {
        System.out.println("Good bye!");
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printTasksList(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print(i + 1 + ". ");
            System.out.println(tasks.get(i).toString());
        }
    }

    public void printTaskMarkedAsDone(Task task) {
        System.out.println("Ok! \"" + task.getDescription() + "\" is marked as done!");
    }

    public void printTaskDeleted(Task removedTask) {
        System.out.println("Ok! I have deleted this task:");
        System.out.println(removedTask.toString());
    }

    public void printNumberOfTasksLeft(int numOfTasks) {
        System.out.println("You now have " + numOfTasks + " task(s) left.");
    }

    public void printErrorMessage(String message) {
        System.out.println("ERROR: " + message);
    }

    public String getUserInput() {
        System.out.print(">> ");
        return in.nextLine();
    }

}
