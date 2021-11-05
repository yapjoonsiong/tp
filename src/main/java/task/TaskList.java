package task;

import command.Ui;
import command.parser.DateParser;
import command.parser.ParserChecks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaskList {
    protected static final String EMPTY_STRING = "";
    protected static final int DAYS_IN_A_WEEK = 7;
    protected static final int DAYS_IN_A_MONTH = 31;
    protected static final int DAYS_IN_A_YEAR = 366;
    private static final Logger logger = command.Logger.myLogger();
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

    //assert index is valid
    public Task get(int index) {
        logger.log(Level.INFO, "Get task");
        return this.taskList.get(index);
    }

    public void delete(Task task) {
        logger.log(Level.INFO, "Successfully deleted task");
        taskList.remove(task);
        this.taskCount = taskList.size();
    }

    protected static String getDate(String description) {
        try {
            int datePos = description.indexOf(ParserChecks.START_OF_DATE);
            return description.substring(datePos).replace(ParserChecks.START_OF_DATE, EMPTY_STRING).trim();
        } catch (StringIndexOutOfBoundsException e) {
            return EMPTY_STRING;
        }
    }

    /**
     * Trims the user input to get the description of the task.
     * This is done by removing the date component from the input string.
     *
     * @param description the task description input by the user that also contains the deadline of the task.
     * @return a string that contains only the task description.
     */
    protected static String removeDate(String description) {
        try {
            int datePos = description.indexOf(ParserChecks.START_OF_DATE);
            return description.substring(0, datePos).trim();
        } catch (StringIndexOutOfBoundsException e) {
            return EMPTY_STRING;
        }
    }

    /**
     * Checks for the validity of the new task input and trim the input for the
     * important components such as date and task description.
     * If there is no error, then update the task list with the new task.
     *
     * @param module    module that the task is for
     * @param userInput user input that consist of the task description and deadline of the task
     */
    public void addTask(String module, String userInput) {
        String date = getDate(userInput);
        if (date.isBlank()) {
            Ui.missingDate();
            return;
        }
        String description = removeDate(userInput);
        if (description.isBlank()) {
            Ui.missingDescription();
            return;
        }
        try {
            LocalDateTime newTaskDeadline = DateParser.parseDate(date);
            if (hasDuplicateDescription(description) && hasDuplicateDeadline(newTaskDeadline)) {
                Ui.duplicateTaskError();
                return;
            }
            if (hasDuplicateDescription(description)) {
                updateTaskDeadline(date, description);
                Ui.taskUpdateMessage();
                return;
            }
            updateTaskList(module, date, description);
        } catch (DateTimeException e) {
            Ui.wrongDateTimeFormat();
        }
    }

    /**
     * Update the selected task deadline with a new user input deadline.
     *
     * @param date the deadline of the task
     * @param description the description of the task
     */
    private void updateTaskDeadline(String date, String description) {
        for (Task task : this.taskList) {
            if (description.toLowerCase(Locale.ROOT).equals(task.getDescription().toLowerCase(Locale.ROOT))) {
                this.taskList.remove(task);
                Task newTask = new Task(description, date);
                this.taskList.add(newTask);
                return;
            }
        }
    }

    /**
     * Add new task to the task list.
     *
     * @param module the module that is currently accessed
     * @param date the deadline that the user input for the task
     * @param description the description of the task that the user added
     */
    private void updateTaskList(String module, String date, String description) {
        Task newTask = new Task(description, date);
        this.taskList.add(taskCount, newTask);
        this.taskCount = taskList.size();
        Ui.addTaskMessage(newTask, module);
        logger.log(Level.INFO, "Successfully added task");
    }

    /**
     * Checks if there is duplication in the task list.
     * This is done by iterating through the task list.
     *
     * @param newTaskDescription the new task description input by user
     * @return a boolean value, true if there is duplication found
     */
    public boolean hasDuplicateDescription(String newTaskDescription) {
        for (Task task : this.taskList) {
            String taskDescription = task.getDescription().toLowerCase(Locale.ROOT);
            if (newTaskDescription.toLowerCase(Locale.ROOT).equals(taskDescription)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasDuplicateDeadline(LocalDateTime newTaskDate) {
        for (Task task : this.taskList) {
            LocalDateTime taskDate = task.getDeadline();
            if (newTaskDate.equals(taskDate)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Custom comparator to determine if the task is of higher priority than the other based on custom condition
     * such as task deadline. The task deadline is of LocalDateTime data type.
     * A task with a more recent deadline will be 'greater' than a task with a later deadline.
     * Therefore, tasks with deadline closer to the current system date will be sorted to
     * the top of the list if this comparator is used for sorting.
     */
    protected static final Comparator<Task> sortByDate = Comparator.comparing(t -> t.deadline);

    /**
     * Custom comparator to determine if the task is of higher priority than the other based on custom condition
     * such as completion status. If the task is completed, it will have a LOW priority.
     * If a task is not completed, it will have a HIGH priority.
     * Sorting a list of task using this comparator to sort will put the tasks that is not completed
     * at the top of the list while the tasks that are done will be sorted to the bottom of the list.
     */
    protected static final Comparator<Task> sortByStatus = (t1, t2) -> {
        if (t1.isDone && !t2.isDone) {
            return Priority.HIGH.compareTo(Priority.LOW);
        }
        if (!t1.isDone && t2.isDone) {
            return Priority.LOW.compareTo(Priority.HIGH);
        }
        return Priority.EQUAL.ordinal();
    };

    /**
     * Determines if the task fits under the weekly task list by checking its deadline against the system clock.
     *
     * @param t the task in the task list.
     * @return true if the period of the current date to the task's deadline is within 7 days.
     */
    protected boolean isWeekly(Task t) {
        LocalDate date = LocalDate.now();
        Period p = Period.between(date, t.deadline.toLocalDate()).normalized();
        int day = p.getYears() * DAYS_IN_A_YEAR + p.getMonths() * DAYS_IN_A_MONTH + p.getDays();
        return day <= DAYS_IN_A_WEEK;
    }

    /**
     * Determines if the task fits under the monthly task list by checking its deadline against the system clock.
     *
     * @param t the task in the task list.
     * @return true if the period of the current date to the task's deadline is within 30 days.
     */
    protected boolean isMonthly(Task t) {
        LocalDate date = LocalDate.now();
        Period p = Period.between(date, t.deadline.toLocalDate()).normalized();
        int day = p.getYears() * DAYS_IN_A_YEAR + p.getMonths() * DAYS_IN_A_MONTH + p.getDays();
        return day <= DAYS_IN_A_MONTH;
    }

    /**
     * Determines if the task fits under the yearly task list by checking its deadline against the system clock.
     *
     * @param t the task in the task list.
     * @return true if the period of the current date to the task's deadline is within a year.
     */
    protected boolean isYearly(Task t) {
        LocalDate date = LocalDate.now();
        Period p = Period.between(date, t.deadline.toLocalDate()).normalized();
        int day = p.getYears() * DAYS_IN_A_YEAR + p.getMonths() * DAYS_IN_A_MONTH + p.getDays();
        return day <= DAYS_IN_A_YEAR;
    }

    public void showAllWeekly(String module) {
        logger.log(Level.INFO, "Printing weekly tasks list...");
        ArrayList<Task> list = new ArrayList<>(weeklyTaskList());
        Ui.printWeeklyTaskList(module, list.size());
        printTasks(list);
    }

    public void showAllMonthly(String module) {
        logger.log(Level.INFO, "Printing monthly tasks list...");
        ArrayList<Task> list = new ArrayList<>(monthlyTaskList());
        Ui.printMonthlyTaskList(module, list.size());
        printTasks(list);
    }

    public void showAllYearly(String module) {
        logger.log(Level.INFO, "Printing yearly tasks list...");
        ArrayList<Task> list = new ArrayList<>(yearlyTaskList());
        Ui.printYearlyTaskList(module, list.size());
        printTasks(list);
    }

    /**
     * Get all weekly tasks in a list.
     *
     * @return an arraylist which is the list of weekly tasks
     */
    public ArrayList<Task> weeklyTaskList() {
        ArrayList<Task> list = new ArrayList<>();
        for (Task task : taskList) {
            assert (task != null);
            if (isWeekly(task)) {
                list.add(task);
            }
        }
        return list;
    }

    /**
     * Get all monthly tasks in a list.
     *
     * @return an arraylist which is the list of monthly tasks
     */
    public ArrayList<Task> monthlyTaskList() {
        ArrayList<Task> list = new ArrayList<>();
        for (Task task : taskList) {
            assert (task != null);
            if (isMonthly(task)) {
                list.add(task);
            }
        }
        return list;
    }

    /**
     * Get all yearly tasks in a list.
     *
     * @return an arraylist which is the list of yearly tasks
     */
    public ArrayList<Task> yearlyTaskList() {
        ArrayList<Task> list = new ArrayList<>();
        for (Task task : taskList) {
            assert (task != null);
            if (isYearly(task)) {
                list.add(task);
            }
        }
        return list;
    }

    public void printTasks(ArrayList<Task> taskList) {
        int index = 1;
        for (Task task : taskList) {
            if (task != null) {
                task.updateOverdue();
                System.out.print(index + ".");
                System.out.println(task);
                index++;
            }
        }
    }

    public void sortTaskListByDate(String module) {
        logger.log(Level.INFO, "Sorting tasks list by date...");
        taskList.sort(sortByDate);
        Ui.printSortListByDate(module);
    }

    public void sortTaskListByStatus(String module) {
        logger.log(Level.INFO, "Sorting tasks list by status...");
        taskList.sort(sortByStatus);
        Ui.printSortListByStatus(module);
    }

    @Override
    public String toString() {
        return taskList.toString();
    }
}


