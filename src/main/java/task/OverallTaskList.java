package task;

import command.Ui;
import module.Module;
import module.ModuleList;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class OverallTaskList extends TaskList{
    private static final Logger logger = Logger.getLogger(OverallTaskList.class.getName());
    private static final int DAYS_IN_A_WEEK = 7;
    private static final int DAYS_IN_A_MONTH = 31;
    private static final int DAYS_IN_A_YEAR = 366;


    protected ArrayList<OverallTask> overallTaskList;

    public OverallTaskList(ModuleList moduleList) {
        this.overallTaskList = new ArrayList<>();
        addAllModuleListTasks(moduleList);
    }

    public ArrayList<OverallTask> getOverallTaskList() {
        return overallTaskList;
    }

    public void addAllModuleListTasks(ModuleList moduleList) {
        for (Module module : moduleList.getModuleList()) {
            for (Task task : module.getTaskList().taskList) {
                String moduleName = module.getModuleName();
                overallTaskList.add(new OverallTask(task.getDescription(), task.getDate(), moduleName, task.isDone));
            }
        }
        logger.log(Level.INFO, "Add all tasks from module list to overall task list");
    }

    public void sortByDateAndPrint() {
        List<OverallTask> newTaskList = overallTaskList
                .stream()
                .sorted(OverallTask.dateComparator)
                .collect(Collectors.toList());
        Ui.printOverallListOrderedByDate(newTaskList);
        logger.log(Level.INFO, "Sort overall task by date");
    }

    public void sortByStatusAndPrint() {
        List<OverallTask> newTaskList = overallTaskList
                .stream()
                .sorted(OverallTask.statusComparator.reversed())
                .collect(Collectors.toList());
        Ui.printOverallListOrderedByStatus(newTaskList);
        logger.log(Level.INFO, "Sort overall task by status");
    }

    public void printWeeklyTasks() {
        List<OverallTask> newTaskList = overallTaskList
                .stream()
                .filter(this::isWithinWeek)
                .collect(Collectors.toList());
        Ui.printOverallWeeklyTasks(newTaskList);
        logger.log(Level.INFO,"print overall weekly tasks");
    }

    private boolean isWithinWeek(OverallTask task) {
        LocalDate date = LocalDate.now();
        Period p = Period.between(date, task.deadline.toLocalDate()).normalized();
        int day = p.getYears() * 366 + p.getMonths() * 31 + p.getDays();
        return day > 0 && day <= DAYS_IN_A_WEEK;
    }

    public void printMonthlyTasks() {
        List<OverallTask> newTaskList = overallTaskList
                .stream()
                .filter(this::isWithinMonth)
                .collect(Collectors.toList());
        Ui.printOverallMonthlyTasks(newTaskList);
        logger.log(Level.INFO,"print overall monthly tasks");
    }

    private boolean isWithinMonth(OverallTask task) {
        LocalDate date = LocalDate.now();
        Period p = Period.between(date, task.deadline.toLocalDate()).normalized();
        int day = p.getYears() * 366 + p.getMonths() * 31 + p.getDays();
        return day >= 0 && day <= DAYS_IN_A_MONTH;
    }

    public void printYearlyTasks() {
        List<OverallTask> newTaskList = overallTaskList
                .stream()
                .filter(this::isWithinYear)
                .collect(Collectors.toList());
        Ui.printOverallYearlyTasks(newTaskList);
        logger.log(Level.INFO,"print overall yearly tasks");
    }

    private boolean isWithinYear(OverallTask task) {
        LocalDate date = LocalDate.now();
        Period p = Period.between(date, task.deadline.toLocalDate()).normalized();
        int day = p.getYears() * 366 + p.getMonths() * 31 + p.getDays();
        return day >= 0 && day <= DAYS_IN_A_YEAR;
    }


    public void printList() {
        int taskNumber = 1;
        for (OverallTask task : overallTaskList) {
            System.out.println(taskNumber + ". " + task);
            taskNumber++;
        }
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
