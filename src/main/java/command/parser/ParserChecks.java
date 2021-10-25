package command.parser;

import command.NoCap;
import command.Ui;
import task.GradableTask;
import task.Task;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class ParserChecks {

    public static final String START_OF_DATE = "/by";
    public static final String START_OF_WEIGHTAGE = "/w";

    public ParserChecks() {
    }

    public Task getTaskFromIndex(String input, ArrayList<Task> taskList) {
        int index;
        Task task = null;
        try {
            index = Integer.parseInt(input) - 1;
            if (isValidIndex(index)) {
                task = taskList.get(index);
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            Ui.printInvalidIndex();
        }
        return task;
    }

    public GradableTask getGradableTaskFromIndex(String input, ArrayList<GradableTask> taskList) {
        int index;
        GradableTask task = null;
        try {
            index = Integer.parseInt(input) - 1;
            if (isValidIndex(index)) {
                task = taskList.get(index);
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            Ui.printInvalidIndex();
        }
        return task;
    }

    public Task getTaskFromKeyword(String keyword, ArrayList<Task> allTaskList) {
        Task task = null;
        ArrayList<Task> taskList = new ArrayList<Task>();
        for (Task item : allTaskList) {
            if (item.getDescription().contains(keyword)) {
                taskList.add(item);
            }
        }

        if (taskList.isEmpty()) {
            Ui.printInvalidKeyword();
            return task;
        }
        assert (!taskList.isEmpty());

        Ui.printTaskFound();
        int counter = 1;
        for (Task item : taskList) {
            System.out.println(counter + ": " + item.getDescription());
            counter++;
        }

        Ui.printSelectIndex();
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        task = getTaskFromIndex(input, taskList);
        return task;
    }

    boolean isValidIndex(int index) {
        if (index < 0) {
            Ui.printInvalidIndex();
            return false;
        }
        return true;
    }

    /**
     * Used in add to verify module does not exist. Prevent duplicate module entries.
     *
     * @param input moduleName to be checked against.
     * @return true if input is existing module.
     */
    boolean isDuplicateModule(String input) {
        try {
            NoCap.moduleList.find(input.toUpperCase(Locale.ROOT));
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
        Ui.duplicateModuleError();
        return true;
    }

    boolean isEmptyDescription(String input) {
        if (input.isEmpty()) {
            Ui.missingDescription();
            return true;
        }
        return false;
    }

    boolean isNotInteger(String input) {
        if (input == null) {
            Ui.inputNotInteger();
            return true;
        }
        try {
            int in = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            Ui.inputNotInteger();
            return true;
        }
        return false;
    }

    boolean hasDateDescription(String input) {
        if (!input.contains(START_OF_DATE)) {
            Ui.invalidDate();
            return false;
        }
        return true;
    }

    boolean hasWeightageDescription(String input) {
        int typePos = input.indexOf(START_OF_DATE);
        String secondPart = input.substring(typePos);
        if (secondPart.contains(START_OF_WEIGHTAGE)) {
            return true;
        }
        Ui.invalidWeightage();
        return false;
    }
}