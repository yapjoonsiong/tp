package command.parser;

import command.NoCap;
import command.Ui;
import task.GradableTask;
import task.Task;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * Class containing parser methods that specifically handles error checking.
 * Also includes methods for getting Task from index or string.
 */
public class ParserChecks {
    public static final String START_OF_DATE = "/by";
    public static final String START_OF_WEIGHTAGE = "/w";

    public ParserChecks() {
    }

    /**
     * Returns a task at the specified index from a tasklist.
     * If input is not an index, error message is printed.
     * Returns null if task cannot be found.
     *
     * @param input index to be accessed.
     * @param taskList arraylist with tasks.
     */
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

    /**
     * Returns a gradable task at the specified index from a tasklist.
     * If input is not an index, error message is printed.
     * Returns null if task cannot be found.
     *
     * @param input index to be accessed.
     * @param taskList arraylist with gradable tasks.
     */
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

    /**
     * Searches for tasks containing keyword from an arraylist of tasks and creates a filtered arraylist.
     * Prints out this filtered list and request for user input to select an index.
     * Calls getTaskFromIndex to get task from the filtered list.
     *
     * @param keyword keyword to search for.
     * @param allTaskList arraylist with all tasks.
     */
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

        String input = requestUserInput();

        if (input.equals("x")) {
            return task;
        }
        task = getTaskFromIndex(input, taskList);
        return task;
    }

    private String requestUserInput() {
        Ui.printSelectIndex();
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        return input;
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

    /**
     * Checks if /w exists after /by.
     *
     * @param input string to be checked
     * @return true if checks passes.
     */
    boolean hasWeightageDescription(String input) {
        int typePos = input.indexOf(START_OF_DATE);
        String secondPart = input.substring(typePos);
        if (secondPart.contains(START_OF_WEIGHTAGE)) {
            return true;
        }
        Ui.invalidWeightage();
        return false;
    }

    boolean includeSpace(String input) {
        if (input.trim().contains(Parser.SPACE_STRING)) {
            Ui.moduleNameHasSpace();
            return true;
        }
        return false;
    }

    /**
     * Checks the input string is a valid grade.
     *
     * @param input string to be checked.
     * @return true if checks passes.
     */
    boolean validGrade(String input) {
        String grade = input.trim();
        switch (grade) {
        case "A+":
        case "A":
        case "A-":
        case "B+":
        case "B":
        case "B-":
        case "C+":
        case "C":
        case "D+":
        case "D":
        case "F":
            return true;
        default:
            Ui.invalidGrade();
            return false;
        }
    }
}