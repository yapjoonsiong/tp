package command.parser;

import command.NoCap;
import command.Ui;
import command.storage.StorageEncoder;
import exceptions.NoCapExceptions;
import module.Module;
import task.GradableTask;
import task.Task;

import java.util.Locale;

public class Command {

    private final ParserChecks parserChecks = new ParserChecks();

    public Command() {
    }

    void commandSwitchSemester(String taskDescription) {
        if (parserChecks.isEmptyDescription(taskDescription)
                | parserChecks.isNotInteger(taskDescription)) {
            return;
        }
        try {
            NoCap.semesterList.setAccessedSemesterIndex(Integer.parseInt(taskDescription) - 1);
            Ui.switchSemesterMessage(NoCap.semesterList
                    .get(Integer.parseInt(taskDescription) - 1).getSemester());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    void commandPrintCap() {
        NoCap.semester.printCap();
    }

    void commandPrintAllCap() {
        NoCap.semesterList.printAllCap();
    }

    void commandPrintTimeTable() {
        NoCap.moduleList.printTimeTable();
    }

    void commandShowInfo(Module module) {
        module.showInformation();
    }

    void commandAddModule(String taskDescription) {
        if (parserChecks.isEmptyDescription(taskDescription)
                || parserChecks.isDuplicateModule(taskDescription)) {
            return;
        }
        NoCap.moduleList.add(taskDescription.toUpperCase(Locale.ROOT));
        Ui.addModuleNameMessage(NoCap.moduleList);
        StorageEncoder.encodeAndSaveSemesterListToJson(NoCap.semesterList);
    }

    void commandAddClass(Module module, String taskDescription) {
        if (parserChecks.isEmptyDescription(taskDescription)) {
            return;
        }
        try {
            module.addClass(taskDescription);
            Ui.addModuleClassMessage(module);
            //**can you explain this
        } catch (NoCapExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    void commandAddTask(Module module, String taskDescription) {
        if (parserChecks.isEmptyDescription(taskDescription) || !parserChecks.hasDateDescription(taskDescription)) {
            return;
        }
        module.addTask(taskDescription);
    }

    void commandAddGradable(Module module, String taskDescription) {
        if (parserChecks.isEmptyDescription(taskDescription)
                || !parserChecks.hasDateDescription(taskDescription)
                || !parserChecks.hasWeightageDescription(taskDescription)) {
            return;
        }
        module.addGradableTask(taskDescription);
    }

    void commandAddGrade(Module module, String taskDescription) {
        if (parserChecks.isEmptyDescription(taskDescription)) {
            return;
        }
        module.addGrade(taskDescription);
        Ui.addModuleGradeMessage(module);
        NoCap.semester.updateCap();
        NoCap.semesterList.updateCap();
    }

    void commandAddCredit(Module module, String taskDescription) {
        if (parserChecks.isEmptyDescription(taskDescription)) {
            return;
        }
        module.addCredits(Integer.parseInt(taskDescription));
        Ui.addModuleCreditsMessage(module);
        NoCap.semester.updateCap();
        NoCap.semesterList.updateCap();
    }

    void commandDeleteModule(String taskDescription) {
        if (parserChecks.isEmptyDescription(taskDescription)
                || parserChecks.isNotInteger(taskDescription)) {
            return;
        }
        try {
            NoCap.moduleList.delete(taskDescription);
            StorageEncoder.encodeAndSaveSemesterListToJson(NoCap.semesterList);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid number value");
        }
    }

    void commandDeleteClass(Module module) {
        module.deleteClass();
    }

    void commandDeleteTask(Module module, String taskDescription) {
        if (parserChecks.isEmptyDescription(taskDescription)) {
            return;
        }
        Task selectedTask = parserChecks.getTaskFromKeyword(taskDescription, module.taskList.getTaskList());
        if (selectedTask != null) {
            module.deleteTask(selectedTask);
        }
    }

    void commandDeleteGrade(Module module) {
        module.deleteGrade();
        NoCap.semester.updateCap();
        NoCap.semesterList.updateCap();
    }

    void commandEditDescription(Module module, String taskType, String taskDescription) {
        Task selectedTask = parserChecks.getTaskFromIndex(taskType, module.taskList.getTaskList());
        if (selectedTask != null) {
            selectedTask.setDescription(taskDescription);
        }
    }

    void commandEditDeadline(Module module, String taskType, String taskDescription) {
        Task selectedTask = parserChecks.getTaskFromIndex(taskType, module.taskList.getTaskList());
        if (selectedTask != null) {
            selectedTask.parseDeadline(taskDescription);
        }
    }

    void commandMarkDone(Module module, String taskDescription) {
        if (parserChecks.isEmptyDescription(taskDescription)) {
            return;
        }
        Task selectedTask = parserChecks.getTaskFromIndex(taskDescription, module.taskList.getTaskList());
        if (selectedTask != null) {
            selectedTask.markDone();
        }
    }

    void commandMarkNotDone(Module module, String taskDescription) {
        if (parserChecks.isEmptyDescription(taskDescription)) {
            return;
        }
        Task selectedTask = parserChecks.getTaskFromIndex(taskDescription, module.taskList.getTaskList());
        if (selectedTask != null) {
            selectedTask.markNotDone();
        }
    }

    void commandMarkGradableDone(Module module, String taskDescription) {
        if (parserChecks.isEmptyDescription(taskDescription)) {
            return;
        }
        GradableTask selectedTask = parserChecks.getGradableTaskFromIndex(taskDescription, module.gradableTaskList.getGradableTaskList());
        if (selectedTask != null) {
            selectedTask.markDone();
        }
    }

    void commandMarkGradableNotDone(Module module, String taskDescription) {
        if (parserChecks.isEmptyDescription(taskDescription)) {
            return;
        }
        GradableTask selectedTask = parserChecks.getGradableTaskFromIndex(taskDescription, module.gradableTaskList.getGradableTaskList());
        if (selectedTask != null) {
            selectedTask.markNotDone();
        }
    }
}