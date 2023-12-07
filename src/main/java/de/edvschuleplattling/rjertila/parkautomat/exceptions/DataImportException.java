package de.edvschuleplattling.rjertila.parkautomat.exceptions;

public class DataImportException extends Exception {
    private final String file;
    private final int zeilenNr;

    public DataImportException(String file, int zeilenNr) {
        super("Fehler beim einlesen der datei " + file + ":" + zeilenNr);
        this.file = file;
        this.zeilenNr = zeilenNr;
    }

    public int getZeilenNr() {
        return zeilenNr;
    }

    public String getFile() {
        return file;
    }
}
