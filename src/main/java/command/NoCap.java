package command;

import command.parser.Parser;
import command.storage.StorageDecoder;
import module.ModuleList;
import semester.SemesterList;
import semester.Semester;

import java.util.Scanner;
import command.Logger;

public class NoCap {

    public static SemesterList semesterList = StorageDecoder.decodeJsonToSemesterList();
    public static ModuleList moduleList;
    public static Semester semester;

    private void run() {
        Ui.printStartMessage(semesterList.get(semesterList.getAccessedSemesterIndex()).getSemester());
        Parser parse = new Parser();
        Scanner in = new Scanner(System.in);
        while (!parse.isExit()) {
            System.out.println();
            moduleList = semesterList.extractAccessedSemester().getModuleList();
            semester = semesterList.extractAccessedSemester();
            String input = in.nextLine();
            parse.chooseTask(input);
        }
        Ui.printEndMessage();
    }

    public static void main(String[] args) {
        new NoCap().run();
    }
}
