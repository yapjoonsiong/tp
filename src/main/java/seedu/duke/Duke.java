package seedu.duke;
import module.Module;
import module.Schedule;

import java.time.LocalTime;
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

        Scanner in = new Scanner(System.in);

        System.out.println("Hello " + in.nextLine());

        //Function to test module class, comment out if not in use
        moduleTest();
    }

    private static void moduleTest(){
        Module m = new Module("cs2113T");
        m.addCredits(4);
        m.addGrade("A+");
        LocalTime start = LocalTime.parse("13:00:00");
        LocalTime end = LocalTime.parse("15:00:00");
        m.addClass(new Schedule("WEDNESDAY", start, end));
        System.out.println(m);
    }
}
