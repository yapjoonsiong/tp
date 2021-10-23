package command.parser;

import command.NoCap;
import command.Ui;
import command.storage.StorageEncoder;
import exceptions.NoCapExceptions;
import module.Module;
import task.Task;

import java.util.Locale;

public class Command {

    public static Module module;
    private final ParserChecks parserChecks = new ParserChecks();

    public Command() {
    }

    void commandSwitchSemester() {
        if (parserChecks.isEmptyDescription(Parser.taskDescription)
                | parserChecks.isNotInteger(Parser.taskDescription)) {
            return;
        }
        try {
            NoCap.semesterList.setAccessedSemesterIndex(Integer.parseInt(Parser.taskDescription) - 1);
            Ui.switchSemesterMessage(NoCap.semesterList
                    .get(Integer.parseInt(Parser.taskDescription) - 1).getSemester());
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

    void commandShowInfo() {
        module.showInformation();
    }

    void commandAddModule() {
        if (parserChecks.isEmptyDescription(Parser.taskDescription)
                | parserChecks.isDuplicateModule(Parser.taskDescription)) {
            return;
        }
        NoCap.moduleList.add(Parser.taskDescription.toUpperCase(Locale.ROOT));
        Ui.addModuleNameMessage(NoCap.moduleList);
        StorageEncoder.encodeAndSaveSemesterListToJson(NoCap.semesterList);
    }

    void commandAddClass() {
        if (parserChecks.isEmptyDescription(Parser.taskDescription)) {
            return;
        }
        try {
            module.addClass(Parser.taskDescription);
            Ui.addModuleClassMessage(module);
            //**can you explain this
        } catch (NoCapExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    void commandAddTask() {
        if (parserChecks.isEmptyDescription(Parser.taskDescription)
                | !parserChecks.hasDateDescription(Parser.taskDescription)) {
            return;
        }
        module.addTask(Parser.taskDescription);
    }

    void commandAddGradable() {
        if (parserChecks.isEmptyDescription(Parser.taskDescription)
                | !parserChecks.hasDateDescription(Parser.taskDescription)
                | !parserChecks.hasWeightageDescription(Parser.taskDescription)) {
            return;
        }
        module.addGradableTask(Parser.taskDescription);
    }

    void commandAddGrade() {
        if (parserChecks.isEmptyDescription(Parser.taskDescription)) {
            return;
        }
        module.addGrade(Parser.taskDescription);
        Ui.addModuleGradeMessage(module);
        NoCap.semester.updateCap();
        NoCap.semesterList.updateCap();
    }

    void commandAddCredit() {
        if (parserChecks.isEmptyDescription(Parser.taskDescription)) {
            return;
        }
        module.addCredits(Integer.parseInt(Parser.taskDescription));
        Ui.addModuleCreditsMessage(module);
        NoCap.semester.updateCap();
        NoCap.semesterList.updateCap();
    }

    void commandDeleteModule() {
        if (parserChecks.isEmptyDescription(Parser.taskDescription)
                | parserChecks.isNotInteger(Parser.taskDescription)) {
            return;
        }
        try {
            NoCap.moduleList.delete(Parser.taskDescription);
            StorageEncoder.encodeAndSaveSemesterListToJson(NoCap.semesterList);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid number value");
        }
    }

    void commandDeleteClass() {
        module.deleteClass();
    }

    void commandDeleteTask() {
        if (parserChecks.isEmptyDescription(Parser.taskDescription)) {
            return;
        }
        Task selectedTask = parserChecks.getTaskFromKeyword(Parser.taskDescription, module.taskList.getTaskList());
        if (selectedTask != null) {
            module.deleteTask(selectedTask);
        }
    }

    void commandDeleteGrade() {
        module.deleteGrade();
        NoCap.semester.updateCap();
        NoCap.semesterList.updateCap();
    }

    void commandEditDescription() {
        if (parserChecks.isEmptyDescription(Parser.taskDescription)) {
            return;
        }
        Parser.splitInput(Parser.taskDescription);
        if (parserChecks.isEmptyDescription(Parser.taskDescription)) {
            return;
        }
        Task selectedTask = parserChecks.getTaskFromIndex(Parser.taskType, module.taskList.getTaskList());
        if (selectedTask != null) {
            selectedTask.setDescription(Parser.taskDescription);
        }
    }

    void commandEditDeadline() {
        if (parserChecks.isEmptyDescription(Parser.taskDescription)) {
            return;
        }
        Parser.splitInput(Parser.taskDescription);
        if (parserChecks.isEmptyDescription(Parser.taskDescription)) {
            return;
        }
        Task selectedTask = parserChecks.getTaskFromIndex(Parser.taskType, module.taskList.getTaskList());
        if (selectedTask != null) {
            selectedTask.parseDeadline(Parser.taskDescription);
        }
    }

    void commandMarkDone() {
        if (parserChecks.isEmptyDescription(Parser.taskDescription)) {
            return;
        }
        Task selectedTask = parserChecks.getTaskFromIndex(Parser.taskDescription, module.taskList.getTaskList());
        if (selectedTask != null) {
            selectedTask.markDone();
        }
    }

    void commandMarkNotDone() {
        if (parserChecks.isEmptyDescription(Parser.taskDescription)) {
            return;
        }
        Task selectedTask = parserChecks.getTaskFromIndex(Parser.taskDescription, module.taskList.getTaskList());
        if (selectedTask != null) {
            selectedTask.markNotDone();
        }
    }
}