package command.storage;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import command.Ui;
import semester.SemesterList;

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
    private static final Logger logger = command.Logger.myLogger();

    /**
     * Decodes a json file located at a specified path into a SemesterList object.
     * Is implemented using the jackson databind library.
     *
     * @return A SemesterList containing information from previous runs.
     */
    public static SemesterList decodeJsonToSemesterList() {
        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.registerModule(new JavaTimeModule());
        SemesterList semesters = new SemesterList();
        try {
            if (!Files.exists(FILE_PATH)) {
                Ui.printNoSaveFileMessage();
                assert semesters.toString().equals(new SemesterList().toString());
                return semesters;
            }
            semesters = objectMapper.readValue(new File(FILE_PATH.toString()), SemesterList.class);
            Ui.loadFileSuccessful();
            logger.log(Level.INFO, "Load file successful");
        } catch (IOException e) {
            Ui.printCorruptFileMessage();
            // Create a new save file if save file is corrupted
            assert semesters.toString().equals(new SemesterList().toString());
            StorageEncoder.encodeAndSaveSemesterListToJson(semesters);
        }
        return semesters;
    }
}
