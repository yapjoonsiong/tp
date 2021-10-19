package command;

import command.parser.Parser;
import command.storage.StorageDecoder;
import module.ModuleList;
import semester.SemesterList;

import java.util.Scanner;
import command.Logger;

public class NoCap {

    public static SemesterList semesterList = StorageDecoder.decodeJsonToSemesterList();
    public static ModuleList moduleList;
    private final Logger logger = new Logger();

    private void run() {
        Ui.printStartMessage();
        Parser parse = new Parser();
        Scanner in = new Scanner(System.in);
        while (!parse.isExit()) {
            moduleList = semesterList.extractAccessedSemester().getModuleList();
            String input = in.nextLine();
            parse.chooseTask(input);
        }
        Ui.printEndMessage();
    }

    public static void main(String[] args) {
        new NoCap().run();
    }
}
