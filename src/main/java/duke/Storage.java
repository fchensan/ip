package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private TextUi ui;
    private File storageFile;

    private String storageFilepath;

    public Storage(TextUi ui, String storageFilepath) {
        this.ui = ui;
        this.storageFilepath = storageFilepath;
    }

    public Task deserializeTask(String inputLine) {
        Task task = null;

        String[] lineSegments = inputLine.split(";");
        boolean isDone = lineSegments[1].equals("1");

        switch (lineSegments[0]) {
        case Todo.IDENTIFIER:
            task = new Todo(isDone, lineSegments[2]);
            break;
        case Deadline.IDENTIFIER:
            task = new Deadline(isDone, lineSegments[2], lineSegments[3]);
            break;
        case Event.IDENTIFIER:
            task = new Event(isDone, lineSegments[2], lineSegments[3]);
            break;
        default:
            break;
        }

        return task;
    }

    public String serializeTask(Task task) {
        String line = "";

        line += task.getIdentifier() + ";";
        line += (task.isDone() ? 1 : 0) + ";";
        line += task.getDescription();

        if (task instanceof Deadline) {
            line += ";" + ((Deadline) task).getBy();
        } else if (task instanceof Event) {
            line += ";" + ((Event) task).getAt();
        }

        return line;
    }

    public TaskList readDataFromFile() throws FileNotFoundException {
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

    public void saveDataToFile(ArrayList<Task> tasks) throws IOException {
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

    public boolean performFileSetup(TaskList taskList) {
        try {
            storageFile = new File(storageFilepath);
            storageFile.createNewFile();

            taskList = readDataFromFile();
        } catch (IOException e) {
            ui.printErrorMessage("Unable to perform file setup.");
            return false;
        }

        return true;
    }

    public void performSavingOperations(ArrayList<Task> tasks) {
        try {
            saveDataToFile(tasks);
        } catch (IOException e) {
            ui.printErrorMessage("Unable to save file.");
        }
    }
}
