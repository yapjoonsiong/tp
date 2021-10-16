package command;

import command.storage.StorageEncoder;
import module.Module;
import task.Task;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Parser {

    private static final String EMPTY_STRING = "";
    private static final String SPACE_STRING = " ";
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
    public static final String ADDGRADE = "addgrade";
    public static final String ADDCREDIT = "addcredit";
    public static final String DELETECLASS = "deleteclass";
    public static final String DELETETASK = "deletetask";
    public static final String DELETEGRADE = "deletegrade";
    public static final String DONE = "done";
    public static final String INFO = "info";
    public static final String START_OF_DATE = "/by";
    public static final String SORT_BY_DATE = "sortbydate";
    public static final String SORT_BY_STATUS = "sortbystatus";
    public static final String SHOW_WEEK = "w";
    public static final String SHOW_MONTH = "m";
    public static final String SHOW_YEAR = "y";

    static String taskType;
    static String taskDescription;
    private final List list = new List();
    protected String moduleName;
    public Module module;
    protected boolean isExit;
    private static Logger logger = Logger.getLogger(Parser.class.getName());

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
        case ADD:
            if(isEmptyDescription(taskDescription) | isDuplicateModule(taskDescription)) {
                break;
            }
            NoCap.moduleList.add(taskDescription.toUpperCase(Locale.ROOT));
            Ui.addModuleNameMessage(NoCap.moduleList);
            StorageEncoder.encodeAndSaveModuleListToJson(NoCap.moduleList);
            break;
        case DELETE:
            if(isEmptyDescription(taskDescription)) {
                break;
            }
            NoCap.moduleList.delete(taskDescription);
            StorageEncoder.encodeAndSaveModuleListToJson(NoCap.moduleList);
            break;
        case LIST:
            list.listParser(taskDescription);
            break;
        case TIMETABLE:
            NoCap.moduleList.printTimeTable();
            break;
        case EXIT:
            Ui.printExitMessage();
            StorageEncoder.encodeAndSaveModuleListToJson(NoCap.moduleList);
            this.isExit = true;
            break;
        case MODULETYPE:
            moduleParser(taskDescription);
            StorageEncoder.encodeAndSaveModuleListToJson(NoCap.moduleList);
            break;
        default:
            System.out.println("Invalid Input!");
            break;
        }
    }

    /**
     * First separate the input into two parts. The first part is saved as moduleName.
     * The next part is split again to obtain the new taskType and taskDescription
     *
     * @param input String to be separated
     */
    void moduleParser(String input) {

        splitInput(input);

        moduleName = taskType.toUpperCase(Locale.ROOT);
        try {
            module = NoCap.moduleList.find(moduleName);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("invalid Module name!");
            return;
        }

        if(isEmptyDescription(taskDescription)) {
            return;
        }
        splitInput(taskDescription);

        switch (taskType) {
        case ADDCLASS:
            if(isEmptyDescription(taskDescription)) {
                break;
            }
            module.addClass(taskDescription);
            Ui.addModuleClassMessage(module);
            break;
        case ADDTASK:
            if(isEmptyDescription(taskDescription) | !hasDateDescription(taskDescription)) {
                break;
            }
            module.addTask(taskDescription);
            break;
        case DONE:
            if(isEmptyDescription(taskDescription)) {
                break;
            }
            getTaskFromIndex(taskDescription).markDone();
            break;
        case ADDGRADE:
            if(isEmptyDescription(taskDescription)) {
                break;
            }
            module.addGrade(taskDescription);
            Ui.addModuleGradeMessage(module);
            break;
        case ADDCREDIT:
            if(isEmptyDescription(taskDescription)) {
                break;
            }
            module.addCredits(Integer.parseInt(taskDescription));
            Ui.addModuleCreditsMessage(module);
            break;
        case DELETECLASS:
            module.deleteClass();
            break;
        case DELETETASK:
            if(isEmptyDescription(taskDescription)) {
                break;
            }
            module.deleteTask(getTaskFromIndex(taskDescription));
            break;
        case DELETEGRADE:
            module.deleteGrade();
            break;
        case INFO:
            module.showInformation();
            break;
        default:
            break;
        }
    }

    private Task getTaskFromIndex(String input) {
        int index;
        Task task = null;
        try {
            index = Integer.parseInt(input) - 1;
            if(isValidIndex(index)) {
                task = module.taskList.get(index);
            }
        } catch (IndexOutOfBoundsException e) {
            Ui.printInvalidIndex();
        }
        return task;
    }

    private boolean isValidIndex(int index) {
        if (index < 0) {
            Ui.printInvalidIndex();
            return false;
        }
        return true;
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

    public String getModuleName() {
        return this.moduleName;
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


}
