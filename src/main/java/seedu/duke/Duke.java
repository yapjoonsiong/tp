package seedu.duke;

import module.ModuleList;
import module.Module;
import module.Schedule;

import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        //Scanner in = new Scanner(System.in);

        //System.out.println("Hello " + in.nextLine());

        Module m = new Module("cs2113T");
        String start = "0800";
        m.addClass(new Schedule("WED", start, "E1-01", "week 1,3,5,7,9"));
        System.out.println(m);
    }

}
