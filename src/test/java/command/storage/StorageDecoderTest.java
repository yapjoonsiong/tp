package command.storage;

import module.Module;
import module.ModuleList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StorageDecoderTest {

    @Test
    public void decodeModuleList_normalModuleListJSON_success() {
        ModuleList modules = new ModuleList();
        modules.add(new Module("CS2102"));
        modules.add(new Module("CS2112"));
        modules.add(new Module("CS2132"));
        StorageEncoder.encodeModuleListToJSON(modules);
        ModuleList loadedModules = StorageDecoder.decodeJSONToModuleList();
        loadedModules.printModules();
    }

}