package command;

import java.util.Scanner;

public class NoCap {
    private final Storage storage;

    public NoCap() {
        storage = new Storage();
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
