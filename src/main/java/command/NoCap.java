package command;

import command.parser.Parser;
import command.storage.StorageDecoder;
import module.ModuleList;
import semester.SemesterList;
import semester.Semester;
import java.util.Scanner;
import command.Logger;

public class NoCap {
    //public static ModuleList moduleList = StorageDecoder.decodeJsonToModuleList();
    public static SemesterList semesterList = new SemesterList();
    public static ModuleList moduleList;
    private final Logger logger = new Logger();

    private void run() {
        Ui.printStartMessage();
        Parser parse = new Parser();
        Scanner in = new Scanner(System.in);
        while (!parse.isExit()) {
            moduleList = semesterList.getAccessedSemester().getModuleList();
            String input = in.nextLine();
            parse.chooseTask(input);
        }
        Ui.printEndMessage();
    }

    public static void main(String[] args) {
        new NoCap().run();
    }
}
