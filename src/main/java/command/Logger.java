package command;

import command.parser.Parser;

import java.util.logging.Level;
import java.util.logging.LogManager;

public final class Logger {
    public static String GLOBAL_LOGGER_NAME = "LOGGER";
    public static java.util.logging.Logger logger;
    private static LogManager logManager;

    public Logger() {
        logger = java.util.logging.Logger.getLogger(GLOBAL_LOGGER_NAME);
        logManager = LogManager.getLogManager();
        //logManager.getLogger(GLOBAL_LOGGER_NAME).setLevel(Level.ALL);
    }
}
