package command;

import org.junit.jupiter.api.Test;
import task.GradableTaskList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;

class VisualiseGradableTest {

    @Test
    void print() {
        GradableTaskList gradableTaskList = new GradableTaskList();
        gradableTaskList.addGradableTask("cs1010", "lecture quizzes /by 12/12/2021 1600 /w 10");
        gradableTaskList.addGradableTask("cs1010", "classPart /by 12/12/2021 1600 /w 15");
        gradableTaskList.addGradableTask("cs1010", "Finals /by 12/12/2021 1600 /w 55");
        gradableTaskList.addGradableTask("cs1010", "att /by 12/12/2021 1600 /w 15");
        gradableTaskList.addGradableTask("cs1010", "labs /by 12/12/2021 1600 /w 5");
        gradableTaskList.getGradableTask(1).setDone(true);
        gradableTaskList.getGradableTask(2).setDone(true);
        VisualiseGradable v = new VisualiseGradable(gradableTaskList);
        ByteArrayOutputStream read = new ByteArrayOutputStream();
        PrintStream save = new PrintStream(read);
        System.setOut(save);
        v.print();
        List<String> actualLines = List.of(read.toString().split("\r\n"));
        List<String> expectedLines = Collections.singletonList(
                "BREAKDOWN:" + System.lineSeparator()
                        + "<==10%===><=====15%=====><=========================55%=========================>"
                        + "<=====15%=====><5%=>" + System.lineSeparator()
                        + "##########@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&"
                        + "&&&&&&&&&+++++++++++++++#####" + System.lineSeparator()
                        + "|---1----||------2------||--------------------------3------------------"
                        + "--------||------4------||-5-|" + System.lineSeparator()
                        + "1: lecture quizzes" + System.lineSeparator()
                        + "2: classPart" + System.lineSeparator()
                        + "3: Finals" + System.lineSeparator()
                        + "4: att" + System.lineSeparator()
                        + "5: labs" + System.lineSeparator()
                        + System.lineSeparator()
                        + "1 lecture quizzes by: 12 Dec 2021 04:00 PM Weightage 10% [ ]\r\n"
                        + "2 classPart by: 12 Dec 2021 04:00 PM Weightage 15% [X]\r\n"
                        + "3 Finals by: 12 Dec 2021 04:00 PM Weightage 55% [X]\r\n"
                        + "4 att by: 12 Dec 2021 04:00 PM Weightage 15% [ ]\r\n"
                        + "5 labs by: 12 Dec 2021 04:00 PM Weightage 5% [ ]");
        assertLinesMatch(actualLines, actualLines);
    }
}