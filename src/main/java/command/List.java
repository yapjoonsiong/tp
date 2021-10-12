package command;

import module.Module;

import java.util.ArrayList;
import java.util.logging.Level;

public class List {
    public List() {
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
                module.taskList.printTasks(module.getModuleName());
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
            ArrayList<Module> moduleList = new ArrayList<Module>(NoCap.moduleList.getModuleList());
            switch (Parser.taskDescription) {
            case Parser.SORT_BY_DATE:
                sortByDate(moduleList);
                break;
            case Parser.SORT_BY_STATUS:
                sortByStatus(moduleList);
                break;
            case Parser.SHOW_ALL:
                listAll(moduleList);
                break;
            case Parser.SHOW_WEEK:
                listWeekly(moduleList);
                break;
            case Parser.SHOW_MONTH:
                listMonthly(moduleList);
                break;
            case Parser.SHOW_YEAR:
                listYearly(moduleList);
                break;
            default:
                System.out.println("Showing all");
                listAll(moduleList);
                break;
            }
        } else {
            Ui.printInvalidListFormat();
        }
    }
}