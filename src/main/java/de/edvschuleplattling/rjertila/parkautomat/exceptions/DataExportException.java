package de.edvschuleplattling.rjertila.parkautomat.exceptions;

import java.io.IOException;


public class DataExportException extends IOException {
    private final String file;

    public DataExportException(String file) {
        super("Fehler beim schreiben in die datei " + file);
        this.file = file;
    }

    public DataExportException(String file, Exception e) {
        super("Fehler beim schreiben in die datei " + file, e);
        this.file = file;
    }

    public String getFile() {
        return file;
    }
}
