package command.parser;

import command.NoCap;
import command.Ui;
import command.storage.StorageEncoder;
import module.Module;

import semester.Semester;
import task.GradableTask;
import task.Task;
import semester.SemesterList;

import java.util.Locale;

public class Parser {

    public static final String EMPTY_STRING = "";
    private static final String SPACE_STRING = " ";
    public static final String SWITCHSEMESTER = "switch";
    public static final String SEMESTERS = "semesters";
    public static final String CAP = "cap";
    public static final String ALLCAP = "allcap";
    public static final String TASK = "task";
    public static final String GRADABLE = "gradable";
    public static final String MODULE = "module";
    public static final String HELP = "help";
    public static final String ADD = "add";
    public static final String DELETE = "delete";
    public static final String LIST = "list";
    public static final String TIMETABLE = "timetable";
    public static final String MODULETYPE = "/m";
    public static final String EXIT = "bye";
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
    public static final String DONE = "done";
    public static final String NOTDONE = "notdone";
    public static final String GRADABLEDONE = "gradabledone";
    public static final String GRADABLENOTDONE = "gradablenotdone";
    public static final String INFO = "info";

    private String taskType;
    private String taskDescription;

    private final Command command = new Command();
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
            command.commandSwitchSemester(taskDescription);
            break;
        case CAP:
            command.commandPrintCap();
            break;
        case ALLCAP:
            command.commandPrintAllCap();
            break;
        case ADD:
            command.commandAddModule(taskDescription);
            break;
        case DELETE:
            command.commandDeleteModule(taskDescription);
            break;
        case TIMETABLE:
            command.commandPrintTimeTable();
            break;
        case LIST:
            splitInput(taskDescription);
            list.overallListParser(taskType, taskDescription);
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
        Module module;
        splitInput(input);
        try {
            module = NoCap.moduleList.find(taskType.toUpperCase(Locale.ROOT));
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printInvalidModuleNameMessage();
            return;
        }

        splitInput(taskDescription);
      
        switch (taskType) {
        case LIST:
            list.moduleListParser(module, taskDescription);
            break;
        case ADDCLASS:
            command.commandAddClass(module, taskDescription);
            break;
        case ADDTASK:
            command.commandAddTask(module, taskDescription);
            break;
        case ADDGRADABLE:
            command.commandAddGradable(module, taskDescription);
            break;
        case ADDGRADE:
            command.commandAddGrade(module, taskDescription);
            break;
        case ADDCREDIT:
            command.commandAddCredit(module, taskDescription);
            break;
        case DELETECLASS:
            command.commandDeleteClass(module);
            break;
        case DELETETASK:
            command.commandDeleteTask(module, taskDescription);
            break;
        case DELETEGRADE:
            command.commandDeleteGrade(module);
            break;
        case EDITDESCRIPTION:
            splitInput(taskDescription);
            command.commandEditDescription(module,taskType,taskDescription);
            break;
        case EDITDEADLINE:
            splitInput(taskDescription);
            command.commandEditDeadline(module,taskType,taskDescription);
            break;
        case DONE:
            command.commandMarkDone(module, taskDescription);
            break;
        case NOTDONE:
            command.commandMarkNotDone(module, taskDescription);
            break;
        case GRADABLEDONE:
            command.commandMarkGradableDone(module, taskDescription);
            break;
        case GRADABLENOTDONE:
            command.commandMarkGradableNotDone(module, taskDescription);
            break;
        case INFO:
            command.commandShowInfo(module);
            break;
        default:
            Ui.printInvalidInputMessage();
            break;
        }
    }

    //split string on first space
    void splitInput(String input) {
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

}