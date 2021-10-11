package task;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;


public class TaskListTest {

    @Test
    void get_success() {
        TaskList a = new TaskList();
        a.addTask("cs1010", "Read Book A /by 12/12/2021 1600");
        a.addTask("cs1010", "Read Book B /by 13/12/2021 1600");
        assertEquals("[ ] Read Book A by: 12 Dec 2021 04:00 PM", a.get(0).toString());
        assertEquals("[ ] Read Book B by: 13 Dec 2021 04:00 PM", a.get(1).toString());
    }

    @Test
    void addTask_success() {
        TaskList tasks = new TaskList();
        tasks.addTask("cs1010", "Read Book /by 12/12/2021 1600");
        assertEquals("[ ] Read Book by: 12 Dec 2021 04:00 PM", tasks.get(0).toString());
    }

    @Test
    void getTaskCount_success() {
        TaskList tasks = new TaskList();
        tasks.addTask("cs1010", "Read Book /by 12/12/2021 1600");
        tasks.addTask("cs1010", "Return Book /by 13/12/2021 1600");
        assertEquals(2, tasks.getTaskCount());
    }

    @Test
    void getTaskList_success() {
        TaskList a = new TaskList();
        TaskList b = new TaskList();
        a.addTask("cs1010", "Read Book A /by 12/12/2021 1600");
        a.addTask("cs1010", "Read Book B /by 13/12/2021 1600");
        b.addTask("cs1010", "Buy Book A /by 14/12/2021 1600");
        b.addTask("cs1010", "Buy Book B /by 15/12/2021 1600");
        ArrayList<Task> list = new ArrayList<>();
        list.addAll(a.getTaskList());
        list.addAll(b.getTaskList());
        assertEquals(4, list.size());
        assertEquals("[ ] Read Book A by: 12 Dec 2021 04:00 PM", list.get(0).toString());
        assertEquals("[ ] Read Book B by: 13 Dec 2021 04:00 PM", list.get(1).toString());
        assertEquals("[ ] Buy Book A by: 14 Dec 2021 04:00 PM", list.get(2).toString());
        assertEquals("[ ] Buy Book B by: 15 Dec 2021 04:00 PM", list.get(3).toString());
    }

    @Test
    void setTaskList_success() {
        TaskList a = new TaskList();
        TaskList b = new TaskList();
        a.addTask("cs1010", "Read Book A /by 12/12/2021 1600");
        a.addTask("cs1010", "Read Book B /by 13/12/2021 1600");
        b.addTask("cs1010", "Buy Book A /by 14/12/2021 1600");
        b.addTask("cs1010", "Buy Book B /by 15/12/2021 1600");
        a.setTaskList(b.getTaskList());
        assertEquals(2, a.getTaskCount());
        assertEquals("[ ] Buy Book A by: 14 Dec 2021 04:00 PM", a.get(0).toString());
        assertEquals("[ ] Buy Book B by: 15 Dec 2021 04:00 PM", a.get(1).toString());

    }

    @Test
    void setTaskCount_success() {
        TaskList a = new TaskList();
        a.setTaskCount(10);
        assertEquals(10, a.getTaskCount());
    }

    @Test
    void delete_success() {
        TaskList a = new TaskList();
        a.addTask("cs1010", "Read Book A /by 12/12/2021 1600");
        a.addTask("cs1010", "Read Book B /by 13/12/2021 1600");
        assertEquals("[ ] Read Book A by: 12 Dec 2021 04:00 PM", a.get(0).toString());
        assertEquals("[ ] Read Book B by: 13 Dec 2021 04:00 PM", a.get(1).toString());
        a.delete(a.get(0));
        assertEquals("[ ] Read Book B by: 13 Dec 2021 04:00 PM", a.get(0).toString());
    }

    @Test
    void printTaskList_success() {
        TaskList a = new TaskList();
        a.addTask("cs1010", "Read Book C /by 14/12/2021 1600");
        a.addTask("cs1010", "Read Book B /by 13/12/2021 1600");
        a.addTask("cs1010", "Read Book A /by 12/12/2021 1600");
        a.addTask("cs1010", "Read Book D /by 12/12/2021 1100");
        // Create a stream to hold the output
        ByteArrayOutputStream read = new ByteArrayOutputStream();
        PrintStream save = new PrintStream(read);
        // Tell Java to use your special stream
        System.setOut(save);
        // Print some output: goes to your special stream
        a.printTaskList("Read");
        List<String> actualLines = List.of(read.toString().split("/n"));
        List<String> expectedLines = Collections.singletonList("The tasks in Read are: " + System.lineSeparator()
                + "1.[ ] Read Book D by: 12 Dec 2021 11:00 AM" + System.lineSeparator()
                + "2.[ ] Read Book A by: 12 Dec 2021 04:00 PM" + System.lineSeparator()
                + "3.[ ] Read Book B by: 13 Dec 2021 04:00 PM" + System.lineSeparator()
                + "4.[ ] Read Book C by: 14 Dec 2021 04:00 PM" + System.lineSeparator());
        assertLinesMatch(expectedLines, actualLines);
    }

