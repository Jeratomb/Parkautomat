package de.edvschuleplattling.rjertila.parkautomat.exceptions;


/**
 * Diese Klasse stellt eine benutzerdefinierte Ausnahme für Geldoperationen dar.
 * Sie erweitert Exception und fügt ein Attribut "title" hinzu.
 * @author rjertila
 */
public class GeldException extends Exception {
    private String title;


    /**
     * Konstruktor für GeldException.
     * @param title Der Titel der Ausnahme.
     * @param message Die Detailnachricht der Ausnahme.
     */
    public GeldException(String title, String message) {
        super("Fehler: " + message);
        this.title = title;
    }

    /**
     * Getter für den Titel.
     * @return Der Titel der Ausnahme.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter für den Titel.
     * @param title Der neue Titel der Ausnahme.
     */
    public void setTitle(String title) {
        this.title = title;
    }

}