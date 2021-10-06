package command;


public class Ui {

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

    public static void addTaskMessage(Task task, String moduleName) {
        System.out.println("Added new task to " + moduleName);
        System.out.println(task);
    }

    public static void printTaskList(String module) {
        System.out.println("The tasks in " + module + " are: ");
    }
}