    private List<String> getWeeklyStrings(String module) {
        return Collections.singletonList("The tasks due within 7 days in " + module + " are: " + System.lineSeparator()
                + "1.[ ] Read Book C by: 1 Jan 2021 04:00 PM" + System.lineSeparator()
                + "2.[ ] Read Book B by: 3 Jan 2021 04:00 PM" + System.lineSeparator()
                + "3.[ ] Read Book A by: 6 Jan 2021 04:00 PM" + System.lineSeparator());
    }

    @Test
    void showAllWeekly_success() {
        TaskList a = new TaskList();
        a.addTask("cs1010", "Read Book C /by 01/01/2021 1600");
        a.addTask("cs1010", "Read Book B /by 03/01/2021 1600");
        a.addTask("cs1010", "Read Book A /by 06/01/2021 1600");
        a.addTask("cs1010", "Read Book D /by 13/01/2021 1100");
        // Create a stream to hold the output
        String module = "moduleName";
        ByteArrayOutputStream read = new ByteArrayOutputStream();
        PrintStream save = new PrintStream(read);
        // Tell Java to use your special stream
        System.setOut(save);
        // Print some output: goes to your special stream
        a.showAllWeekly(module);
        List<String> actualLines = List.of(read.toString().split("/n"));
        List<String> expectedLines = getWeeklyStrings(module);
        assertLinesMatch(expectedLines, actualLines);
    }

    private List<String> getMonthlyStrings(String module) {
        return Collections.singletonList("The tasks due within a month in " + module + " are: " + System.lineSeparator()
                + "1.[ ] Read Book A by: 1 Jan 2021 04:00 PM" + System.lineSeparator()
                + "2.[ ] Read Book C by: 3 Jan 2021 04:00 PM" + System.lineSeparator()
                + "3.[ ] Read Book D by: 6 Jan 2021 04:00 PM" + System.lineSeparator()
                + "4.[ ] Read Book E by: 13 Jan 2021 11:00 AM" + System.lineSeparator()
                + "5.[ ] Read Book F by: 30 Jan 2021 11:00 AM" + System.lineSeparator());
    }

    @Test
    void showAllMonthly_success() {
        TaskList a = new TaskList();
        a.addTask("cs1010", "Read Book A /by 01/01/2021 1600");
        a.addTask("cs1010", "Read Book B /by 01/04/2021 1600");
        a.addTask("cs1010", "Read Book C /by 03/01/2021 1600");
        a.addTask("cs1010", "Read Book D /by 06/01/2021 1600");
        a.addTask("cs1010", "Read Book E /by 13/01/2021 1100");
        a.addTask("cs1010", "Read Book F /by 30/01/2021 1100");
        a.addTask("cs1010", "Read Book G /by 13/02/2021 1100");
        // Create a stream to hold the output
        ByteArrayOutputStream read = new ByteArrayOutputStream();
        PrintStream save = new PrintStream(read);
        // Tell Java to use your special stream
        System.setOut(save);
        // Print some output: goes to your special stream
        String module = "moduleName";
        a.showAllMonthly(module);
        List<String> actualLines = List.of(read.toString().split("/n"));
        List<String> expectedLines = getMonthlyStrings(module);
        assertLinesMatch(expectedLines, actualLines);
    }

    private List<String> getYearlyStrings(String module) {
        return Collections.singletonList("The tasks due within a year in " + module + " are: " + System.lineSeparator()
                + "1.[ ] Read Book C by: 1 Jan 2021 04:00 PM" + System.lineSeparator()
                + "2.[ ] Read Book B by: 3 Jan 2021 04:00 PM" + System.lineSeparator()
                + "3.[ ] Read Book A by: 6 Jan 2021 04:00 PM" + System.lineSeparator()
                + "4.[ ] Read Book D by: 13 Jan 2021 11:00 AM" + System.lineSeparator()
                + "5.[ ] Read Book E by: 1 Jan 2022 11:00 AM" + System.lineSeparator());
    }

    @Test
    void showAllYearly_success() {
        TaskList a = new TaskList();
        a.addTask("cs1010", "Read Book C /by 01/01/2021 1600");
        a.addTask("cs1010", "Read Book B /by 03/01/2021 1600");
        a.addTask("cs1010", "Read Book A /by 06/01/2021 1600");
        a.addTask("cs1010", "Read Book D /by 13/01/2021 1100");
        a.addTask("cs1010", "Read Book E /by 01/01/2022 1100");
        a.addTask("cs1010", "Read Book F /by 30/12/2021 1100");
        // Create a stream to hold the output
        ByteArrayOutputStream read = new ByteArrayOutputStream();
        PrintStream save = new PrintStream(read);
        // Tell Java to use your special stream
        System.setOut(save);
        // Print some output: goes to your special stream
        String module = "moduleName";
        a.showAllYearly(module);
        List<String> actualLines = List.of(read.toString().split("/n"));
        List<String> expectedLines = getYearlyStrings(module);
        assertLinesMatch(expectedLines, actualLines);
    }
}

