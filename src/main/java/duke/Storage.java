package duke;

import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *  Handles saving and reading data in and out of a text file.
 */
public class Storage {
    private TextUi ui;
    private File storageFile;
    private String storageFilepath;
    private DateTimeFormatter dateTimeFormatter;

    public Storage(TextUi ui, String storageFilepath, DateTimeFormatter dateTimeFormatter) {
        this.ui = ui;
        this.storageFilepath = storageFilepath;
        this.dateTimeFormatter = dateTimeFormatter;
    }

    private Task deserializeTask(String inputLine) {
        Task task = null;
        LocalDateTime dateTime;

        String[] lineSegments = inputLine.split(";");
        boolean isDone = lineSegments[1].equals("1");

        switch (lineSegments[0]) {
        case Todo.IDENTIFIER:
            task = new Todo(isDone, lineSegments[2]);
            break;
        case Deadline.IDENTIFIER:
            dateTime = LocalDateTime.parse(lineSegments[3], dateTimeFormatter);
            task = new Deadline(isDone, lineSegments[2], dateTime, dateTimeFormatter);
            break;
        case Event.IDENTIFIER:
            dateTime = LocalDateTime.parse(lineSegments[3], dateTimeFormatter);
            task = new Event(isDone, lineSegments[2], dateTime, dateTimeFormatter);
            break;
        default:
            break;
        }

        return task;
    }

    private String serializeTask(Task task) {

        String line = "";

        line += task.getIdentifier() + ";";
        line += (task.isDone() ? 1 : 0) + ";";
        line += task.getDescription();

        if (task instanceof Deadline) {
            line += ";" + ((Deadline) task).getBy().format(dateTimeFormatter);
        } else if (task instanceof Event) {
            line += ";" + ((Event) task).getAt().format(dateTimeFormatter);
        }

        return line;
    }

    private TaskList readDataFromFile() throws FileNotFoundException {
        TaskList tasks = new TaskList();
        Scanner s = new Scanner(storageFile);
        String currLine;

        while (s.hasNext()) {
            currLine = s.nextLine();

            if (currLine == "") {
                return tasks;
            }

            tasks.add(deserializeTask(currLine));
        }

        return tasks;
    }

    private void saveDataToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(storageFile);

        Task task;
        String textToWrite = "";

        for (int i = 0; i < tasks.size(); i++) {
            task = tasks.get(i);
            textToWrite += serializeTask(task) + System.lineSeparator();
        }

        fw.write(textToWrite);
        fw.close();
    }

    /**
     * Opens up the storage text file containing tasks, and adds all of the tasks into the given TaskList.
     *
     * @param taskList the list of tasks to add on to
     * @return true if operation is successful, false otherwise
     */
    public boolean performFileSetup(TaskList taskList) {
        try {
            storageFile = new File(storageFilepath);
            storageFile.createNewFile();

            taskList.addAll(readDataFromFile());
        } catch (IOException e) {
            ui.printErrorMessage("Unable to perform file setup.");
            return false;
        }

        return true;
    }

    /**
     * Saves the tasks from the given list into the storage text file.
     *
     * @param tasks the list containing tasks to be saved
     */
    public void save(ArrayList<Task> tasks) {
        try {
            saveDataToFile(tasks);
        } catch (IOException e) {
            ui.printErrorMessage("Unable to save file.");
        }
    }
}
