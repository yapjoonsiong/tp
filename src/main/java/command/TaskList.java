package command;

import java.util.ArrayList;

public class TaskList {
    private static final String EMPTY_STRING = "";
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
    public void addTask(String userInput) {
        String date = getDate(userInput);
        if (date.isBlank()) {
            Ui.missingDate();
        } else {
            String description = removeDate(userInput);
            Task newTask = new Task(description, date);
            this.taskList.add(taskCount, newTask);
        }
    }

    public void printTaskList(String module) {
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


