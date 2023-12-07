package de.edvschuleplattling.rjertila.parkautomat.exceptions;

import java.io.IOException;

/**
 * Eine benutzerdefinierte Ausnahme, die ausgelöst wird, wenn ein Fehler beim Exportieren von Daten auftritt.
 * Erweitert IOException und fügt das Attribut "file" hinzu.
 * @author rjertila
 */
public class DataExportException extends IOException {
    private final String file;

    /**
     * Erstellt eine neue DataExportException.
     * @param path Der Pfad, bei dem der Fehler aufgetreten ist.
     * @param e Die ursprüngliche Ausnahme.
     */
    public DataExportException(String path, Exception e) {
        super(SprachFilter.getMessage("DataExportException") + ": " + path, e);
        this.file = path;
    }

    /**
     * Gibt den Pfad der Datei zurück, bei der der Fehler aufgetreten ist.
     * @return Der Pfad der Datei.
     */
    public String getFile() {
        return file;
    }
}