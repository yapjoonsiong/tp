package command;

import command.storage.StorageEncoder;
import module.ModuleList;

import java.util.Scanner;

public class NoCap {
    private final StorageEncoder storage;
    public static ModuleList moduleList = new ModuleList();
    public NoCap() {
        storage = new StorageEncoder();
    }

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
