package command;

import java.util.logging.Level;
import java.util.logging.LogManager;

public final class Logger {
    public static String GLOBAL_LOGGER_NAME = "LOGGER";
    private static LogManager logManager;

    public Logger() {
        logManager = LogManager.getLogManager();
        logManager.getLogger(GLOBAL_LOGGER_NAME).setLevel(Level.SEVERE);
    }

}
