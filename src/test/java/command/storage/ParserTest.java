package command.storage;

import command.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void splitInput_withDescription_success() {
        Parser testParser = new Parser();
        testParser.chooseTask("Add taskDescription");
        assertEquals("Add", testParser.getTaskType());
        assertEquals("taskDescription", testParser.getTaskDescription());
    }

    @Test
    public void splitInput_withoutDescription_success() {
        Parser testParser = new Parser();
        testParser.chooseTask("Help");
        assertEquals("Help", testParser.getTaskType());
        assertEquals("", testParser.getTaskDescription());
    }

}
