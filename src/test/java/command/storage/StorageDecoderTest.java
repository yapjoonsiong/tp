package command.storage;

import module.Module;
import module.ModuleList;
import module.Schedule;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class StorageDecoderTest {
    private static final String ROOT = System.getProperty("user.dir");
    private static final Path FILE_PATH = Paths.get(ROOT, "data", "data.json");

    @Test
    public void decodeModuleList_normalModuleList_success() {
        ModuleList modules = new ModuleList();
        modules.add(new Module("CS2102"));
        modules.add(new Module("CS2112"));
        modules.add(new Module("CS2132"));
        modules.get(0).addTask("sleep /by 21/08/2022 1600");
        modules.get(0).addCredits(4);
        modules.get(2).addClass(new Schedule("Monday", "12pm", "E3", "Bad"));
        modules.get(0).addClass(new Schedule("Monday", "12pm", "D3", "Bad"));
        modules.get(0).addClass(new Schedule("Tuesday", "12pm", "D3", "Bad"));
        StorageEncoder.encodeAndSaveModuleListToJson(modules);
        ModuleList loadedModules = StorageDecoder.decodeJsonToModuleList();
        assertEquals(modules.toString(), loadedModules.toString());
    }

    @Test
    public void decodeModuleList_emptyModuleList_success() {
        ModuleList modules = new ModuleList();
        StorageEncoder.encodeAndSaveModuleListToJson(modules);
        ModuleList loadedModules = StorageDecoder.decodeJsonToModuleList();
        assertEquals(loadedModules.toString(), modules.toString());
    }

    @Test
    public void decodeModuleList_noFile_success() {
        try {
            if (Files.exists(FILE_PATH)) {
                Files.delete(FILE_PATH);
            }
        } catch (IOException e) {
            fail(e);
        }
        ModuleList loadedModules = StorageDecoder.decodeJsonToModuleList();
        assertEquals(loadedModules.toString(), new ModuleList().toString());

    }

}