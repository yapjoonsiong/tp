package command.parser;

import command.Logger;
import command.NoCap;
import module.ModuleList;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;

public class CommandTest {

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

        parser.chooseTask("");



    }

}
