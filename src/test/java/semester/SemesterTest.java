package semester;

import command.NoCap;
import module.Module;
import module.ModuleList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SemesterTest {

    @Test
    void getCredits_success() {
        NoCap.semesterList = new SemesterList();
        NoCap.semesterList.get(0).getModuleList().add(new Module("CS2040C"));
        NoCap.semesterList.get(0).getModuleList().get(0).addCredits(4);
        NoCap.semesterList.get(0).updateCap();
        NoCap.semesterList.get(0).getModuleList().add(new Module("CS2113T"));
        NoCap.semesterList.get(0).getModuleList().get(1).addCredits(4);
        NoCap.semesterList.get(0).updateCap();
        assertEquals(8, NoCap.semesterList.get(0).getCredits());
    }

    @Test
    void getPoints_success() {
        NoCap.semesterList = new SemesterList();
        NoCap.semesterList.get(0).getModuleList().add(new Module("CS2040C"));
        NoCap.semesterList.get(0).getModuleList().get(0).addCredits(4);
        NoCap.semesterList.get(0).getModuleList().get(0).addGrade("A");
        NoCap.semesterList.get(0).updateCap();
        NoCap.semesterList.get(0).getModuleList().add(new Module("CS2113T"));
        NoCap.semesterList.get(0).getModuleList().get(1).addCredits(4);
        NoCap.semesterList.get(0).getModuleList().get(1).addGrade("A");
        NoCap.semesterList.get(0).updateCap();
        assertEquals(40, NoCap.semesterList.get(0).getPoints());
    }

    @Test
    void getCap_success() {
        NoCap.semesterList = new SemesterList();
        NoCap.semesterList.get(0).getModuleList().add(new Module("CS2040C"));
        NoCap.semesterList.get(0).getModuleList().get(0).addCredits(4);
        NoCap.semesterList.get(0).getModuleList().get(0).addGrade("A");
        NoCap.semesterList.get(0).updateCap();
        NoCap.semesterList.get(0).getModuleList().add(new Module("CS2113T"));
        NoCap.semesterList.get(0).getModuleList().get(1).addCredits(4);
        NoCap.semesterList.get(0).getModuleList().get(1).addGrade("B");
        NoCap.semesterList.get(0).updateCap();
        assertEquals(4.25, NoCap.semesterList.get(0).getCap());
    }

    @Test
    void getModuleList_success() {
        ModuleList expected = new ModuleList();
        Semester s = new Semester();
        s.moduleList = expected;
        assertEquals(expected, s.getModuleList());
    }

    @Test
    void getSemester_success() {
        Semester s = new Semester("Y1S1");
        assertEquals("Y1S1", s.getSemester());
    }
}
