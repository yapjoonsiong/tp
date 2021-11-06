package command;


import module.Module;
import module.ModuleList;
import schedule.Schedule;
import schedule.ScheduleList;
import task.GradableTaskList;
import task.OverallTask;
import task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.StreamSupport;

public class Ui {
    private static final String logo = "NoCap Logo";

    public static void printHelpMessage() {
        System.out.println("Show help: help \n"
                + "Show all semesters: list semesters\n"
                + "Change semester: switch <semester index>\n"
                + "Add module: add <module name>\n"
                + "Delete module: delete <module index>\n"
                + "List all module: list module\n"
                + "Add task: /m <module> addtask <description> /by <date> (time)\n"
                + "Add gradable task:  /m <module> addgradable <description> /by <date> (time) /w <weightage>\n"
                + "Edit description: /m <module> editdesc <task index> <new description>\n"
                + "Edit deadline: /m <module> editdate <task index> <new deadline>\n"
                + "Delete task: /m <module> deletetask <sub string>\n"
                + "Mark task as complete: /m <module> done <task index>\n"
                + "Mark task as incomplete: /m <module> notdone <task index>\n"
                + "Mark gradable task as complete: /m <module> gradabledone <task index>\n"
                + "Mark gradable task as incomplete: /m <module> gradablenotdone <task index>\n"
                + "List module tasks: /m <module> list (optional argument)\n"
                + "Add class:  /m <module> addclass <day/period/location/comments>\n"
                + "Delete class: /m <module> deleteclass <class index>\n"
                + "Add grade: /m <module> addgrade <grade letter>\n"
                + "Delete grade: /m <module> deletegrade\n"
                + "Add credit: /m <module> addcredit\n"
                + "Show timetable: timetable\n"
                + "List all tasks: list task (optional argument)\n"
                + "Show module cap: cap\n"
                + "Show overall cap: allcap\n"
                + "Exit NoCap: bye");

    }

    public static void missingDescription() {
        System.out.println("You are missing a description!");
    }

    public static void missingDate() {
        System.out.println("You are missing a date!");
    }

    public static void printStartMessage(String semester) {
        System.out.println("Welcome to NoCap");
        System.out.println("You are now accessing semester: " + semester);
    }

    public static void printEndMessage() {
        System.out.println("Thank you for using NoCap");
    }

    public static void invalidDate() {
        System.out.println("Missing /by");
    }

    public static void invalidWeightage() {
        System.out.println("Missing /w");
    }


    public static void wrongDateTimeFormat() {
        System.out.println("Wrong date format input!");
        System.out.println("Format: dd/MM/yyyy hhmm");
    }

    public static void inputNotInteger() {
        System.out.println("Input must be an integer!");
    }

    public static void switchSemesterMessage(String semester) {
        System.out.println("Semester successfully switched");
        System.out.println("You are now accessing semester: " + semester);
    }

    public static void addModuleNameMessage(ModuleList moduleList) {
        System.out.println("Module successfully added: ");
        moduleList.printModules();
    }

    public static void addModuleGradeMessage(Module module) {
        System.out.println("Module grade successfully added: ");
        System.out.println(module);
    }

    public static void addModuleCreditsMessage(Module module) {
        System.out.println("Module credits successfully added: ");
        System.out.println(module);
    }

    public static void addModuleClassMessage(Module module) {
        System.out.println("Module Class successfully added: ");
        System.out.println(module.getScheduleList());
    }

    public static void addTaskMessage(Task task, String moduleName) {
        System.out.println("Added new task to " + moduleName);
        System.out.println(task);
    }

    public static void printTaskList(String module, int taskCount) {
        System.out.println("Task List for " + module.toUpperCase(Locale.ROOT) + ":");
        System.out.println("There are " + taskCount + " tasks");
    }

    public static void printWeeklyTaskList(String module, int taskCount) {
        System.out.println("Task List for " + module.toUpperCase(Locale.ROOT) + ":");
        System.out.println("There are " + taskCount + " tasks due within 7 days");
    }

    public static void printMonthlyTaskList(String module, int taskCount) {
        System.out.println("Task List for " + module.toUpperCase(Locale.ROOT) + ":");
        System.out.println("There are " + taskCount + " tasks due within a month");
    }

    public static void printYearlyTaskList(String module, int taskCount) {
        System.out.println("Task List for " + module.toUpperCase(Locale.ROOT) + ":");
        System.out.println("There are " + taskCount + " tasks due within a year");
    }

