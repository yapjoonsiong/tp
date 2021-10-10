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

    public static void encodeAndSaveModuleListToJson(ModuleList moduleList) {
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

    //TODO: Implement custom exceptions
    private static void createDataDirectory() /*throws DukeException*/ {
        File newDirectory = new File(DIRECTORY_PATH.toString());
        boolean createSuccess = newDirectory.mkdir();
        if (!createSuccess) {
            //throw new DukeException(ExceptionMessages.EXCEPTION_CREATE_DIRECTORY_FAIL);
        }
    }

    //TODO: Implement custom exceptions
    private static void createFile() {
        try {
            File newFile = new File(FILE_PATH.toString());
            boolean createSuccess = newFile.createNewFile();
            if (!createSuccess) {
                System.out.println("Error creating save file");
            }
        } catch (IOException e) {
            System.out.println("Error saving file");
        }
    }
}