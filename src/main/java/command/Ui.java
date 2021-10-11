package command;


import task.Task;

public class Ui {

    public static void printHelpMessage() {
        System.out.println("Display help messages: help");
        System.out.println("Add Module: add <module>");
        System.out.println("Delete Module: delete <Module>");
        System.out.println("List tasks: list task");
        System.out.println("List modules: list module");
        System.out.println("Show timetable: timetable");
        System.out.println("Add class to module: /m <module> addclass <description> /by <date>");
        System.out.println("Add task to module: /m <module> addtask <description> /by <date>");
        System.out.println("Add grade to module: /m <module> addgrade <gradeLetter>");
        System.out.println("Delete all classes from a module: /m <module> deleteclass");
        System.out.println("Delete all tasks from a module: /m <module> deletetask");
        System.out.println("Delete grade from a module: /m <module> deletegrade");
        System.out.println("Exit programme: bye");
    }

    public static void missingDescription() {
        System.out.println("You are missing a description!");
    }

    public static void missingDate() {
        System.out.println("You are missing a date!");
    }

    public static void printStartMessage() {
        System.out.println("Welcome to NoCap");
    }

    public static void printEndMessage() {
        System.out.println("Thank you for using NoCap");
    }

    public static void invalidDate() {
        System.out.println("Wrong date format input");
    }

    public static void wrongDateTimeFormat() {
        System.out.println("Wrong date format input!");
        System.out.println("Format: dd/MM/yy hhmm");
    }

    public static void addTaskMessage(Task task, String moduleName) {
        System.out.println("Added new task to " + moduleName);
        System.out.println(task);
    }

    public static void printTaskList(String module) {
        System.out.println("The tasks due in " + module + " are: ");
    }

    public static void printWeeklyTaskList(String module) {
        System.out.println("The tasks due within 7 days in " + module + " are: ");
    }

    public static void printMonthlyTaskList(String module) {
        System.out.println("The tasks due within a month in " + module + " are: ");
    }

    public static void printYearlyTaskList(String module) {
        System.out.println("The tasks due within a year in " + module + " are: ");
    }

    public static void loadFileSuccessful() {
        System.out.println("Modules loaded successfully");
    }

    public static void printNoSaveFileMessage() {
        System.out.println("No save file found, starting with an empty template");
    }
}
