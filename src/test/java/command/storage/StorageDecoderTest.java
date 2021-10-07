package command.storage;

import module.Module;
import module.ModuleList;
import org.junit.jupiter.api.Test;

class StorageDecoderTest {

    @Test
    public void decodeModuleList_normalModuleList_success() {
        ModuleList modules = new ModuleList();
        modules.add(new Module("CS2102"));
        modules.add(new Module("CS2112"));
        modules.add(new Module("CS2132"));
        StorageEncoder.encodeModuleListToJson(modules);
        ModuleList loadedModules = StorageDecoder.decodeJsonToModuleList();
        loadedModules.printModules();
    }

}