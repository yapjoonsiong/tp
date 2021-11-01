package semester;

import command.NoCap;
import exceptions.NoCapExceptions;
import module.Module;
import module.ModuleList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SemesterListTest {

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
            NoCap.semesterList.get(1).getModuleList().add(new Module("CS2113T"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            NoCap.semesterList.get(1).getModuleList().get(0).addCredits(4);
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            NoCap.semesterList.get(1).updateCap();
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            NoCap.semesterList.updateCap();
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        assertEquals(8, NoCap.semesterList.getCredits());
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
            NoCap.semesterList.get(1).getModuleList().add(new Module("CS2113T"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            NoCap.semesterList.get(1).getModuleList().get(0).addCredits(4);
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        NoCap.semesterList.get(1).getModuleList().get(0).addGrade("A");
        try {
            NoCap.semesterList.get(1).updateCap();
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            NoCap.semesterList.updateCap();
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        assertEquals(40, NoCap.semesterList.getPoints());
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
            NoCap.semesterList.get(1).getModuleList().add(new Module("CS2113T"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            NoCap.semesterList.get(1).getModuleList().get(0).addCredits(4);
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        NoCap.semesterList.get(1).getModuleList().get(0).addGrade("B");
        try {
            NoCap.semesterList.get(1).updateCap();
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            NoCap.semesterList.updateCap();
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        assertEquals(4.25, NoCap.semesterList.getCap());
    }

    @Test
    void getSemesterList_success() {
        SemesterList expected = new SemesterList();
        NoCap.semesterList = expected;
        assertEquals(expected.getSemesterList(), NoCap.semesterList.getSemesterList());
    }

    @Test
    void getAccessedSemesterIndex_success() {
        NoCap.semesterList = new SemesterList();
        NoCap.semesterList.setAccessedSemesterIndex(1);
        assertEquals(1, NoCap.semesterList.getAccessedSemesterIndex());
    }

    @Test
    void extractAccessedSemester_success() {
        SemesterList expected = new SemesterList();
        NoCap.semesterList = expected;
        NoCap.semesterList.setAccessedSemesterIndex(1);
        assertEquals(expected.get(1), NoCap.semesterList.extractAccessedSemester());
    }
}
