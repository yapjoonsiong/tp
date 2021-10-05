package module;

import command.TaskList;

import java.util.ArrayList;

public class Module {
    protected String letterGrade;
    protected String moduleName;
    protected Schedule schedule;
    protected TaskList taskList;
    protected int credits;

    public Module(String moduleName){
        this.moduleName = moduleName;
        this.letterGrade = null;
        this.scheduleList = new ArrayList<Schedule>();
        this.credits = 0;
    }
    public int size() {
        return this.scheduleList.size();
    }

    public Schedule get(int index) {
        return this.scheduleList.get(index);
    }

    public void addGrade(String letterGrade){
        this.letterGrade = letterGrade;
    }

    public void addClass(Schedule schedule){
        this.scheduleList.add(schedule);
    }

    public void addTask(String userInput){
        this.taskList.addTask(userInput);
    }

    public void addCredits(int credits){
        this.credits = credits;
    }

    public String toString(){
        return "Module name: " + moduleName +
                "\nCredits: " + credits +
                "\nSchedule: " + schedule +
                "\nGrade: " + letterGrade +
                "\nTasks: " + taskList;
    }
}
