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
        String expected = "1\n"
                + "Module name: cs2113T\n"
                + "CREDITS: 0\n"
                + "--------------------------- \n"
                + "SCHEDULE: \n"
                + "--------------------------- \n"
                + "GRADE: null\n"
                + "TASKS: []\n"
                + "BREAKDOWN: \n"
                + "\n"
                + "2\n"
                + "Module name: cs2040c\n"
                + "CREDITS: 0\n"
                + "--------------------------- \n"
                + "SCHEDULE: \n"
                + "--------------------------- \n"
                + "GRADE: null\n"
                + "TASKS: []\n"
                + "BREAKDOWN: \n"
                + "\n"
                + "3\n"
                + "Module name: MA1508E\n"
                + "CREDITS: 0\n"
                + "--------------------------- \n"
                + "SCHEDULE: \n"
                + "--------------------------- \n"
                + "GRADE: null\n"
                + "TASKS: []\n"
                + "BREAKDOWN: \n"
                + "\n";
        assertEquals(expected, moduleList.toString());
    }
    */
}
