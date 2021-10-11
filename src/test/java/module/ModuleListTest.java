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

}
