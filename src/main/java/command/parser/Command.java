package command.parser;

import command.NoCap;
import command.Ui;
import command.storage.StorageEncoder;
import exceptions.NoCapExceptions;
import task.Task;

import java.util.Locale;

public class Command {

    private final Parser parser;
    private final ParserSearch parserSearch = new ParserSearch();

    public Command(Parser parser) {
        this.parser = parser;
    }

    void commandSwitchSemester() {
        if (parser.isEmptyDescription(Parser.taskDescription) || parser.isNotInteger(Parser.taskDescription)) {
            return;
        }
        try {
            NoCap.semesterList.setAccessedSemesterIndex(Integer.parseInt(Parser.taskDescription) - 1);
            Ui.switchSemesterMessage(NoCap.semesterList.get(Integer.parseInt(Parser.taskDescription) - 1).getSemester());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    void commandDeleteModule(Parser parser) {
        if (parser.isEmptyDescription(Parser.taskDescription) || parser.isNotInteger(Parser.taskDescription)) {
            return;
        }
        try {
            NoCap.moduleList.delete(Parser.taskDescription);
            StorageEncoder.encodeAndSaveSemesterListToJson(NoCap.semesterList);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid number value");
        }
    }

    void commandAddModule(Parser parser) {
        if (parser.isEmptyDescription(Parser.taskDescription) | parser.isDuplicateModule(Parser.taskDescription)) {
            return;
        }
        NoCap.moduleList.add(Parser.taskDescription.toUpperCase(Locale.ROOT));
        Ui.addModuleNameMessage(NoCap.moduleList);
        StorageEncoder.encodeAndSaveSemesterListToJson(NoCap.semesterList);
    }

    void commandPrintAllCap() {
        NoCap.semesterList.printAllCap();
    }

    void commandPrintCap() {
        NoCap.semester.printCap();
    }

    void commandPrintTimeTable() {
        NoCap.moduleList.printTimeTable();
    }

    void commandShowInfo() {
        Parser.module.showInformation();
    }

    void commandAddClass(Parser parser) {
        if (parser.isEmptyDescription(Parser.taskDescription)) {
            return;
        }
        try {
            Parser.module.addClass(Parser.taskDescription);
            Ui.addModuleClassMessage(Parser.module);
        } catch (NoCapExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    void commandAddTask(Parser parser) {
        if (parser.isEmptyDescription(Parser.taskDescription) | !parser.hasDateDescription(Parser.taskDescription)) {
            return;
        }
        Parser.module.addTask(Parser.taskDescription);
    }

    void commandAddGradable(Parser parser) {
        if (parser.isEmptyDescription(Parser.taskDescription) | !parser.hasDateDescription(Parser.taskDescription)
                | !parser.hasWeightageDescription(Parser.taskDescription)) {
            return;
        }
        Parser.module.addGradableTask(Parser.taskDescription);
    }

    void commandMarkDone(Parser parser) {
        Task selectedTask;
        if (parser.isEmptyDescription(Parser.taskDescription)) {
            return;
        }
        selectedTask = parserSearch.getTaskFromIndex(Parser.taskDescription, Parser.module.taskList.getTaskList());
        if (selectedTask != null) {
            selectedTask.markDone();
        }
    }

    void commandMarkNotDone(Parser parser) {
        Task selectedTask;
        if (parser.isEmptyDescription(Parser.taskDescription)) {
            return;
        }
        selectedTask = parserSearch.getTaskFromIndex(Parser.taskDescription, Parser.module.taskList.getTaskList());
        if (selectedTask != null) {
            selectedTask.markNotDone();
        }
    }

    void commandAddGrade(Parser parser) {
        if (parser.isEmptyDescription(Parser.taskDescription)) {
            return;
        }
        Parser.module.addGrade(Parser.taskDescription);
        Ui.addModuleGradeMessage(Parser.module);
        NoCap.semester.updateCap();
        NoCap.semesterList.updateCap();
    }

    void commandAddCredit(Parser parser) {
        if (parser.isEmptyDescription(Parser.taskDescription)) {
            return;
        }
        Parser.module.addCredits(Integer.parseInt(Parser.taskDescription));
        Ui.addModuleCreditsMessage(Parser.module);
        NoCap.semester.updateCap();
        NoCap.semesterList.updateCap();
    }

    void commandDeleteClass() {
        Parser.module.deleteClass();
    }

    void commandDeleteTask(Parser parser) {
        Task selectedTask;
        if (parser.isEmptyDescription(Parser.taskDescription)) {
            return;
        }
        selectedTask = parserSearch.getTaskFromKeyword(Parser.taskDescription, Parser.module.taskList.getTaskList());
        if (selectedTask != null) {
            Parser.module.deleteTask(selectedTask);
        }
    }

    void commandEditDescription(Parser parser) {
        Task selectedTask;
        if (parser.isEmptyDescription(Parser.taskDescription)) {
            return;
        }
        Parser.splitInput(Parser.taskDescription);
        if (parser.isEmptyDescription(Parser.taskDescription)) {
            return;
        }
        selectedTask = parserSearch.getTaskFromIndex(Parser.taskType, Parser.module.taskList.getTaskList());
        if (selectedTask != null) {
            selectedTask.setDescription(Parser.taskDescription);
        }
    }

    void commandEditDeadline(Parser parser) {
        Task selectedTask;
        if (parser.isEmptyDescription(Parser.taskDescription)) {
            return;
        }
        Parser.splitInput(Parser.taskDescription);
        if (parser.isEmptyDescription(Parser.taskDescription)) {
            return;
        }
        selectedTask = parserSearch.getTaskFromIndex(Parser.taskType, Parser.module.taskList.getTaskList());
        if (selectedTask != null) {
            selectedTask.parseDeadline(Parser.taskDescription);
        }
    }

    void commandDeleteGrade() {
        Parser.module.deleteGrade();
        NoCap.semester.updateCap();
        NoCap.semesterList.updateCap();
    }
}