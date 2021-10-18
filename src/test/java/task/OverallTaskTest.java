package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OverallTaskTest {
    @Test
    void overallTask_normalTask_success() {
        OverallTask task = new OverallTask("Assignment", "19/09/2021 1600", "CS2113", true);
        assertEquals("[CS2113] [X] Assignment by: 19 Sep 2021 04:00 PM", task.toString());
    }

    @Test
    void dateComparator_equal_success() {
        OverallTask task1 = new OverallTask("Assignment", "19/09/2021 1600", "CS2113", true);
        OverallTask task2 = new OverallTask("Assignment", "19/09/2021 1600", "CS2113", true);
        int result = OverallTask.dateComparator.compare(task1, task2);
        assertEquals(result, 0);
    }

    @Test
    void dateComparator_leftHeavy_success() {
        OverallTask task1 = new OverallTask("Assignment", "19/09/2021 1600", "CS2113", true);
        OverallTask task2 = new OverallTask("Assignment", "19/09/2021 1800", "CS2113", true);
        int result = OverallTask.dateComparator.compare(task1, task2);
        assertTrue(result < 0);
    }

    @Test
    void dateComparator_rightHeavy_success() {
        OverallTask task1 = new OverallTask("Assignment", "19/09/2021 1600", "CS2113", true);
        OverallTask task2 = new OverallTask("Assignment", "19/09/2021 1300", "CS2113", true);
        int result = OverallTask.dateComparator.compare(task1, task2);
        assertTrue(result > 0);
    }

    @Test
    void statusComparator_equal_success() {
        OverallTask task1 = new OverallTask("Assignment", "19/09/2021 1600", "CS2113", true);
        OverallTask task2 = new OverallTask("Assignment", "19/09/2021 1600", "CS2113", true);
        int result = OverallTask.statusComparator.compare(task1, task2);
        assertEquals(result, 0);
    }

    @Test
    void statusComparator_leftHeavy_success() {
        OverallTask task1 = new OverallTask("Assignment", "19/09/2021 1600", "CS2113", true);
        OverallTask task2 = new OverallTask("Assignment", "19/09/2021 1600", "CS2113", false);
        int result = OverallTask.statusComparator.compare(task1, task2);
        assertTrue (result < 0);
    }

    @Test
    void statusComparator_rightHeavy_success() {
        OverallTask task1 = new OverallTask("Assignment", "19/09/2021 1600", "CS2113", false);
        OverallTask task2 = new OverallTask("Assignment", "19/09/2021 1600", "CS2113", true);
        int result = OverallTask.statusComparator.compare(task1, task2);
        assertTrue (result > 0);
    }


}