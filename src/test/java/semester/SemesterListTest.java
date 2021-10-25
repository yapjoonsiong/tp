package semester;

import command.NoCap;
import module.Module;
import module.ModuleList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SemesterListTest {

    @Test
    void getCredits_success() {
        NoCap.semesterList = new SemesterList();
        NoCap.semesterList.get(0).getModuleList().add(new Module("CS2040C"));
        NoCap.semesterList.get(0).getModuleList().get(0).addCredits(4);
        NoCap.semesterList.get(0).updateCap();
        NoCap.semesterList.get(1).getModuleList().add(new Module("CS2113T"));
        NoCap.semesterList.get(1).getModuleList().get(0).addCredits(4);
        NoCap.semesterList.get(1).updateCap();
        NoCap.semesterList.updateCap();
        assertEquals(8, NoCap.semesterList.getCredits());
    }

    @Test
    void getPoints_success() {
        NoCap.semesterList = new SemesterList();
        NoCap.semesterList.get(0).getModuleList().add(new Module("CS2040C"));
        NoCap.semesterList.get(0).getModuleList().get(0).addCredits(4);
        NoCap.semesterList.get(0).getModuleList().get(0).addGrade("A");
        NoCap.semesterList.get(0).updateCap();
        NoCap.semesterList.get(1).getModuleList().add(new Module("CS2113T"));
        NoCap.semesterList.get(1).getModuleList().get(0).addCredits(4);
        NoCap.semesterList.get(1).getModuleList().get(0).addGrade("A");
        NoCap.semesterList.get(1).updateCap();
        NoCap.semesterList.updateCap();
        assertEquals(40, NoCap.semesterList.getPoints());
    }

    @Test
    void getCap_success() {
        NoCap.semesterList = new SemesterList();
        NoCap.semesterList.get(0).getModuleList().add(new Module("CS2040C"));
        NoCap.semesterList.get(0).getModuleList().get(0).addCredits(4);
        NoCap.semesterList.get(0).getModuleList().get(0).addGrade("A");
        NoCap.semesterList.get(0).updateCap();
        NoCap.semesterList.get(1).getModuleList().add(new Module("CS2113T"));
        NoCap.semesterList.get(1).getModuleList().get(0).addCredits(4);
        NoCap.semesterList.get(1).getModuleList().get(0).addGrade("B");
        NoCap.semesterList.get(1).updateCap();
        NoCap.semesterList.updateCap();
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
