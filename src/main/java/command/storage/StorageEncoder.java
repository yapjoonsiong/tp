package command.storage;


import com.fasterxml.jackson.databind.ObjectMapper;
import module.ModuleList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StorageEncoder {

    private static final String ROOT = System.getProperty("user.dir");
    private static final Path FILE_PATH = Paths.get(ROOT, "data", "data.json");
    private static final Path DIRECTORY_PATH = Paths.get(ROOT, "data");

    public static void encodeModuleListToJSON(ModuleList moduleList) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (!Files.exists(DIRECTORY_PATH)) {
                createDataDirectory();
            }
            if (!Files.exists(FILE_PATH)) {
                createFile();
            }
            objectMapper.writeValue(new File(FILE_PATH.toString()), moduleList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //TODO: Add catch blocks for each exception
        }
    }

    private static void createDataDirectory() /*throws DukeException*/ {
        File newDirectory = new File(DIRECTORY_PATH.toString());
        boolean createSuccess = newDirectory.mkdir();
        if (!createSuccess) {
//            throw new DukeException(ExceptionMessages.EXCEPTION_CREATE_DIRECTORY_FAIL);
        }
    }

    private static void createFile() /*throws DukeException*/ {
        try {
            File newFile = new File(FILE_PATH.toString());
            boolean createSuccess = newFile.createNewFile();
//            if (!createSuccess) {
//                throw new DukeException(ExceptionMessages.EXCEPTION_CREATE_FILE_FAIL);
//            }
        } catch (IOException e) {
//            Ui.showCreateSaveFileError();
            System.out.println("Error saving file");
        }
    }
}