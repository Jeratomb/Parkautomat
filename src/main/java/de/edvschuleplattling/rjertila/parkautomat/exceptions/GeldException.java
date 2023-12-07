package de.edvschuleplattling.rjertila.parkautomat.exceptions;

/**
 * Eine benutzerdefinierte Ausnahme für Geldoperationen.
 * Erweitert Exception und fügt ein Attribut "title" hinzu.
 * @author rjertila
 */
public class GeldException extends Exception {
    private String title;

    /**
     * Erstellt eine neue GeldException.
     * @param title Der Titel der Ausnahme.
     * @param message Die Detailnachricht der Ausnahme.
     */
    public GeldException(String title, String message) {
        super(SprachFilter.getMessage(message));
        this.title = title;
    }
    public GeldException(String message) {
        super(SprachFilter.getMessage(message));
    }

    /**
     * Gibt den Titel der Ausnahme zurück.
     * @return Der Titel der Ausnahme.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setzt den Titel der Ausnahme.
     * @param title Der neue Titel der Ausnahme.
     */
    public void setTitle(String title) {
        this.title = title;
    }
}