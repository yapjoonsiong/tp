package module;

import command.TaskList;

import java.util.ArrayList;

public class Module {
    protected String letterGrade;
    protected String moduleName;
    //protected Schedule schedule;
    private ArrayList<Schedule> scheduleList;
   // protected Tasklist taskList = new TaskList();
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

//    public void addTask(Task task){
//        this.taskList.add(task);
//    }

    public void addCredits(int credits){
        this.credits = credits;
    }

    /*public String toString(){
        return "Module name: " + moduleName +
                "\nCredits: " + credits +
                "\nSchedule: " + schedule +
                "\nGrade: " + letterGrade;
    }*/
}
