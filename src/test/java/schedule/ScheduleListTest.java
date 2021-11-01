package schedule;

import command.NoCap;
import exceptions.NoCapExceptions;
import module.Module;
import module.ModuleList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScheduleListTest {

    @Test
    void size_success() {
        ScheduleList s = new ScheduleList();
        try {
            s.addClass(new Schedule("WED", "0800", "E1-01", "tutorial"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            s.addClass(new Schedule("MON", "0800", "e-learning", "tutorial"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        assertEquals(2, s.size());
    }

    @Test
    void getSchedule_success() {
        NoCap.moduleList = new ModuleList();
        ModuleList moduleList = NoCap.moduleList;
        Module m = null;
        try {
            m = new Module("cs2113T");
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        moduleList.add(m);

        Schedule expected = null;
        try {
            expected = new Schedule("WED", "0800", "E1-01", "tutorial");
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        m.addClass(expected);
        try {
            m.addClass(new Schedule("MON", "0800", "e-learning", "tutorial"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }

        assertEquals(expected, m.getScheduleList().getSchedule(0));
    }

    @Test
    void getScheduleList_success() {
        Module m = null;
        try {
            m = new Module("cs2113T");
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        ScheduleList expected = new ScheduleList();
        m.setScheduleList(expected);
        assertEquals(expected, m.getScheduleList());
    }

    @Test
    void testDelete() throws NoCapExceptions {
        ScheduleList scheduleList1 = new ScheduleList();
        Schedule s1 = new Schedule("WED", "0800", "E1-01", "tutorial");
        Schedule s2 = new Schedule("MON", "1000", "ZOOM", "lecture");
        Schedule s3 = new Schedule("TUE", "1500", "ARC", "lab");
        scheduleList1.addClass(s1);
        scheduleList1.addClass(s2);
        scheduleList1.addClass(s3);
        scheduleList1.deleteClass("1");
        ScheduleList scheduleList2 = new ScheduleList();
        scheduleList2.addClass(s2);
        scheduleList2.addClass(s3);
        assertEquals(scheduleList1.toString(), scheduleList2.toString());
    }
}
