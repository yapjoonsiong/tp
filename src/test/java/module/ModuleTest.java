package module;

import command.NoCap;
import exceptions.NoCapExceptions;
import schedule.Schedule;
import semester.SemesterList;
import task.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ModuleTest {
    @Test
    void testAddGrade() {
        Module m1 = null;
        try {
            m1 = new Module("cs2113T");
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        Module m2 = null;
        try {
            m2 = new Module("cs2040c");
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        Module m3 = null;
        try {
            m3 = new Module("ma1508E");
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        m1.addGrade("A+");
        m2.addGrade("A-");
        m3.addGrade("B");
        assertEquals("A+", m1.letterGrade);
        assertEquals("A-", m2.letterGrade);
        assertEquals("B", m3.letterGrade);
    }

    @Test
    void testAddCredit() {
        Module m1 = null;
        try {
            m1 = new Module("cs2113T");
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        Module m2 = null;
        try {
            m2 = new Module("cs2040c");
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        Module m3 = null;
        try {
            m3 = new Module("ma1508E");
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            m1.addCredits(2);
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            m2.addCredits(4);
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            m3.addCredits(6);
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        assertEquals(2, m1.credits);
        assertEquals(4, m2.credits);
        assertEquals(6, m3.credits);
    }

    @Test
    void getCredits() {
        Module m1 = null;
        try {
            m1 = new Module("cs2113T");
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        Module m2 = null;
        try {
            m2 = new Module("cs2040c");
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        Module m3 = null;
        try {
            m3 = new Module("ma1508E");
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            m1.addCredits(2);
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            m2.addCredits(4);
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            m3.addCredits(6);
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        assertEquals(2, m1.getCredits());
        assertEquals(4, m2.getCredits());
        assertEquals(6, m3.getCredits());
    }

    @Test
    void getLetterGrade() {
        Module m1 = null;
        try {
            m1 = new Module("cs2113T");
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        Module m2 = null;
        try {
            m2 = new Module("cs2040c");
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        Module m3 = null;
        try {
            m3 = new Module("ma1508E");
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        m1.addGrade("A+");
        m2.addGrade("A-");
        m3.addGrade("B");
        assertEquals("A+", m1.getLetterGrade());
        assertEquals("A-", m2.getLetterGrade());
        assertEquals("B", m3.getLetterGrade());
    }

    @Test
    void getModuleName() {
        Module m1 = null;
        try {
            m1 = new Module("cs2113T");
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        Module m2 = null;
        try {
            m2 = new Module("cs2040c");
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        Module m3 = null;
        try {
            m3 = new Module("ma1508E");
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        assertEquals("cs2113T", m1.getModuleName());
        assertEquals("cs2040c", m2.getModuleName());
        assertEquals("ma1508E", m3.getModuleName());
    }

    @Test
    void getTaskList() {
        Module m = null;
        try {
            m = new Module("cs2113T");
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        m.addTask("read /by 1800");
        TaskList taskList = new TaskList();
        taskList.addTask("cs2113T", "read /by 1800");

        assertEquals(taskList.toString(), m.getTaskList().toString());
    }

    @Test
    void getScheduleList() {
        NoCap.moduleList = new ModuleList();
        ModuleList moduleList = NoCap.moduleList;
        Module m = null;
        try {
            m = new Module("cs2113T");
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        moduleList.add(m);
        try {
            m.addClass("WED/0800/E1-01/tut");
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            assertEquals(new Schedule("WED", "0800", "E1-01", "tut").toString(), m.get(0).toString());
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
    }

    @Test
    void size() {
        NoCap.moduleList = new ModuleList();
        ModuleList moduleList = NoCap.moduleList;
        Module m = null;
        try {
            m = new Module("cs2113T");
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        moduleList.add(m);
        try {
            m.addClass("WED/0800/E1-01/tut");
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            m.addClass("TUE/0800/E1-01/lect");
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            m.addClass("MON/1000/E1-01/lect");
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        assertEquals(3, m.size());
    }

    @Test
    void testToString() {
        NoCap.moduleList = new ModuleList();
        ModuleList moduleList = NoCap.moduleList;
        Module m = null;
        try {
            m = new Module("cs2113T");
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        moduleList.add(m);
        m.addGrade("A+");
        try {
            m.addCredits(4);
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            m.addClass("WED/0800/E1-01/tut");
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            m.addClass("TUE/0800/E1-01/lect");
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            m.addClass("MON/1000/E1-01/lect");
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        m.addTask("read /by 11/11/2021 1900");
        m.addTask("do tutorial /by 12/11/2021 2000");
        m.addGradableTask("Assignment /by 12/12/2021 1600 /w 30");
        m.addGradableTask("Finals /by 15/12/2021 1600 /w 50");
        String exp = "Module name: cs2113T\n"
                + "CREDITS: 4\n" + "--------------------------- \n" + "SCHEDULE: \n" + "1.\n"
                + "Day: WED\n" + "Start Time: 0800\n" + "Location: E1-01\n" + "Comments: tut\n" + "2.\n"
                + "Day: TUE\n" + "Start Time: 0800\n" + "Location: E1-01\n" + "Comments: lect\n" + "3.\n"
                + "Day: MON\n" + "Start Time: 1000\n" + "Location: E1-01\n" + "Comments: lect\n"
                + "--------------------------- \n" + "GRADE: A+\n"
                + "TASKS: [[ ] read by: 11 Nov 2021 07:00 PM, [ ] do tutorial by: 12 Nov 2021 08:00 PM]\n"
                + "BREAKDOWN: \n1 " + "Assignment by: 12 Dec 2021 04:00 PM Weightage 30% [ ]\r\n"
                + "2 " + "Finals by: 15 Dec 2021 04:00 PM Weightage 50% [ ]\r\n";
        assertEquals(exp, m.toString());

    }
}