    public static void printEmptyTaskList(String module) {
        System.out.println("There are no tasks due in " + module.toUpperCase(Locale.ROOT));
    }

    public static void printSortListByDate(String module) {
        System.out.println(module.toUpperCase(Locale.ROOT) + " successfully sorted by date");
    }


    public static void printSortListByStatus(String module) {
        System.out.println(module.toUpperCase(Locale.ROOT) + " successfully sorted by status");
    }

    /**
     * Method to print error when save file cannot be parsed.
     */
    public static void parseSaveFileError() {
        System.out.println("Error parsing save file");
    }

    /**
     * Method to print error when a save file could not be written.
     */
    public static void writeSaveFileError() {
        System.out.println("Error writing to save file");
    }

    /**
     * Method to print error if there are errors when trying to save the file.
     */
    public static void saveFileError() {
        System.out.println("Error saving file");
    }

    /**
     * Method to print message when data is loaded successfully from a save file.
     */
    public static void loadFileSuccessful() {
        System.out.println("Data loaded successfully");
    }

    /**
     * Method to print message when a save file cannot be found.
     */
    public static void printNoSaveFileMessage() {
        System.out.println("No save file found, starting with an empty template");
    }

    /**
     * Method to print message when a save file is corrupted.
     */
    public static void printCorruptFileMessage() {
        System.out.println("Error reading save file, creating new template");
    }

    public static void printInvalidListFormat() {
        System.out.println("The list format is wrong! Please use list module/tasks "
                + "[ sortbydate | sortbystatus ] [ w | m | y]");
        System.out.println("Please refer to the user guide or help function for more details!");
    }

    public static void printInvalidIndex() {
        System.out.println("Task with the specified index not found!");
    }

    public static void printInvalidKeyword() {
        System.out.println("Task with the specified keyword not found!");
    }

    public static void printTaskFound() {
        System.out.println("The following task(s) are found:");
    }

    public static void printSelectIndex() {
        System.out.println("Please choose a task to perform the action, or press x to cancel:");
    }

    public static void printTaskDeleted(Task task) {
        System.out.println(task.getDescription() + " has been deleted.");
    }

    public static void printMarkDoneMessage(Task task) {
        System.out.println("Task is completed:" + System.lineSeparator() + task);
    }

    public static void printMarkNotDoneMessage(Task task) {
        System.out.println("Task is marked not complete:" + System.lineSeparator() + task);
    }

    public static void printExitMessage() {
        System.out.println("Thank you for using NoCap!");
        System.out.println("Have a nice day!");
    }

    public static void duplicateModuleError() {
        System.out.println("This module already exists!");
    }

    public static void printInvalidInputMessage() {
        System.out.println("Invalid Input!");
    }

    public static void printInvalidModuleNameMessage() {
        System.out.println("Invalid Module name!");
    }

    /**
     * Method to print overall task list that has been sorted by date.
     *
     * @param newTaskList Overall task list to be printed.
     */
    public static void printOverallListOrderedByDate(List<OverallTask> newTaskList) {
        System.out.println("Tasks sorted by date: ");
        if (newTaskList.isEmpty()) {
            System.out.println("You have no tasks");
            return;
        }
        for (int i = 0; i < newTaskList.size(); i++) {
            System.out.println((i + 1) + ". " + newTaskList.get(i));
        }
    }

    /**
     * Method to print overall task list that has been sorted by status.
     *
     * @param newTaskList Overall task list to be printed.
     */
    public static void printOverallListOrderedByStatus(List<OverallTask> newTaskList) {
        System.out.println("Tasks sorted by status: ");
        if (newTaskList.isEmpty()) {
            System.out.println("You have no tasks");
            return;
        }
        for (int i = 0; i < newTaskList.size(); i++) {
            System.out.println((i + 1) + ". " + newTaskList.get(i));
        }
    }

    /**
     * Method to print overall task list containing weekly tasks.
     *
     * @param newTaskList Overall task list to be printed.
     */
    public static void printOverallWeeklyTasks(List<OverallTask> newTaskList) {
        System.out.println("Weekly tasks: ");
        if (newTaskList.isEmpty()) {
            System.out.println("You have no weekly tasks");
            return;
        }
        for (int i = 0; i < newTaskList.size(); i++) {
            System.out.println((i + 1) + ". " + newTaskList.get(i));
        }
    }

