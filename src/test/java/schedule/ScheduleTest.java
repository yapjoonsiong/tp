package schedule;

import command.NoCap;
import exceptions.NoCapExceptions;
import module.Module;
import module.ModuleList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScheduleTest {

    @Test
    void getLocation_success() {
        Schedule s = null;
        try {
            s = new Schedule("WED", "0800", "E1-01", "tutorial");
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        assertEquals("E1-01", s.getLocation());
    }

    @Test
    void getComment_success() {
        Schedule s = null;
        try {
            s = new Schedule("WED", "0800", "E1-01", "tutorial");
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        assert s != null;
        assertEquals("tutorial", s.getComment());
    }

    @Test
    void getDay_success() {
        Schedule s = null;
        try {
            s = new Schedule("WED", "0800", "E1-01", "tutorial");
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        assert s != null;
        assertEquals("WED", s.getDay());
    }

    @Test
    void getStartTime_success() {
        Schedule s = null;
        try {
            s = new Schedule("WED", "0800", "E1-01", "tutorial");
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        assert s != null;
        assertEquals("0800", s.getStartTime());
    }
}
