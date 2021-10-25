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
        Module m = new Module("cs2113T");
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
        Module m = new Module("cs2113T");
        ScheduleList expected = new ScheduleList();
        m.setScheduleList(expected);
        assertEquals(expected, m.getScheduleList());
    }
}
