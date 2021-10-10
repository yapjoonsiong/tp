package command.storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import module.ModuleList;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StorageDecoder {

    private static final String ROOT = System.getProperty("user.dir");
    private static final Path FILE_PATH = Paths.get(ROOT, "data", "data.json");

    public static ModuleList decodeJsonToModuleList() {
        ObjectMapper objectMapper = new ObjectMapper();
        ModuleList modules = new ModuleList();
        if (!Files.exists(FILE_PATH)) {
            return modules;
        }
        try {
            modules = objectMapper.readValue(new File(FILE_PATH.toString()), ModuleList.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //TODO: Add catch blocks for each exception
        }
        return modules;
    }
}
