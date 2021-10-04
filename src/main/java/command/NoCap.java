package command;

import java.util.Scanner;

public class NoCap {
    private final Storage storage;
    private final TaskList tasks;

    public NoCap() {
        storage = new Storage();
        tasks = new TaskList(storage.taskList);
    }
    private void run() {
        Ui.printStartMessage();
        Parser parse = new Parser();
        Scanner in = new Scanner(System.in);
        while (!parse.isExit()) {
            String input = in.nextLine();
            parse.chooseTask(tasks, storage, input);
        }
        Ui.printEndMessage();
    }
    public static void main(String[] args) {
        new NoCap().run();
    }
}
