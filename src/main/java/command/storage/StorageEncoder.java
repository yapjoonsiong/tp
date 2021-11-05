package command.storage;

import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import command.Ui;
import exceptions.ExceptionMessages;
import exceptions.NoCapExceptions;
import semester.SemesterList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StorageEncoder {

    //Constants
    private static final String ROOT = System.getProperty("user.dir");
    private static final Path FILE_PATH = Paths.get(ROOT, "data", "data.json");
    private static final Path DIRECTORY_PATH = Paths.get(ROOT, "data");

    //Logger object
    private static final Logger logger = command.Logger.myLogger();

    /**
     * Takes in a SemesterList object and converts it into json format.
     * Then, stores it in a specified file path. If the specified directory does
     * not exist, this method will attempt to create the directory first before saving the
     * SemesterList object. Is implemented using the jackson databind library.
     *
     * @param semesterList The SemesterList object to be converted into json format.
     */
    public static void encodeAndSaveSemesterListToJson(SemesterList semesterList) {
        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.registerModule(new JavaTimeModule());
        try {
            if (!Files.exists(DIRECTORY_PATH)) {
                createDataDirectory();
            }
            if (!Files.exists(FILE_PATH)) {
                createFile();
            }
            objectMapper.writeValue(new File(FILE_PATH.toString()), semesterList);
            logger.log(Level.INFO, "File saved");
        } catch (NoCapExceptions e) {
            System.out.println(e.getMessage());
        } catch (DatabindException e) {
            Ui.parseSaveFileError();
        } catch (IOException e) {
            Ui.writeSaveFileError();
        }
    }

    private static void createDataDirectory() throws NoCapExceptions {
        File newDirectory = new File(DIRECTORY_PATH.toString());
        boolean createSuccess = newDirectory.mkdir();
        logger.log(Level.INFO, "New data directory being created");
        assert Files.exists(DIRECTORY_PATH);
        if (!createSuccess) {
            throw new NoCapExceptions(ExceptionMessages.EXCEPTION_CREATE_DIRECTORY_FAIL);
        }
    }

    private static void createFile() throws NoCapExceptions {
        try {
            File newFile = new File(FILE_PATH.toString());
            logger.log(Level.INFO, "New data file being created");
            boolean createSuccess = newFile.createNewFile();
            if (!createSuccess) {
                throw new NoCapExceptions(ExceptionMessages.EXCEPTION_CREATE_FILE_FAIL);
            }
            assert Files.exists(FILE_PATH);
        } catch (IOException e) {
            Ui.saveFileError();
        }
    }
}