package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OverallTaskTest {
    @Test
    void overallTask_normalTask_success (){
        OverallTask task = new OverallTask("Assignment", "19/09/2021 1600", "CS2113", true);
        assertEquals("[CS2113] [X] Assignment by: 19 Sep 2021 04:00 PM", task.toString());
    }

}