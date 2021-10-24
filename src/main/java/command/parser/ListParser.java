package command.parser;

import command.NoCap;
import command.Ui;
import module.Module;
import task.OverallTaskList;
import task.TaskList;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ListParser {

    public static final String SORT_BY_DATE = "sortbydate";
    public static final String SORT_BY_STATUS = "sortbystatus";
    public static final String SHOW_WEEK = "w";
    public static final String SHOW_MONTH = "m";
    public static final String SHOW_YEAR = "y";
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public ListParser() {
    }

    boolean isEmpty(Module module) {
        if (module.taskList.size() == 0) {
            Ui.printEmptyTaskList(module.getModuleName());
            return true;
        }
        return false;
    }

    private void sortByDate(Module module) {
        TaskList list = module.getTaskList();
        if (!isEmpty(module)) {
            list.sortTaskListByDate(module.getModuleName());
        }
    }

    private void sortByStatus(Module module) {
        TaskList list = module.getTaskList();
        if (!isEmpty(module)) {
            list.sortTaskListByStatus(module.getModuleName());
        }
    }

    private void listWeekly(Module module) {
        TaskList list = module.getTaskList();
        if (!isEmpty(module)) {
            list.showAllWeekly(module.getModuleName());
        }
    }

    private void listAll(Module module) {
        TaskList list = module.getTaskList();
        if (!isEmpty(module)) {
            Ui.printTaskList(module.getModuleName(), list.getTaskCount());
            list.printTasks(list.getTaskList());
        }
    }

    private void listMonthly(Module module) {
        TaskList list = module.getTaskList();
        if (!isEmpty(module)) {
            list.showAllMonthly(module.getModuleName());
        }
    }

    private void listYearly(Module module) {
        TaskList list = module.getTaskList();
        if (!isEmpty(module)) {
            list.showAllYearly(module.getModuleName());
        }
    }

    public void overallListParser(String taskType, String taskDescription) {
        switch (taskType) {
        case Parser.MODULE:
            NoCap.moduleList.printModules();
            break;
        case Parser.SEMESTERS:
            NoCap.semesterList.printSemesters();
            break;
        case Parser.TASK:
            OverallTaskList allTaskList = new OverallTaskList(NoCap.moduleList);
            switch (taskDescription) {
            case SORT_BY_DATE:
                logger.log(Level.INFO, "Sort TaskList by date");
                allTaskList.sortByDateAndPrint();
                break;
            case SORT_BY_STATUS:
                logger.log(Level.INFO, "Sort TaskList by status");
                allTaskList.sortByStatusAndPrint();
                break;
            case SHOW_WEEK:
                logger.log(Level.INFO, "Print weekly TaskList");
                allTaskList.printWeeklyTasks();
                break;
            case SHOW_MONTH:
                logger.log(Level.INFO, "Print monthly TaskList");
                allTaskList.printMonthlyTasks();
                break;
            case SHOW_YEAR:
                allTaskList.printYearlyTasks();
                logger.log(Level.INFO, "Print yearly TaskList");
                break;
            default:
                allTaskList.printAllTasks();
                break;
            }
            break;
        default:
            Ui.printInvalidListFormat();
            break;
        }
    }

    public void moduleListParser(Module module, String input) {
        switch (input) {
        case SORT_BY_DATE:
            sortByDate(module);
            break;
        case SORT_BY_STATUS:
            sortByStatus(module);
            break;
        case SHOW_WEEK:
            listWeekly(module);
            break;
        case SHOW_MONTH:
            listMonthly(module);
            break;
        case SHOW_YEAR:
            listYearly(module);
            break;
        default:
            listAll(module);
            break;
        }
    }
}