package duke;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
    public void markAsDone(int taskIndex) {
        get(taskIndex).markAsDone();
    }
}
