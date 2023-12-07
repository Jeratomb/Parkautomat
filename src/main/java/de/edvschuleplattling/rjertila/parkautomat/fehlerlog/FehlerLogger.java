package de.edvschuleplattling.rjertila.parkautomat.fehlerlog;

import de.edvschuleplattling.rjertila.parkautomat.exceptions.GeldException;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FehlerLogger {
    private static final String LOG_FILE = "./src/main/java/de/edvschuleplattling/rjertila/parkautomat/fehlerlog/fehler.log";
    private static final Logger logger = Logger.getLogger(FehlerLogger.class.getName());

    public void log(GeldException e){
        try {
            FileHandler fileHandler = new FileHandler(LOG_FILE, true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            logger.severe("Timestamp: " + timestamp + ", Title: " + e.getTitle() + ", Message: " + e.getMessage());

            fileHandler.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}