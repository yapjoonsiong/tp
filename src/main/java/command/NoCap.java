package command;

import command.parser.Parser;
import command.storage.StorageDecoder;
import module.ModuleList;

import java.util.Scanner;

public class NoCap {
    public static ModuleList moduleList = StorageDecoder.decodeJsonToModuleList();


    private void run() {
        Ui.printStartMessage();
        Parser parse = new Parser();
        Scanner in = new Scanner(System.in);
        while (!parse.isExit()) {
            String input = in.nextLine();
            parse.chooseTask(input);
        }
        Ui.printEndMessage();
    }

    public static void main(String[] args) {
        new NoCap().run();
    }
}
