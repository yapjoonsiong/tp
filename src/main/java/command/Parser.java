package command;

import command.storage.StorageEncoder;
import module.Module;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    public static final String DELETECLASS = "deleteclass";
    public static final String DELETETASK = "deletetask";
    public static final String DELETEGRADE = "deletegrade";
    public static final String INFO = "info";
    public static final String START_OF_DATE = "/by";
    public static final String SORT_BY_DATE = "sortbydate";
    public static final String SORT_BY_STATUS = "sortbystatus";
    public static final String SHOW_WEEK = "w";
    public static final String SHOW_MONTH = "m";
    public static final String SHOW_YEAR = "y";
    public static final String SHOW_ALL = "a";


    static String taskType;
    static String taskDescription;
    protected String moduleName;
    protected Module module;
    protected boolean isExit;
    private static Logger logger = Logger.getLogger(Parser.class.getName());
    public static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    public static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d MMM yyyy hh:mm a");

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
            logger.log(Level.INFO, "Help Test");
            break;
        case ADD:
            if (taskDescription.isEmpty()) {
                Ui.missingDescription();
                break;
            }
            NoCap.moduleList.add(taskDescription);
            StorageEncoder.encodeAndSaveModuleListToJson(NoCap.moduleList);
            logger.log(Level.INFO, "Add Test");
            break;
        case DELETE:
            if (taskDescription.isEmpty()) {
                Ui.missingDescription();
                break;
            }
            NoCap.moduleList.delete(taskDescription);
            StorageEncoder.encodeAndSaveModuleListToJson(NoCap.moduleList);
            logger.log(Level.INFO, "Delete Test");
            break;
        case LIST:
            listParser(taskDescription);
            break;
        case TIMETABLE:
            NoCap.moduleList.printTimeTable();
            logger.log(Level.INFO, "Timetable Test");
            break;
        case EXIT:
            Ui.printExitMessage();
            logger.log(Level.INFO, "Exit Test");
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
        moduleName = taskType;
        try {
            module = NoCap.moduleList.find(moduleName);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("invalid Module name!");
            return;
        }

        if (taskDescription.isEmpty()) {
            Ui.missingDescription();
            return;
        }
        splitInput(taskDescription);

        switch (taskType) {
        case ADDCLASS:
            if (taskDescription.isEmpty()) {
                Ui.missingDescription();
                break;
            }
            module.addClass(taskDescription);
            logger.log(Level.INFO, "AddClass test");
            break;
        case ADDTASK:
            if (taskDescription.isEmpty()) {
                Ui.missingDescription();
                break;
            }
            if (!taskDescription.contains(START_OF_DATE)) {
                Ui.invalidDate();
                break;
            }
            module.addTask(taskDescription);
            break;
        case ADDGRADE:
            if (taskDescription.isEmpty()) {
                Ui.missingDescription();
                break;
            }
            module.addGrade(taskDescription);
            logger.log(Level.INFO, "AddGrade test");
            break;
        case DELETECLASS:
            //moduleName -> deleteclass method
            logger.log(Level.INFO, "DeleteClass test");
            break;
        case DELETETASK:
            //moduleName -> deletetask method
            logger.log(Level.INFO, "DeleteTask test");
            break;
        case DELETEGRADE:
            module.deleteGrade();
            logger.log(Level.INFO, "DeleteGrade test");
            break;
        case INFO:
            module.showInformation();
            break;
        default:
            System.out.println("Invalid Module Command!");
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

    public static LocalDateTime parseDate(String str) {
        return LocalDateTime.parse(str, inputFormatter);
    }

    public static String dateStringOutput(LocalDateTime dateTime) {
        return dateTime.format(outputFormatter);
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

    private boolean isEmpty(Module module) {
        if (module.taskList.size() == 0) {
            Ui.printEmptyTaskList(module.getModuleName());
            return true;
        }
        return false;
    }

    private void sortByDate(ArrayList<Module> moduleList) {
        for (Module module : moduleList) {
            if (isEmpty(module)) {
                break;
            }
            module.taskList.sortTaskListByDate(module.getModuleName());
        }
    }

    private void sortByStatus(ArrayList<Module> moduleList) {
        for (Module module : moduleList) {
            if (isEmpty(module)) {
                break;
            }
            module.taskList.sortTaskListByStatus(module.getModuleName());
        }
    }

    private void listWeekly(ArrayList<Module> moduleList) {
        for (Module module : moduleList) {
            if (isEmpty(module)) {
                break;
            }
            module.taskList.showAllWeekly(module.getModuleName());
        }
    }

    private void listAll(ArrayList<Module> moduleList) {
        for (Module module : moduleList) {
            if (isEmpty(module)) {
                break;
            }
            module.taskList.printTasks(module.getModuleName());
        }
    }

    private void listMonthly(ArrayList<Module> moduleList) {
        for (Module module : moduleList) {
            if (isEmpty(module)) {
                break;
            }
            module.taskList.showAllMonthly(module.getModuleName());
        }
    }

    private void listYearly(ArrayList<Module> moduleList) {
        for (Module module : moduleList) {
            if (isEmpty(module)) {
                break;
            }
            module.taskList.showAllYearly(module.getModuleName());
        }
    }

    void listParser(String input) {
        splitInput(input);
        if (taskType.equals(MODULE)) {
            NoCap.moduleList.printModules();
            logger.log(Level.INFO, "List Module Test");
        } else if (taskType.equals(TASK)) {
            ArrayList<Module> moduleList = new ArrayList<>(NoCap.moduleList.getModuleList());
            switch (taskDescription) {
            case SORT_BY_DATE:
                sortByDate(moduleList);
                break;
            case SORT_BY_STATUS:
                sortByStatus(moduleList);
                break;
            case SHOW_ALL:
                listAll(moduleList);
                break;
            case SHOW_WEEK:
                listWeekly(moduleList);
                break;
            case SHOW_MONTH:
                listMonthly(moduleList);
                break;
            case SHOW_YEAR:
                listYearly(moduleList);
                break;
            default:
                System.out.println("Invalid optional command!");
                break;
            }
        } else {
            Ui.printInvalidListFormat();
        }
    }
}
        /*
        for (int i = 0; i < NoCap.moduleList.size(); i++) {
            System.out.println((i + 1) + ". " + NoCap.moduleList.get(i).getModuleName());
            for (int j = 0; j < NoCap.moduleList.get(i).taskList.size(); j++) {
                System.out.println("\t" + (j + 1) + ". " + NoCap.moduleList.get(i).getTaskList().get(j));
            }
        }*/