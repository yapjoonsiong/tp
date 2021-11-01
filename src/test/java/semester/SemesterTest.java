package semester;

import command.NoCap;
import exceptions.NoCapExceptions;
import module.Module;
import module.ModuleList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SemesterTest {

    @Test
    void getCredits_success() {
        NoCap.semesterList = new SemesterList();
        try {
            NoCap.semesterList.get(0).getModuleList().add(new Module("CS2040C"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            NoCap.semesterList.get(0).getModuleList().get(0).addCredits(4);
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            NoCap.semesterList.get(0).updateCap();
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            NoCap.semesterList.get(0).getModuleList().add(new Module("CS2113T"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            NoCap.semesterList.get(0).getModuleList().get(1).addCredits(4);
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            NoCap.semesterList.get(0).updateCap();
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        assertEquals(8, NoCap.semesterList.get(0).getCredits());
    }

    @Test
    void getPoints_success() {
        NoCap.semesterList = new SemesterList();
        try {
            NoCap.semesterList.get(0).getModuleList().add(new Module("CS2040C"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            NoCap.semesterList.get(0).getModuleList().get(0).addCredits(4);
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        NoCap.semesterList.get(0).getModuleList().get(0).addGrade("A");
        try {
            NoCap.semesterList.get(0).updateCap();
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            NoCap.semesterList.get(0).getModuleList().add(new Module("CS2113T"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            NoCap.semesterList.get(0).getModuleList().get(1).addCredits(4);
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        NoCap.semesterList.get(0).getModuleList().get(1).addGrade("A");
        try {
            NoCap.semesterList.get(0).updateCap();
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        assertEquals(40, NoCap.semesterList.get(0).getPoints());
    }

    @Test
    void getCap_success() {
        NoCap.semesterList = new SemesterList();
        try {
            NoCap.semesterList.get(0).getModuleList().add(new Module("CS2040C"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            NoCap.semesterList.get(0).getModuleList().get(0).addCredits(4);
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        NoCap.semesterList.get(0).getModuleList().get(0).addGrade("A");
        try {
            NoCap.semesterList.get(0).updateCap();
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            NoCap.semesterList.get(0).getModuleList().add(new Module("CS2113T"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            NoCap.semesterList.get(0).getModuleList().get(1).addCredits(4);
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        NoCap.semesterList.get(0).getModuleList().get(1).addGrade("B");
        try {
            NoCap.semesterList.get(0).updateCap();
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
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
