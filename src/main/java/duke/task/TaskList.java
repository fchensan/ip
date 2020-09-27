package duke.task;

import duke.task.Task;

import java.util.ArrayList;

/**
 * An ArrayList with of Task instances with helper functions.
 */
public class TaskList extends ArrayList<Task> {
    /**
     * Marks a task as done
     *
     * @param taskIndex the index of the task to be marked as done
     */
    public void markAsDone(int taskIndex) {
        get(taskIndex).markAsDone();
    }
}
