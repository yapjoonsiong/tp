package module;

import command.TaskList;

public class Module {
    protected String letterGrade;
    protected String moduleName;
    protected Schedule schedule;
   // protected Tasklist taskList = new TaskList();
    protected int credits;

    public Module(String moduleName){
        this.moduleName = moduleName;
        this.letterGrade = null;
        this.schedule = null;
        this.credits = 0;
    }

    public void addGrade(String letterGrade){
        this.letterGrade = letterGrade;
    }

    public void addClass(Schedule schedule){
        this.schedule = schedule;
    }

//    public void addTask(Task task){
//        this.taskList.add(task);
//    }

    public void addCredits(int credits){
        this.credits = credits;
    }

    public String toString(){
        return "Module name: " + moduleName +
                "\nCredits: " + credits +
                "\nSchedule: " + schedule +
                "\nGrade: " + letterGrade;
    }
}
