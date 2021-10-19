package command.storage;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import command.Ui;
import module.ModuleList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StorageDecoder {

    //Constants
    private static final String ROOT = System.getProperty("user.dir");
    private static final Path FILE_PATH = Paths.get(ROOT, "data", "data.json");

    //Logger object
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static ModuleList decodeJsonToModuleList() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        ModuleList modules = new ModuleList();
        if (!Files.exists(FILE_PATH)) {
            Ui.printNoSaveFileMessage();
            assert modules.getModuleList().size() == 0;
            return modules;
        }
        try {
            modules = objectMapper.readValue(new File(FILE_PATH.toString()), ModuleList.class);
            Ui.loadFileSuccessful();
            logger.log(Level.INFO,"Load file successful");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Error reading save file, creating new template");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return modules;
    }
}
