package task;

import command.Ui;
import module.Module;
import module.ModuleList;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Represents a list of OverallTask objects. Used for listing all tasks in a semester.
 */
public class OverallTaskList extends TaskList {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    protected ArrayList<OverallTask> overallTaskList;

    /**
     * Constructor which takes in a ModuleList object.
     * Adds in all tasks in the ModuleList object into OverallTaskList.
     *
     * @param moduleList The ModuleList object containing the tasks to be added.
     */
    public OverallTaskList(ModuleList moduleList) {
        this.overallTaskList = new ArrayList<>();
        addAllModuleListTasks(moduleList);
    }

    private void addAllModuleListTasks(ModuleList moduleList) {
        for (Module module : moduleList.getModuleList()) {
            String moduleName = module.getModuleName();
            for (Task task : module.getTaskList().taskList) {
                task.updateOverdue();
                overallTaskList.add(new OverallTask(task, moduleName));
            }
            for (GradableTask gradableTask : module.getGradableTaskList().gradableTaskList) {
                gradableTask.updateOverdue();
                overallTaskList.add(new OverallTask(gradableTask, moduleName));
            }
        }
        logger.log(Level.INFO, "Add all tasks from module list to overall task list");
    }

    /**
     * Sorts all tasks by deadline in the task list and prints it out to output.
     */
    public void sortByDateAndPrint() {
        List<OverallTask> newTaskList = overallTaskList
                .stream()
                .sorted(OverallTask.dateComparator)
                .collect(Collectors.toList());
        Ui.printOverallListOrderedByDate(newTaskList);
        logger.log(Level.INFO, "Sort overall task by date");
    }

    /**
     *  all tasks in task list by status and prints it out to the output.
     */
    public void sortByStatusAndPrint() {
        List<OverallTask> newTaskList = overallTaskList
                .stream()
                .sorted(OverallTask.statusComparator.reversed())
                .collect(Collectors.toList());
        Ui.printOverallListOrderedByStatus(newTaskList);
        logger.log(Level.INFO, "Sort overall task by status");
    }

    /**
     * Prints all tasks due within the next week.
     */
    public void printWeeklyTasks() {
        List<OverallTask> newTaskList = overallTaskList
                .stream()
                .filter(this::isWeekly)
                .collect(Collectors.toList());
        Ui.printOverallWeeklyTasks(newTaskList);
        logger.log(Level.INFO, "print overall weekly tasks");
    }

    /**
     * Prints all tasks due within the month.
     */
    public void printMonthlyTasks() {
        List<OverallTask> newTaskList = overallTaskList
                .stream()
                .filter(this::isMonthly)
                .collect(Collectors.toList());
        Ui.printOverallMonthlyTasks(newTaskList);
        logger.log(Level.INFO, "print overall monthly tasks");
    }

    /**
     * Prints all tasks due within a year.
     */
    public void printYearlyTasks() {
        List<OverallTask> newTaskList = overallTaskList
                .stream()
                .filter(this::isYearly)
                .collect(Collectors.toList());
        Ui.printOverallYearlyTasks(newTaskList);
        logger.log(Level.INFO, "print overall yearly tasks");
    }

    /**
     * Prints all gradable tasks in the task list.
     */
    public void printGradableTasks() {
        List<OverallTask> newTaskList = overallTaskList
                .stream()
                .filter(OverallTask::isGradable)
                .collect(Collectors.toList());
        Ui.printGradableTasks(newTaskList);
        logger.log(Level.INFO, "print gradable tasks");
    }

    /**
     * Prints all the tasks in the task list without any sorting.
     */
    public void printAllTasks() {
        Ui.printAllOverallTasks(overallTaskList);
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (OverallTask task : overallTaskList) {
            out.append(task.toString()).append(System.lineSeparator());
        }
        return out.toString();
    }


}
