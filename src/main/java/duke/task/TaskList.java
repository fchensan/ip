package duke.task;

import java.time.LocalDate;
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

    public TaskList findTasks(String searchPhrase) {
        TaskList matchingTasks = new TaskList();

        for (Task task : this) {
            if (task.getDescription().contains(searchPhrase)) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }

    public TaskList getTasksByDate(LocalDate date) {
        TaskList matchingTasks = new TaskList();

        for (Task task : this) {
            if ((task instanceof Deadline && ((Deadline) task).getBy().toLocalDate().equals(date)) ||
                    (task instanceof Event && ((Event) task).getAt().toLocalDate().equals(date))) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }
}
