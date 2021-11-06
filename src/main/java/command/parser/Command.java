package command.parser;

import command.NoCap;
import command.Ui;
import command.storage.StorageEncoder;
import exceptions.NoCapExceptions;
import module.Module;
import task.GradableTask;
import task.Task;
import task.TaskList;

import java.util.Locale;

/**
 * Class containing parser methods that calls commands to other classes.
 */
public class Command {

    private final ParserChecks parserChecks = new ParserChecks();

    public Command() {
    }

    /**
     * Takes in an input string and parse it for semester index.
     * Checks input is indeed an integer and not empty.
     *
     * @param taskDescription string index of Semester to switch to.
     */
    void commandSwitchSemester(String taskDescription) {
        if (parserChecks.isEmptyDescription(taskDescription)
                | parserChecks.isNotInteger(taskDescription)) {
            return;
        }
        try {
            NoCap.semesterList.setAccessedSemesterIndex(Integer.parseInt(taskDescription) - 1);
            Ui.switchSemesterMessage(NoCap.semesterList
                    .get(Integer.parseInt(taskDescription) - 1).getSemester());
            StorageEncoder.encodeAndSaveSemesterListToJson(NoCap.semesterList);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    void commandPrintCap() {
        try {
            NoCap.semester.printCap();
            StorageEncoder.encodeAndSaveSemesterListToJson(NoCap.semesterList);
        } catch (NoCapExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    void commandPrintAllCap() {
        try {
            NoCap.semesterList.printAllCap();
            StorageEncoder.encodeAndSaveSemesterListToJson(NoCap.semesterList);
        } catch (NoCapExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    void commandPrintTimeTable() {
        NoCap.moduleList.printTimeTable();
        StorageEncoder.encodeAndSaveSemesterListToJson(NoCap.semesterList);
    }

    void commandShowInfo(Module module) {
        module.showInformation();
    }

    /**
     * Takes in an input string and parse it for module name
     * Checks input is not empty, module name does not already exists and does not include spaces.
     *
     * @param taskDescription module name to add.
     */
    void commandAddModule(String taskDescription) {
        if (parserChecks.isEmptyDescription(taskDescription)
                || parserChecks.isDuplicateModule(taskDescription)
                || parserChecks.includeSpace(taskDescription)) {
            return;
        }
        try {
            NoCap.moduleList.add(taskDescription.toUpperCase(Locale.ROOT));
            Ui.addModuleNameMessage(NoCap.moduleList);
            StorageEncoder.encodeAndSaveSemesterListToJson(NoCap.semesterList);
        } catch (NoCapExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Takes in an input string and parse it for class information.
     * Checks input is not empty.
     *
     * @param module module in which the class is to be added to.
     * @param taskDescription class to add.
     */
    void commandAddClass(Module module, String taskDescription) {
        if (parserChecks.isEmptyDescription(taskDescription)) {
            return;
        }
        try {
            module.addClass(taskDescription);
            Ui.addModuleClassMessage(module);
        } catch (NoCapExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Takes in an input string and parse it for task information
     * Checks input is not empty and contains /by.
     *
     * @param module module in which the task is to be added to.
     * @param taskDescription module name to add.
     */
    void commandAddTask(Module module, String taskDescription) {
        if (parserChecks.isEmptyDescription(taskDescription)
                || !parserChecks.hasDateDescription(taskDescription)) {
            return;
        }
        module.addTask(taskDescription);
    }

    /**
     * Takes in an input string and parse it for task information
     * Checks input is not empty and contains /by and /w.
     *
     * @param module module in which the gradable task is to be added to.
     * @param taskDescription module name to add.
     */
    void commandAddGradable(Module module, String taskDescription) {
        if (parserChecks.isEmptyDescription(taskDescription)
                || !parserChecks.hasDateDescription(taskDescription)
                || !parserChecks.hasWeightageDescription(taskDescription)) {
            return;
        }
        module.addGradableTask(taskDescription);
        Ui.visualiseGradableTask(module.getGradableTaskList());
    }

    /**
     * Takes in an input string and parse it for grade information
     * Checks input is not empty and contains a valid grade digit.
     *
     * @param module module in which the grade is to be added to.
     * @param taskDescription grade to add
     */
    void commandAddGrade(Module module, String taskDescription) {
        if (parserChecks.isEmptyDescription(taskDescription) || !parserChecks.validGrade(taskDescription)) {
            return;
        }
        module.addGrade(taskDescription);
        Ui.addModuleGradeMessage(module);
        try {
            NoCap.semester.updateCap();
            NoCap.semesterList.updateCap();
        } catch (NoCapExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Takes in an input string and parse it for credit information
     * Checks input is not empty and is an integer.
     *
     * @param module module in which the credit is to be assigned to.
     * @param taskDescription credit value to add
     */
    void commandAddCredit(Module module, String taskDescription) {
        if (parserChecks.isEmptyDescription(taskDescription)
                || parserChecks.isNotInteger(taskDescription)) {
            return;
        }
        try {
            module.addCredits(Integer.parseInt(taskDescription));
            Ui.addModuleCreditsMessage(module);
            NoCap.semester.updateCap();
            NoCap.semesterList.updateCap();
        } catch (NoCapExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Takes in a module index and deletes it from module list.
     * Checks input is not empty and contains an index.
     *
     * @param taskDescription string index of module to delete.
     */
    void commandDeleteModule(String taskDescription) {
        if (parserChecks.isEmptyDescription(taskDescription)
                || parserChecks.isNotInteger(taskDescription)) {
            return;
        }
        try {
            NoCap.moduleList.delete(taskDescription);
            Ui.printRemainingModules();
            NoCap.moduleList.printModules();
            StorageEncoder.encodeAndSaveSemesterListToJson(NoCap.semesterList);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid number value");
        }
    }

    /**
     * Deletes a class at the specified index from the module given.
     * Checks input is not empty and contains an index.
     *
     * @param module module in which the class is to be deleted from.
     * @param taskDescription string index of class to delete.
     */
    void commandDeleteClass(Module module, String taskDescription) {
        if (parserChecks.isEmptyDescription(taskDescription)
                || parserChecks.isNotInteger(taskDescription)) {
            return;
        }
        try {
            module.deleteClass(taskDescription);
            Ui.printRemainingSchedules(module.getScheduleList());
            StorageEncoder.encodeAndSaveSemesterListToJson(NoCap.semesterList);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid number value");
        }
    }

    /**
     * Deletes a task from a module by searching for keyword matches.
     *
     * @param module module in which the task is to be deleted from.
     * @param taskDescription keyword to search for.
     */
    void commandDeleteTask(Module module, String taskDescription) {
        if (parserChecks.isEmptyDescription(taskDescription)) {
            return;
        }
        Task selectedTask = parserChecks.getTaskFromKeyword(taskDescription, module.taskList.getTaskList());
        if (selectedTask != null) {
            Ui.printTaskDeleted(selectedTask);
            module.deleteTask(selectedTask);
        }
    }

    /**
     * Deletes an assigned grade from a module.
     *
     * @param module module in which the grade is to be deleted from.
     */
    void commandDeleteGrade(Module module) {
        module.deleteGrade();
        Ui.deleteGradeMesage(module);
        try {
            NoCap.semester.updateCap();
            NoCap.semesterList.updateCap();
        } catch (NoCapExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Edits the description of a task.
     * Perform additional checks to verify that a task of that index can be found,
     * and the provided description is not the same as an existing task.
     *
     * @param module module of the task to be edited.
     * @param taskType index of the task in the module.
     * @param taskDescription new description to replace the old description.
     */
    void commandEditDescription(Module module, String taskType, String taskDescription) {
        TaskList list = module.getTaskList();
        Task selectedTask = parserChecks.getTaskFromIndex(taskType, module.taskList.getTaskList());
        if (selectedTask != null && !list.hasDuplicateDescription(taskDescription)) {
            selectedTask.setDescription(taskDescription);
            Ui.printUpdateTaskDescription(selectedTask);
        } else if (list.hasDuplicateDescription(taskDescription)) {
            Ui.duplicateTaskError();
        }
    }

    /**
     * Edits the deadline of a task.
     * Perform additional checks to verify that a task of that index can be found.
     *
     * @param module module of the task to be edited.
     * @param taskType index of the task in the module.
     * @param taskDescription new deadline to replace the old deadline.
     */
    void commandEditDeadline(Module module, String taskType, String taskDescription) {
        Task selectedTask = parserChecks.getTaskFromIndex(taskType, module.taskList.getTaskList());
        if (selectedTask != null) {
            selectedTask.parseDeadline(taskDescription);
        }
    }

    /**
     * Edit the status of a task to be completed.
     * Checks taskDescription is not empty
     *
     * @param module module of the task to be edited.
     * @param taskDescription index of task in module.
     */
    void commandMarkDone(Module module, String taskDescription) {
        if (parserChecks.isEmptyDescription(taskDescription)) {
            return;
        }
        Task selectedTask = parserChecks.getTaskFromIndex(taskDescription, module.taskList.getTaskList());
        if (selectedTask != null) {
            selectedTask.markDone();
        }
    }

    /**
     * Edit the status of a task to be not completed.
     * Checks taskDescription is not empty
     *
     * @param module module of the task to be edited.
     * @param taskDescription index of task in module.
     */
    void commandMarkNotDone(Module module, String taskDescription) {
        if (parserChecks.isEmptyDescription(taskDescription)) {
            return;
        }
        Task selectedTask = parserChecks.getTaskFromIndex(taskDescription, module.taskList.getTaskList());
        if (selectedTask != null) {
            selectedTask.markNotDone();
        }
    }

    /**
     * Edit the status of a gradable task to be completed.
     * Checks taskDescription is not empty
     *
     * @param module module of the task to be edited.
     * @param taskDescription index of task in module.
     */
    void commandMarkGradableDone(Module module, String taskDescription) {
        if (parserChecks.isEmptyDescription(taskDescription)) {
            return;
        }
        GradableTask selectedTask = parserChecks.getGradableTaskFromIndex(taskDescription,
                module.gradableTaskList.getGradableTaskList());
        if (selectedTask != null) {
            selectedTask.markDone();
        }
    }

    /**
     * Edit the status of a gradable task to be not completed.
     * Checks taskDescription is not empty
     *
     * @param module module of the task to be edited.
     * @param taskDescription index of task in module.
     */
    void commandMarkGradableNotDone(Module module, String taskDescription) {
        if (parserChecks.isEmptyDescription(taskDescription)) {
            return;
        }
        GradableTask selectedTask = parserChecks.getGradableTaskFromIndex(taskDescription,
                module.gradableTaskList.getGradableTaskList());
        if (selectedTask != null) {
            selectedTask.markNotDone();
        }
    }

}