package org.example;

import java.io.IOException;
import java.util.logging.*;

public class LoggerConfig {
    private static final Logger logger = Logger.getLogger(LoggerConfig.class.getName());

    static {
        try {
            FileHandler fileHandler = new FileHandler("logFile.log");
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error configuring logger", e);
        }
    }

    public static Logger getLogger() {
        return logger;
    }
}