    /**
     * Method to print overall task list containing monthly tasks.
     *
     * @param newTaskList Overall task list to be printed.
     */
    public static void printOverallMonthlyTasks(List<OverallTask> newTaskList) {
        System.out.println("Monthly tasks: ");
        if (newTaskList.isEmpty()) {
            System.out.println("You have no monthly tasks");
            return;
        }
        for (int i = 0; i < newTaskList.size(); i++) {
            System.out.println((i + 1) + ". " + newTaskList.get(i));
        }
    }

    /**
     * Method to print overall task list containing yearly tasks.
     *
     * @param newTaskList Overall task list to be printed.
     */
    public static void printOverallYearlyTasks(List<OverallTask> newTaskList) {
        System.out.println("Yearly tasks: ");
        if (newTaskList.isEmpty()) {
            System.out.println("You have no yearly tasks");
            return;
        }
        for (int i = 0; i < newTaskList.size(); i++) {
            System.out.println((i + 1) + ". " + newTaskList.get(i));
        }
    }

    /**
     * Method to print all tasks in an overall task list.
     *
     * @param overallTaskList Overall task list to be printed.
     */
    public static void printAllOverallTasks(ArrayList<OverallTask> overallTaskList) {
        System.out.println("All tasks: ");
        if (overallTaskList.isEmpty()) {
            System.out.println("You have no tasks");
            return;
        }
        for (int i = 0; i < overallTaskList.size(); i++) {
            System.out.println((i + 1) + ". " + overallTaskList.get(i));
        }
    }

    /**
     * Method to print overall task list containing only gradable tasks.
     *
     * @param newTaskList Overall task list to be printed.
     */
    public static void printGradableTasks(List<OverallTask> newTaskList) {
        System.out.println("Gradable tasks: ");
        if (newTaskList.isEmpty()) {
            System.out.println("You have no gradable tasks");
            return;
        }
        for (int i = 0; i < newTaskList.size(); i++) {
            System.out.println((i + 1) + ". " + newTaskList.get(i));
        }
    }

    /**
     * Method to print overall task list containing only non-gradable tasks.
     *
     * @param newTaskList Overall task list to be printed.
     */
    public static void printNormalTasks(List<OverallTask> newTaskList) {
        System.out.println("Non-gradable tasks: ");
        if (newTaskList.isEmpty()) {
            System.out.println("You have no non-gradable tasks");
            return;
        }
        for (int i = 0; i < newTaskList.size(); i++) {
            System.out.println((i + 1) + ". " + newTaskList.get(i));
        }
    }

    public static void visualiseGradableTask(GradableTaskList gl) {
        VisualiseGradable v = new VisualiseGradable(gl);
        v.print();
    }

    public static void wrongWeightage() {
        System.out.println("Invalid Weightage, must be between 5 and 100 inclusive.");
    }

    public static void wrongWeightageSplits() {
        System.out.println("Sum of all weightages cannot exceed 100%");
    }

    public static void moduleNameHasSpace() {
        System.out.println("Module name cannot include space.");
    }

    public static void duplicateTaskError() {
        System.out.println("Doing this will result in duplicate tasks!");
    }

    public static void taskUpdateMessage() {
        System.out.println("The task you are trying to add already exists");
        System.out.println("Deadline for this task has been updated");
    }

    public static void printUpdateTaskDescription(Task t) {
        System.out.println("Task is updated with a new description."
                + System.lineSeparator() + "This is your new Task:"
                + System.lineSeparator() + t);
    }

    public static void deleteModuleMessage(Module m) {
        System.out.print(m.getModuleName());
        System.out.println(" has been successfully deleted");
    }

    public static void deleteScheduleMessage(Schedule s) {
        System.out.println("Class: ");
        System.out.println(s);
        System.out.println("has been successfully deleted");
    }

    public static void printRemainingModules() {
        System.out.println("Remaining Modules are: ");
    }

    public static void printRemainingSchedules(ScheduleList sl) {
        System.out.println("\nRemaining Classes are: ");
        System.out.println(sl);
    }

    public static void deleteGradeMesage(Module m) {
        System.out.println("Module grade has been successfully deleted");
        System.out.println(m);
    }

    public static void emptyModuleListMessage() {
        System.out.println("You currently have no Modules.");
    }

    public static void invalidGrade() {
        System.out.println("Invalid grade!");
    }
}
