package command.parser;

import command.NoCap;
import command.Ui;
import module.Module;
import task.OverallTaskList;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListParser {
    private static final Logger logger = Logger.getLogger(ListParser.class.getName());

    public ListParser() {
    }

    boolean isEmpty(Module module) {
        if (module.taskList.size() == 0) {
            Ui.printEmptyTaskList(module.getModuleName());
            return true;
        }
        return false;
    }

    void sortByDate(ArrayList<Module> moduleList) {
        for (Module module : moduleList) {
            if (!isEmpty(module)) {
                module.taskList.sortTaskListByDate(module.getModuleName());
            }
        }
    }

    void sortByStatus(ArrayList<Module> moduleList) {
        for (Module module : moduleList) {
            if (!isEmpty(module)) {
                module.taskList.sortTaskListByStatus(module.getModuleName());
            }
        }
    }

    void listWeekly(ArrayList<Module> moduleList) {
        for (Module module : moduleList) {
            if (!isEmpty(module)) {
                module.taskList.showAllWeekly(module.getModuleName());
            }
        }
    }

    void listAll(ArrayList<Module> moduleList) {
        for (Module module : moduleList) {
            if (!isEmpty(module)) {
                Ui.printTaskList(module.getModuleName(), module.taskList.getTaskCount());
                module.taskList.printTasks(module.taskList.getTaskList());
            }
        }
    }

    void listMonthly(ArrayList<Module> moduleList) {
        for (Module module : moduleList) {
            if (!isEmpty(module)) {
                module.taskList.showAllMonthly(module.getModuleName());
            }
        }
    }

    void listYearly(ArrayList<Module> moduleList) {
        for (Module module : moduleList) {
            if (!isEmpty(module)) {
                module.taskList.showAllYearly(module.getModuleName());
            }
        }
    }

    void listParser(String input) {
        Parser.splitInput(input);
        if (Parser.taskType.equals(Parser.MODULE)) {
            NoCap.moduleList.printModules();
        } else if (Parser.taskType.equals(Parser.TASK)) {
            //ArrayList<Module> moduleList = new ArrayList<Module>(NoCap.moduleList.getModuleList());
            OverallTaskList allTaskList = new OverallTaskList(NoCap.moduleList);
            switch (Parser.taskDescription) {
            case Parser.SORT_BY_DATE:
                logger.log(Level.INFO, "Sort TaskList by date");
                allTaskList.sortByDateAndPrint();
                //sortByDate(moduleList);
                break;
            case Parser.SORT_BY_STATUS:
                logger.log(Level.INFO, "Sort TaskList by status");
                allTaskList.sortByStatusAndPrint();
                //sortByStatus(moduleList);
                break;
            case Parser.SHOW_WEEK:
                logger.log(Level.INFO, "Print weekly TaskList");
                allTaskList.printWeeklyTasks();
                //listWeekly(moduleList);
                break;
            case Parser.SHOW_MONTH:
                logger.log(Level.INFO, "Print monthly TaskList");
                allTaskList.printMonthlyTasks();
                //listMonthly(moduleList);
                break;
            case Parser.SHOW_YEAR:
                allTaskList.printYearlyTasks();
                logger.log(Level.INFO, "Print yearly TaskList");
                //listYearly(moduleList);
                break;
            default:
                allTaskList.printList();
                System.out.println("Showing all tasks");
                //listAll(moduleList);
                break;
            }
        } else {
            Ui.printInvalidListFormat();
        }
    }
}