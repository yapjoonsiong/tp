package module;

import task.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ModuleTest {

    @Test
    void getLetterGrade() {
        Module m = new Module("cs2113T");
        m.addGrade("A+");
        assertEquals("A+", m.getLetterGrade());
    }

    @Test
    void getModuleName() {
        Module m = new Module("cs2113T");
        assertEquals("cs2113T", m.getModuleName());
    }

    @Test
    void getTaskList() {
        Module m = new Module("cs2113T");
        m.addTask("read /by 1800");
        TaskList taskList = new TaskList();
        taskList.addTask("cs2113T", "read /by 1800");
        assertEquals(taskList.toString(), m.getTaskList().toString());
    }

    @Test
    void getScheduleList() {
        Module m = new Module("cs2113T");
        String start = "0800";
        m.addClass("WED/0800/E1-01/tut");
        assertEquals(new Schedule("WED", "0800", "E1-01", "tut").toString(),m.get(0).toString());
    }

    @Test
    void getCredits() {
        Module m = new Module("cs2113T");
        m.addCredits(4);
        assertEquals(4, m.getCredits());
    }

    @Test
    void get() {
        Module m = new Module("cs2113T");
    }
}