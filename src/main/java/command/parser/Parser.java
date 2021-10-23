package command.parser;

import command.NoCap;
import command.Ui;
import command.storage.StorageEncoder;
import module.Module;
import task.Task;

import java.util.Locale;

public class Parser {

    public static final String EMPTY_STRING = "";
    private static final String SPACE_STRING = " ";
    public static final String SWITCHSEMESTER = "switch";
    public static final String SEMESTERS = "semesters";
    public static final String CAP = "cap";
    public static final String ALLCAP = "allcap";
    public static final String TASK = "task";
    public static final String MODULE = "module";
    public static final String HELP = "help";
    public static final String ADD = "add";
    public static final String DELETE = "delete";
    public static final String LIST = "list";
    public static final String TIMETABLE = "timetable";
    public static final String EXIT = "bye";
    public static final String MODULETYPE = "/m";
    public static final String ADDCLASS = "addclass";
    public static final String ADDTASK = "addtask";
    public static final String ADDGRADABLE = "addgradable";
    public static final String ADDGRADE = "addgrade";
    public static final String ADDCREDIT = "addcredit";
    public static final String DELETECLASS = "deleteclass";
    public static final String DELETETASK = "deletetask";
    public static final String DELETEGRADE = "deletegrade";
    public static final String EDITDESCRIPTION = "editdesc";
    public static final String EDITDEADLINE = "editdate";
    public static final String NOTDONE = "notdone";
    public static final String DONE = "done";
    public static final String INFO = "info";
    public static final String START_OF_DATE = "/by";
    public static final String START_OF_WEIGHTAGE = "/w";
    public static final String SORT_BY_DATE = "sortbydate";
    public static final String SORT_BY_STATUS = "sortbystatus";
    public static final String SHOW_WEEK = "w";
    public static final String SHOW_MONTH = "m";
    public static final String SHOW_YEAR = "y";

    public static String taskType;
    public static String taskDescription;
    public static Module module;

    private final Command command = new Command(this);
    private final ListParser list = new ListParser();

    protected boolean isExit;

    public Parser() {
        this.isExit = false;
    }

    /**
     * Method to process user command.
     *
     * @param line User input
     */
    public void chooseTask(String line) {
        splitInput(line);
        switch (taskType) {
        case HELP:
            Ui.printHelpMessage();
            break;
        case SWITCHSEMESTER:
            command.commandSwitchSemester();
            break;
        case CAP:
            command.commandPrintCap();
            break;
        case ALLCAP:
            command.commandPrintAllCap();
            break;
        case ADD:
            command.commandAddModule(this);
            break;
        case DELETE:
            command.commandDeleteModule(this);
            break;
        case TIMETABLE:
            command.commandPrintTimeTable();
            break;
        case LIST:
            list.overallListParser(taskDescription);
            break;
        case MODULETYPE:
            moduleParser(taskDescription);
            StorageEncoder.encodeAndSaveSemesterListToJson(NoCap.semesterList);
            break;
        case EXIT:
            Ui.printExitMessage();
            StorageEncoder.encodeAndSaveSemesterListToJson(NoCap.semesterList);
            this.isExit = true;
            break;
        default:
            Ui.printInvalidInputMessage();
            break;
        }
    }


    /**
     * First separate the input into two parts. The first part is saved as module.
     * The next part is split again to obtain the new taskType and taskDescription
     *
     * @param input String to be separated
     */
    void moduleParser(String input) {

        splitInput(input);
        try {
            module = NoCap.moduleList.find(taskType.toUpperCase(Locale.ROOT));
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printInvalidModuleNameMessage();
            return;
        }

        if (isEmptyDescription(taskDescription)) {
            return;
        }
        splitInput(taskDescription);

        switch (taskType) {
        case LIST:
            list.moduleListParser(module, taskDescription);
            break;
        case ADDCLASS:
            command.commandAddClass(this);
            break;
        case ADDTASK:
            command.commandAddTask(this);
            break;
        case ADDGRADABLE:
            command.commandAddGradable(this);
            break;
        case DONE:
            command.commandMarkDone(this);
            break;
        case NOTDONE:
            command.commandMarkNotDone(this);
            break;
        case ADDGRADE:
            command.commandAddGrade(this);
            break;
        case ADDCREDIT:
            command.commandAddCredit(this);
            break;
        case DELETECLASS:
            command.commandDeleteClass();
            break;
        case DELETETASK:
            command.commandDeleteTask(this);
            break;
        case EDITDESCRIPTION:
            command.commandEditDescription(this);
            break;
        case EDITDEADLINE:
            command.commandEditDeadline(this);
            break;
        case DELETEGRADE:
            command.commandDeleteGrade();
            break;
        case INFO:
            command.commandShowInfo();
            break;
        default:
            Ui.printInvalidInputMessage();
            break;
        }
    }

    //split string on first space
    static void splitInput(String input) {
        try {
            int typePos = input.indexOf(SPACE_STRING);
            taskType = input.substring(0, typePos);
            taskDescription = input.substring(typePos).trim();
        } catch (StringIndexOutOfBoundsException e) {
            taskType = input.trim();
            taskDescription = EMPTY_STRING;
            assert (taskType.equals(input.trim()));
        }
    }

    public boolean isExit() {
        return this.isExit;
    }

    public String getTaskType() {
        return taskType;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Used in add to verify module does not exist. Prevent duplicate module entries.
     *
     * @param input moduleName to be checked against.
     * @return true if input is existing module.
     */
    boolean isDuplicateModule(String input) {
        try {
            module = NoCap.moduleList.find(input.toUpperCase(Locale.ROOT));
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

    boolean hasDateDescription(String input) {
        if (input.contains(START_OF_DATE)) {
            return true;
        }
        Ui.invalidDate();
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