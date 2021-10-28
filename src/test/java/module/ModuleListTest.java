package module;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModuleListTest {
    @Test
    void get_success() {
        ModuleList moduleList = new ModuleList();
        Module m = new Module("cs2113T");
        moduleList.add(m);
        assertEquals(m, moduleList.get(0));
    }

    @Test
    void find_success() {
        ModuleList moduleList = new ModuleList();
        Module m = new Module("cs2113T");
        moduleList.add(m);
        assertEquals(m, moduleList.find("cs2113T"));
    }

    @Test
    void size_success() {
        ModuleList moduleList = new ModuleList();
        Module m = new Module("cs2113T");
        moduleList.add(m);
        assertEquals(1, moduleList.size());
    }

    @Test
    void testDelete() {
        ModuleList moduleList1 = new ModuleList();
        ModuleList expectedList = new ModuleList();
        Module m1 = new Module("cs2113T");
        Module m2 = new Module("Cs2040c");
        Module m3 = new Module("ma1508E");
        moduleList1.add(m1);
        moduleList1.add(m2);
        moduleList1.add(m3);
        moduleList1.delete("1");
        expectedList.add(m2);
        expectedList.add(m3);
        assertEquals(expectedList.toString(), moduleList1.toString());
    }

    /*
    @Test
    void toString_success() {
        ModuleList moduleList = new ModuleList();
        Module m1 = new Module("cs2113T");
        moduleList.add(m1);
        Module m2 = new Module("cs2040c");
        moduleList.add(m2);
        Module m3 = new Module("MA1508E");
        moduleList.add(m3);
        String expected = "1" + System.lineSeparator()
                + "Module name: cs2113T" + System.lineSeparator()
                + "CREDITS: 0" + System.lineSeparator()
                + "--------------------------- " + System.lineSeparator()
                + "SCHEDULE: " + System.lineSeparator()
                + "--------------------------- " + System.lineSeparator()
                + "GRADE: null" + System.lineSeparator()
                + "TASKS: []" + System.lineSeparator()
                + "BREAKDOWN: " + System.lineSeparator()
                + "" + System.lineSeparator()
                + "2" + System.lineSeparator()
                + "Module name: cs2040c" + System.lineSeparator()
                + "CREDITS: 0" + System.lineSeparator()
                + "--------------------------- " + System.lineSeparator()
                + "SCHEDULE: " + System.lineSeparator()
                + "--------------------------- " + System.lineSeparator()
                + "GRADE: null" + System.lineSeparator()
                + "TASKS: []" + System.lineSeparator()
                + "BREAKDOWN: " + System.lineSeparator()
                + "" + System.lineSeparator()
                + "3" + System.lineSeparator()
                + "Module name: MA1508E" + System.lineSeparator()
                + "CREDITS: 0" + System.lineSeparator()
                + "--------------------------- " + System.lineSeparator()
                + "SCHEDULE: " + System.lineSeparator()
                + "--------------------------- " + System.lineSeparator()
                + "GRADE: null" + System.lineSeparator()
                + "TASKS: []" + System.lineSeparator()
                + "BREAKDOWN: " + System.lineSeparator()
                + System.lineSeparator();
        assertEquals(expected, moduleList.toString());
    }
    */
}
