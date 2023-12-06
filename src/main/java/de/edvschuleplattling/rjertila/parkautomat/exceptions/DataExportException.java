package de.edvschuleplattling.rjertila.parkautomat.exceptions;

import java.io.IOException;

/**
 * Diese Klasse stellt eine benutzerdefinierte Ausnahme dar, wenn ein Fehler beim Exportieren von Daten auftritt.
 * Sie erweitert IOException und fügt das Attribut "file" hinzu.
 * @author rjertila
 */
public class DataExportException extends IOException {
    private final String file;

    /**
     * Konstruktor für DataExportException.
     * @param file Der Name der Datei, bei der der Fehler aufgetreten ist.
     */
    public DataExportException(String file) {
        super("Fehler beim schreiben in die datei " + file);
        this.file = file;
    }

    /**
     * Konstruktor für DataExportException mit einer spezifischen Ausnahme.
     * @param file Der Name der Datei, bei der der Fehler aufgetreten ist.
     * @param e Die spezifische Ausnahme, die aufgetreten ist.
     */
    public DataExportException(String file, Exception e) {
        super("Fehler beim schreiben in die datei " + file, e);
        this.file = file;
    }

    /**
     * Getter für den Dateinamen.
     * @return Der Name der Datei, bei der der Fehler aufgetreten ist.
     */
    public String getFile() {
        return file;
    }
}