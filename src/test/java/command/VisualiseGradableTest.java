package command;

import org.junit.jupiter.api.Test;
import task.GradableTaskList;

import static org.junit.jupiter.api.Assertions.*;

class VisualiseGradableTest {

    @Test
    void print() {
        GradableTaskList gradableTaskList = new GradableTaskList();
        gradableTaskList.addGradableTask("cs1010", "lecture quizzes /by 12/12/2021 1600 /w 10");
        gradableTaskList.addGradableTask("cs1010", "classPart /by 12/12/2021 1600 /w 15");
        gradableTaskList.addGradableTask("cs1010", "Finals /by 12/12/2021 1600 /w 50");
        gradableTaskList.addGradableTask("cs1010", "att /by 12/12/2021 1600 /w 15");
        gradableTaskList.addGradableTask("cs1010", "labs /by 12/12/2021 1600 /w 2");
        gradableTaskList.getGradableTask(1).setDone(true);
        gradableTaskList.getGradableTask(2).setDone(true);
        VisualiseGradable v = new VisualiseGradable(gradableTaskList);
        v.print();
    }
}