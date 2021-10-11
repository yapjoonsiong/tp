package task;

import command.Parser;
import command.Ui;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Comparator;


public class TaskList {
    private static final String EMPTY_STRING = "";
    private static final int DAYS_IN_A_WEEK = 7;
    private static final int DAYS_IN_A_MONTH = 31;
    private static final int DAYS_IN_A_YEAR = 366;
    protected ArrayList<Task> taskList;
    protected int taskCount;


    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    //Getters
    public int getTaskCount() {
        return taskCount;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public int size() {
        return this.taskList.size();
    }

    //Setters
    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void setTaskCount(int taskCount) {
        this.taskCount = taskCount;
    }

    public Task get(int index) {
        return this.taskList.get(index);
    }

    public void delete(Task task) {
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

    public static Comparator<Task> sortByDate = new Comparator<Task>() {
        @Override
        public int compare(Task t1, Task t2) {
            return t1.deadline.compareTo(t2.deadline);
        }
    };

    public static Comparator<Task> sortByStatus = new Comparator<Task>() {
        @Override
        public int compare(Task t1, Task t2) {
            if (t1.isDone && !t2.isDone) {
                return -1;
            }
            if (!t1.isDone && t2.isDone) {
                return 1;
            }
            return 0;
        }
    };

    private boolean isWeekly(Task t) {
        Task refTask = taskList.get(0);
        LocalDateTime refDay = refTask.deadline;
        Period p = Period.between(refDay.toLocalDate(), t.deadline.toLocalDate()).normalized();
        int day = p.getYears() * 366 + p.getMonths() * 31 + p.getDays();
        return day <= DAYS_IN_A_WEEK;
    }

    private boolean isMonthly(Task t) {
        Task refTask = taskList.get(0);
        LocalDateTime refDay = refTask.deadline;
        Period p = Period.between(refDay.toLocalDate(), t.deadline.toLocalDate()).normalized();
        int day = p.getYears() * 366 + p.getMonths() * 31 + p.getDays();
        return day <= DAYS_IN_A_MONTH;
    }

    private boolean isYearly(Task t) {
        Task refTask = taskList.get(0);
        LocalDateTime refDay = refTask.deadline;
        Period p = Period.between(refDay.toLocalDate(), t.deadline.toLocalDate()).normalized();
        int day = p.getYears() * 366 + p.getMonths() * 31 + p.getDays();
        return day <= DAYS_IN_A_YEAR;
    }

    public void showAllWeekly(String module) {
        taskList.sort(sortByDate);
        int index = 1;
        Ui.printWeeklyTaskList(module);
        for (Task task : taskList) {
            if (task != null && isWeekly(task)) {
                System.out.print(index + ".");
                System.out.println(task);
                index++;
            }
        }
    }

    public void showAllMonthly(String module) {
        taskList.sort(sortByDate);
        int index = 1;
        Ui.printMonthlyTaskList(module);
        for (Task task : taskList) {
            if (task != null && isMonthly(task)) {
                System.out.print(index + ".");
                System.out.println(task);
                index++;
            }
        }
    }

    public void showAllYearly(String module) {
        taskList.sort(sortByDate);
        int index = 1;
        Ui.printYearlyTaskList(module);
        for (Task task : taskList) {
            if (task != null && isYearly(task)) {
                System.out.print(index + ".");
                System.out.println(task);
                index++;
            }
        }
    }

    public void printTaskList(String module) {
        taskList.sort(sortByDate);
        taskList.sort(sortByStatus);
        int index = 1;
        Ui.printTaskList(module);
        for (Task task : taskList) {
            if (task != null) {
                System.out.print(index + ".");
                System.out.println(task);
                index++;
            }
        }
    }

    @Override
    public String toString() {
        return taskList.toString();
    }
}


