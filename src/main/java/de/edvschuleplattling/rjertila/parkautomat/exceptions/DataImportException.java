package de.edvschuleplattling.rjertila.parkautomat.exceptions;

/**
 * Eine benutzerdefinierte Ausnahme, die ausgelöst wird, wenn ein Fehler beim Importieren von Daten auftritt.
 * Erweitert Exception und fügt die Attribute "file" und "zeilenNr" hinzu.
 * @author rjertila
 */
public class DataImportException extends Exception {
    private final String file;
    private final int zeilenNr;

    /**
     * Erstellt eine neue DataImportException.
     * @param file Der Pfad der Datei, bei der der Fehler aufgetreten ist.
     * @param zeilenNr Die Zeilennummer, in der der Fehler aufgetreten ist.
     */
    public DataImportException(String file, int zeilenNr) {
        super(SprachFilter.getMessage("DataImportException") + ": " + file + ":" + zeilenNr);
        this.file = file;
        this.zeilenNr = zeilenNr;
    }

    /**
     * Gibt die Zeilennummer zurück, in der der Fehler aufgetreten ist.
     * @return Die Zeilennummer.
     */
    public int getZeilenNr() {
        return zeilenNr;
    }

    /**
     * Gibt den Pfad der Datei zurück, bei der der Fehler aufgetreten ist.
     * @return Der Pfad der Datei.
     */
    public String getFile() {
        return file;
    }
}