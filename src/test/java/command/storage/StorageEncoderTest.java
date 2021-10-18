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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


class StorageEncoderTest {

    private static final String ROOT = System.getProperty("user.dir");
    private static final Path FILE_PATH = Paths.get(ROOT, "data", "data.json");
    private static final Path DIRECTORY_PATH = Paths.get(ROOT, "data");

    @Test
    public void encodeModuleList_normalModuleList_success() {
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
        assertTrue(Files.exists(FILE_PATH));
        final String expectedOutput = {\"moduleList\":[{\"letterGrade\":null,\"points\":0.0,\"moduleName"
                            + "\":\"CS2102\",\"taskList\":{\"taskList\":[{\"description\""
                            + ":\"sleep\",\"date\":\"21/08/2022 1600\",\"deadline\":[2022,8,"
                            + "21,16,0],\"done\":false}],\"taskCount\":1},\""
                            + "scheduleList\":{\"scheduleList\":[{\"startTime\":\"12pm\",\""
                            + "location\":\"D3\",\"day\":\"Monday\",\"comment\":\"Bad\"},"
                            + "{\"startTime\":\"12pm\",\"location\":\"D3\",\"day\":\"Tuesday"
                            + "\",\"comment\":\"Bad\"}]},\"credits\":4,\"gradableTaskList\":{"
                            + "\"taskList\":[],\"taskCount\":0,\"gradableTaskList\":[]}},"
                            + "{\"letterGrade\":null,\"points\":0.0,\"moduleName\":\"CS2112\",\"taskList\":{\""
                            + "taskList\":[],\"taskCount\":0},\"scheduleList\":{\"scheduleList"
                            + "\":[]},\"credits\":0,\"gradableTaskList\":{\"taskList\":[],\"task"
                            + "Count\":0,\"gradableTaskList\":[]}},{\"letterGrade\":null,\"points\":0.0,\"module"
                            + "Name\":\"CS2132\",\"taskList\":{\"taskList\":[],\"taskCount\":0},"
                            + "\"scheduleList\":{\"scheduleList\":[{\"startTime\":\"12pm\",\""
                            + "location\":\"E3\",\"day\":\"Monday\",\"comment\":\"Bad\"}]},\""
                            + "credits\":0,\"gradableTaskList\":{\"taskList\":[],\"taskCount\":0"
                            + ",\"gradableTaskList\":[]}}]}";
        try {
            String fileContent = Files.readString(FILE_PATH);
            assertEquals(expectedOutput, fileContent);
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void encodeModuleList_emptyModuleList_success() {
        ModuleList modules = new ModuleList();
        StorageEncoder.encodeAndSaveModuleListToJson(modules);
        assertTrue(Files.exists(FILE_PATH));
        try {
            String fileContent = Files.readString(FILE_PATH);
            assertEquals("{\"moduleList\":[]}", fileContent);
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void encodeModuleList_noFile_success() {
        try {
            if (Files.exists(DIRECTORY_PATH)) {
                Files.delete(FILE_PATH);
                Files.delete(DIRECTORY_PATH);
            }
        } catch (IOException e) {
            fail(e.getMessage());
        }
        ModuleList modules = new ModuleList();
        StorageEncoder.encodeAndSaveModuleListToJson(modules);
        assertTrue(Files.exists(FILE_PATH));
    }
}