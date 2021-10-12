package task;

import command.Parser;
import command.Ui;

import java.lang.reflect.Array;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TaskList {
    private static final String EMPTY_STRING = "";
    private static final int DAYS_IN_A_WEEK = 7;
    private static final int DAYS_IN_A_MONTH = 31;
    private static final int DAYS_IN_A_YEAR = 366;
    private static final Logger logger = Logger.getLogger(TaskList.class.getName());
    protected ArrayList<Task> taskList;
    protected int taskCount;



    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    //Getters
    public int getTaskCount() {
        logger.log(Level.INFO, "Get task count");
        return taskCount;
    }

    public ArrayList<Task> getTaskList() {
        logger.log(Level.INFO, "Get task list");
        return taskList;
    }

    public int size() {
        logger.log(Level.INFO, "Get size of task list");
        return this.taskList.size();
    }

    //Setters
    public void setTaskList(ArrayList<Task> taskList) {
        logger.log(Level.INFO, "Set task list");
        this.taskList = taskList;
    }

    public void setTaskCount(int taskCount) {
        logger.log(Level.INFO, "Set task count");
        this.taskCount = taskCount;
    }

    public Task get(int index) {
        logger.log(Level.INFO, "Get task");
        return this.taskList.get(index);
    }

    public void delete(Task task) {
        logger.log(Level.INFO, "Delete task");
        taskList.remove(task);
        this.taskCount = taskList.size();
    }

    private static String getDate(String description) {
        try {
            int datePos = description.indexOf(Parser.START_OF_DATE);
            return description.substring(datePos).replace(Parser.START_OF_DATE, EMPTY_STRING).trim();
        } catch (StringIndexOutOfBoundsException e) {
            return EMPTY_STRING;
        }
    }

    private static String removeDate(String description) {
        try {
            int datePos = description.indexOf(Parser.START_OF_DATE);
            return description.substring(0, datePos).trim();
        } catch (StringIndexOutOfBoundsException e) {
            return EMPTY_STRING;
        }
    }

    /**
     * Create a new Task object and add it to the tasks list.
     *
     * @param userInput task description input by user
     */
    public void addTask(String module, String userInput) throws DateTimeException {
        logger.log(Level.INFO, "Add task");
        String date = getDate(userInput);
        if (date.isBlank()) {
            Ui.missingDate();
        } else {
            try {
                String description = removeDate(userInput);
                Task newTask = new Task(description, date);
                this.taskList.add(taskCount, newTask);
                this.taskCount = taskList.size();
                Ui.addTaskMessage(newTask, module);
            } catch (DateTimeException e) {
                Ui.wrongDateTimeFormat();
            }
        }
    }

    public static Comparator<Task> sortByDate = Comparator.comparing(t -> t.deadline);

    public static Comparator<Task> sortByStatus = (t1, t2) -> {
        if (t1.isDone && !t2.isDone) {
            return -1;
        }
        if (!t1.isDone && t2.isDone) {
            return 1;
        }
        return 0;
    };

    private boolean isWeekly(Task refTask, Task t) {
        LocalDateTime refDay = refTask.deadline;
        Period p = Period.between(refDay.toLocalDate(), t.deadline.toLocalDate()).normalized();
        int day = p.getYears() * 366 + p.getMonths() * 31 + p.getDays();
        return day <= DAYS_IN_A_WEEK;
    }

    private boolean isMonthly(Task refTask, Task t) {
        LocalDateTime refDay = refTask.deadline;
        Period p = Period.between(refDay.toLocalDate(), t.deadline.toLocalDate()).normalized();
        int day = p.getYears() * 366 + p.getMonths() * 31 + p.getDays();
        return day <= DAYS_IN_A_MONTH;
    }

    private boolean isYearly(Task refTask, Task t) {
        LocalDateTime refDay = refTask.deadline;
        Period p = Period.between(refDay.toLocalDate(), t.deadline.toLocalDate()).normalized();
        int day = p.getYears() * 366 + p.getMonths() * 31 + p.getDays();
        return day <= DAYS_IN_A_YEAR;
    }

    public void showAllWeekly(String module) {
        ArrayList<Task> tmp = new ArrayList<>(taskList);
        tmp.sort(sortByDate);
        Ui.printWeeklyTaskList(module);
        printWeekly(tmp.get(0));
    }

    public void showAllMonthly(String module) {
        ArrayList<Task> tmp = new ArrayList<>(taskList);
        tmp.sort(sortByDate);
        Ui.printMonthlyTaskList(module);
        printMonthly(tmp.get(0));
    }

    public void showAllYearly(String module) {
        ArrayList<Task> tmp = new ArrayList<>(taskList);
        tmp.sort(sortByDate);
        Ui.printYearlyTaskList(module);
        printYearly(tmp.get(0));
    }

    private void printWeekly(Task tmp) {
        int index = 1;
        for (Task task : taskList) {
            if (task != null && isWeekly(tmp, task)) {
                System.out.print(index + ".");
                System.out.println(task);
                index++;
            }
        }
    }

    private void printMonthly(Task tmp) {
        int index = 1;
        for (Task task : taskList) {
            if (task != null && isMonthly(tmp, task)) {
                System.out.print(index + ".");
                System.out.println(task);
                index++;
            }
        }
    }

    private void printYearly(Task tmp) {
        int index = 1;
        for (Task task : taskList) {
            if (task != null && isYearly(tmp, task)) {
                System.out.print(index + ".");
                System.out.println(task);
                index++;
            }
        }
    }

    public void printTasks(String module) {
        Ui.printTaskList(module);
        int index = 1;
        for (Task task : taskList) {
            if (task != null) {
                System.out.print(index + ".");
                System.out.println(task);
                index++;
            }
        }
    }

    public void sortTaskListByDate(String module) {
        taskList.sort(sortByDate);
    }

    public void sortTaskListByStatus(String module) {
        taskList.sort(sortByStatus);
    }

    @Override
    public String toString() {
        return taskList.toString();
    }
}


