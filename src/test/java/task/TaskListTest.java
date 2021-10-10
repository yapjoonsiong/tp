package task;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;


public class TaskListTest {
    @Test
    void get_success() {
        TaskList a = new TaskList();
        a.addTask("Read Book A /by Monday");
        a.addTask("Read Book B /by Friday");
        assertEquals("[ ] Read Book A by: Monday", a.get(0).toString());
        assertEquals("[ ] Read Book B by: Friday", a.get(1).toString());
    }

    @Test
    void addTask_success() {
        TaskList tasks = new TaskList();
        tasks.addTask("Read Book /by Monday");
        assertEquals("[ ] Read Book by: Monday", tasks.get(0).toString());
    }

    @Test
    void getTaskCount_success() {
        TaskList tasks = new TaskList();
        tasks.addTask("Read Book /by Monday");
        tasks.addTask("Return Book /by Tuesday");
        assertEquals(2, tasks.getTaskCount());
    }
    @Test
    void getTaskList_success() {
        TaskList a = new TaskList();
        TaskList b = new TaskList();
        ArrayList<Task> list = new ArrayList<>();
        a.addTask("Read Book A /by Monday");
        a.addTask("Read Book B /by Friday");
        b.addTask("Buy Book A /by Tuesday");
        b.addTask("Buy Book B /by Wednesday");
        list.addAll(a.getTaskList());
        list.addAll(b.getTaskList());
        assertEquals(4, list.size());
        assertEquals("[ ] Read Book A by: Monday", list.get(0).toString());
        assertEquals("[ ] Read Book B by: Friday", list.get(1).toString());
        assertEquals("[ ] Buy Book A by: Tuesday", list.get(2).toString());
        assertEquals("[ ] Buy Book B by: Wednesday", list.get(3).toString());
    }

    @Test
    void setTaskList_success() {
        TaskList a = new TaskList();
        TaskList b = new TaskList();
        a.addTask("Read Book A /by Monday");
        a.addTask("Read Book B /by Friday");
        b.addTask("Buy Book A /by Tuesday");
        b.addTask("Buy Book B /by Wednesday");
        a.setTaskList(b.getTaskList());
        assertEquals(2, a.getTaskCount());
        assertEquals("[ ] Buy Book A by: Tuesday", a.get(0).toString());
        assertEquals("[ ] Buy Book B by: Wednesday", a.get(1).toString());

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
        a.addTask("Read Book A /by Monday");
        a.addTask("Read Book B /by Friday");
        assertEquals("[ ] Read Book A by: Monday", a.get(0).toString());
        assertEquals("[ ] Read Book B by: Friday", a.get(1).toString());
        a.delete(a.get(0));
        assertEquals("[ ] Read Book B by: Friday", a.get(0).toString());
    }
    @Test
    void printTaskList_success() {
        TaskList a = new TaskList();
        a.addTask("Read Book A /by Monday");
        a.addTask("Read Book B /by Friday");
        // Create a stream to hold the output
        ByteArrayOutputStream read = new ByteArrayOutputStream();
        PrintStream save = new PrintStream(read);
        // Tell Java to use your special stream
        System.setOut(save);
        // Print some output: goes to your special stream
        a.printTaskList("Read");
        List<String> actualLines = List.of(read.toString().split("/n"));
        List<String> expectedLines = Collections.singletonList("The tasks in Read are: " + System.lineSeparator() +
                "1.[ ] Read Book A by: Monday" + System.lineSeparator() +
                "2.[ ] Read Book B by: Friday" + System.lineSeparator());
        assertLinesMatch(expectedLines, actualLines);
    }
}
