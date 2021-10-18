package task;

import module.Module;
import module.ModuleList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OverallTaskList {
    protected ArrayList<OverallTask> overallTaskList;

    public OverallTaskList() {
        this.overallTaskList = new ArrayList<>();
    }

    public ArrayList<OverallTask> getOverallTaskList() {
        return overallTaskList;
    }

    public void addAllModuleListTasks(ModuleList moduleList) {
        for (Module module : moduleList.getModuleList()) {
            for (Task task : module.getTaskList().taskList) {
                String moduleName = module.getModuleName();
                overallTaskList.add(new OverallTask(task.getDescription(), task.getDate(), moduleName, task.isDone));
            }
        }
    }

    public void printSortByDate() {
        List<OverallTask> newTaskList = overallTaskList
                .stream()
                .sorted(OverallTask.dateComparator)
                .collect(Collectors.toList());
        System.out.println(newTaskList);
    }

}
