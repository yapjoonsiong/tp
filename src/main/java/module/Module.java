package module;


import command.NoCap;

import command.VisualiseGradable;

import exceptions.NoCapExceptions;

import schedule.Schedule;
import schedule.ScheduleList;
import task.GradableTaskList;
import task.Task;
import task.TaskList;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Module {
    protected String letterGrade;
    protected double points;
    protected String moduleName;
    public TaskList taskList;
    private ScheduleList scheduleList;
    protected int credits;
    public GradableTaskList gradableTaskList;
    private static final Logger logger = command.Logger.myLogger();

    /**
     * Constructor to create an instance of Module.
     * Empty SchdeuleList, TaskList and GradableTaskList is created
     * Credits is automatically assigned 0
     * LetterGrade automatically assigned to null
     *
     * @param moduleName Name of module to be created
     */
    public Module(String moduleName) throws NoCapExceptions {
        if (moduleName.length() > 16) {
            throw new NoCapExceptions("Module name must be less than 17 characters");
        }
        this.moduleName = moduleName;
        this.letterGrade = "NIL";
        this.scheduleList = new ScheduleList();
        this.credits = 0;
        this.points = 0;
        this.taskList = new TaskList();
        this.gradableTaskList = new GradableTaskList();
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

    public TaskList getTaskList() {
        return taskList;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    public ScheduleList getScheduleList() {
        return this.scheduleList;
    }

    public void setScheduleList(ScheduleList scheduleList) {
        this.scheduleList = scheduleList;
    }

    public GradableTaskList getGradableTaskList() {
        return this.gradableTaskList;
    }

    public void addGradableTask(String userInput) {
        this.gradableTaskList.addGradableTask(this.moduleName, userInput);
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
        assert index >= 0;
        return this.scheduleList.getSchedule(index);
    }

    /**
     * +
     * Add grade to the module.
     * Points are automatically update when the grade is added.
     *
     * @param letterGrade grade of module
     */

    public void addGrade(String letterGrade) {
        this.letterGrade = letterGrade;
        updatePoints();
    }

    public void deleteGrade() {
        this.letterGrade = "NIL";
        updatePoints();
    }

    private void updatePoints() {
        switch (letterGrade) {
        case "A+":
        case "A":
            points = 5;
            break;
        case "A-":
            points = 4.5;
            break;
        case "B+":
            points = 4.0;
            break;
        case "B":
            points = 3.5;
            break;
        case "B-":
            points = 3.0;
            break;
        case "C+":
            points = 2.5;
            break;
        case "C":
            points = 2.0;
            break;
        case "D+":
            points = 1.5;
            break;
        case "D":
            points = 1.0;
            break;
        case "F":
        default:
            points = 0;
            break;
        }
    }

    public double getPoints() {
        return points;
    }

    public void addClass(Schedule schedule) {
        this.scheduleList.addClass(schedule);
    }

    //overloading to take in String input * Added by jiexiong to keep Parser clean

    /**
     * Overload addClass method to take in String input.
     * Adds a Class object with corresponding details.
     *
     * @param input User input class details.
     * @throws NoCapExceptions Throws exception when Class exists in a slot or when input is wrongly formatted.
     */
    public void addClass(String input) throws NoCapExceptions {
        this.scheduleList.addClass(input);
    }

    public void deleteClass(String input) {
        this.scheduleList.deleteClass(input);
    }

    public void addTask(String userInput) {
        this.taskList.addTask(this.moduleName, userInput);
    }

    public void deleteTask(Task task) {
        this.taskList.delete(task);
    }

    public void addCredits(int credits) throws NoCapExceptions {
        if (credits < 0) {
            throw new NoCapExceptions("credit must be an integer > 0");
        }
        this.credits = credits;
    }

    public void showInformation() {
        System.out.println(toString());
    }

    /**
     * Reformats Module for easier viewing.
     */
    @Override
    public String toString() {

        return "Module name: " + moduleName
                + "\nCREDITS: " + credits
                + "\n--------------------------- "
                + "\nSCHEDULE: \n" + scheduleList
                + "--------------------------- "
                + "\nGRADE: " + letterGrade
                + "\nTASKS: " + taskList
                + "\nBREAKDOWN: \n" + gradableTaskList;
    }
}
