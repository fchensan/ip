package duke.task;

import java.util.ArrayList;

/**
 * An ArrayList Task instances with helper functions.
 */
public class TaskList extends ArrayList<Task> {
    /**
     * Marks a task as done.
     *
     * @param taskIndex the index of the task to be marked as done
     */
    public void markAsDone(int taskIndex) {
        get(taskIndex).markAsDone();
    }
}
