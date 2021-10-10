package module;

import command.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        taskList.addTask("read /by 1800");
        assertEquals(taskList.toString(), m.getTaskList().toString());
    }

    @Test
    void getScheduleList() {
        Module m = new Module("cs2113T");
        String start = "0800";
        m.addClass(new Schedule("WED", start, "E1-01", "week 1,3,5,7,9"));
        assertEquals(new Schedule("WED", start, "E1-01", "week 1,3,5,7,9").toString(),m.get(0).toString());
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