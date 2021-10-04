package command;

import java.util.ArrayList;

public class TaskList {
    private static final String EMPTY_STRING = "";

    protected int taskCount;

    /**
     * @param taskList The list of tasks stored in an ArrayList
     */
    public TaskList(ArrayList<Task> taskList) {
        loadTaskCount(taskList);
    }

    public int getTaskCount() {
        return taskCount;
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
    public void loadTaskCount(ArrayList<Task> taskList) {
        this.taskCount = taskList.size();
    }

    /**
     * Create a new Task object and add it to the tasks list
     *
     * @param taskList list of items stored in an ArrayList
     * @param userInput task description input by user
     *
     */
    public void addTask(ArrayList<Task> taskList, String userInput) {
        String date = getDate(userInput);
        if (date.isBlank()) {
            Ui.missingDate();
        } else {
            String description = removeDate(userInput);
            Task newTask = new Task(description, date);
            taskList.add(taskCount, newTask);
        }
    }

}


