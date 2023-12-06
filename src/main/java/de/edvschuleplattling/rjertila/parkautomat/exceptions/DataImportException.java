package de.edvschuleplattling.rjertila.parkautomat.exceptions;

/**
 * Diese Klasse stellt eine benutzerdefinierte Ausnahme dar, wenn ein Fehler beim Importieren von Daten auftritt.
 * Sie erweitert Exception und fügt die Attribute "file" und "zeilenNr" hinzu.
 * @author rjertila
 */
public class DataImportException extends Exception {
    private final String file;
    private final int zeilenNr;

    /**
     * Konstruktor für DataImportException.
     * @param file Der Name der Datei, bei der der Fehler aufgetreten ist.
     * @param zeilenNr Die Zeilennummer, in der der Fehler aufgetreten ist.
     */
    public DataImportException(String file, int zeilenNr) {
        super("Fehler beim einlesen der datei " + file + ":" + zeilenNr);
        this.file = file;
        this.zeilenNr = zeilenNr;
    }

    /**
     * Getter für die Zeilennummer.
     * @return Die Zeilennummer, in der der Fehler aufgetreten ist.
     */
    public int getZeilenNr() {
        return zeilenNr;
    }

    /**
     * Getter für den Dateinamen.
     * @return Der Name der Datei, bei der der Fehler aufgetreten ist.
     */
    public String getFile() {
        return file;
    }
}