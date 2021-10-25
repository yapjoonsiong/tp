package command.parser;

import command.Logger;
import command.NoCap;
import command.storage.StorageDecoder;
import module.Module;
import module.ModuleList;
import org.junit.jupiter.api.Test;
import semester.Semester;
import semester.SemesterList;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandTest {

      /*
    @Test
    public void variousCommands_missingDescription_ErrorMessage() {
        NoCap.moduleList = new ModuleList();
        Parser parser = new Parser();

        parser.chooseTask("add");
        parser.chooseTask("delete");
        parser.chooseTask("list");

        parser.chooseTask("/m CS1010 addclass");
        parser.chooseTask("/m CS1010 addtask");
        parser.chooseTask("/m CS1010 addgradable");
        parser.chooseTask("/m CS1010 addgrade");
        parser.chooseTask("/m CS1010 addcredit");

    }*/

    @Test
    public void commandEdit_success() {
        NoCap.moduleList = new ModuleList();
        Command command = new Command();

        command.commandAddModule("cs1010");
        Module module = NoCap.moduleList.get(0);

        command.commandAddTask(module, "test1 /by 10 10 10");

        command.commandEditDescription(module, "0", "invalid index");
        assertEquals(module.getTaskList().toString(), "[[ ] test1 by: 10 Oct 2010 12:00 AM]");

        command.commandEditDescription(module, "1", "test2");
        assertEquals(module.getTaskList().toString(), "[[ ] test2 by: 10 Oct 2010 12:00 AM]");

        command.commandEditDeadline(module, "1", "invalid date");
        assertEquals(module.getTaskList().toString(), "[[ ] test2 by: 10 Oct 2010 12:00 AM]");

        command.commandEditDeadline(module, "1", "11 11 11");
        assertEquals(module.getTaskList().toString(), "[[ ] test2 by: 11 Nov 2011 12:00 AM]");
    }

    @Test
    public void variousCommands_Success() {
        NoCap.moduleList = new ModuleList();
        Parser parser = new Parser();

        parser.chooseTask("add cs1010");
        parser.chooseTask("add cs1010");
        /*
        parser.chooseTask("/m CS1010 addtask test1 /by 10 10 10");
        parser.chooseTask("/m CS1010 addtask test2 /by 11 11 21");
        parser.chooseTask("/m CS1010 addtask test2 /by 11 11 22");
        parser.chooseTask("/m CS1010 addgradable test3 /by 10 10 10 /w 10");

        //parser.chooseTask("delete 2"); // missing feedback

        parser.chooseTask("list task");
        parser.chooseTask("list task w");
        parser.chooseTask("list task m");
        parser.chooseTask("list task gradable");

        parser.chooseTask("/m cs1010 list task");
        parser.chooseTask("/m cs1010 list tdasdasdasdasd");


        parser.chooseTask("/m cs1010 addclass MON/1100/e-learning/tutorial");
        parser.chooseTask("/m cs1010 addclass MON/1100//");
        parser.chooseTask("timetable"); //add in ug only show first instance
        parser.chooseTask("/m cs1010 deleteclass");
        parser.chooseTask("timetable");*/

        //parser.chooseTask("/m CS1010 addtask test1 /by 10 10 10");
        //parser.chooseTask("/m CS1010 addtask dssd /by 10-10-10");

        parser.chooseTask("/m cs1010 addgradable finalss dasda sda asdasd /by 10/10/10 /w 70");
        parser.chooseTask("/m cs1010 addgradable assignment /by 10/10/10 /w 70");
        parser.chooseTask("/m cs1010 gradabledone 2");

        parser.chooseTask("list task");


    }

}
