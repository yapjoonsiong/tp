package module;


import task.TaskList;

import java.util.ArrayList;


public class Module {
    protected String letterGrade;
    protected String moduleName;
    protected Schedule schedule;
    public TaskList taskList;
    private ArrayList<Schedule> scheduleList;
    protected int credits;

    public Module(String moduleName) {
        this.moduleName = moduleName;
        this.letterGrade = null;
        this.scheduleList = new ArrayList<Schedule>();
        this.credits = 0;
        this.taskList = new TaskList();
    }

    /**
     * For deserialization from JSON file.
     */
    public Module() {
    }

    //Getters and Setters
    public String getLetterGrade() {
        return letterGrade;
    }

    public void setLetterGrade(String letterGrade) {
        this.letterGrade = letterGrade;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(ArrayList<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    //Methods
    public int size() {
        return this.scheduleList.size();
    }

    public Schedule get(int index) {
        return this.scheduleList.get(index);
    }

    public void addGrade(String letterGrade) {
        this.letterGrade = letterGrade;
    }

    public void deleteGrade() {
        this.letterGrade = null;
    }

    public void addClass(Schedule schedule) {
        this.scheduleList.add(schedule);
    }

    public void addTask(String userInput) {
        this.taskList.addTask(this.moduleName, userInput);
    }

    public void addCredits(int credits) {
        this.credits = credits;
    }

    @Override
    public String toString() {
        String stringList = this.scheduleList.toString();
        return "Module name: " + moduleName
                + "\nCredits: " + credits
                + "\nSchedule: \n" + scheduleList.toString().substring(1, stringList.length() - 1)
                + "\nGrade: " + letterGrade
                + "\nTasks: " + taskList;
    }
}
