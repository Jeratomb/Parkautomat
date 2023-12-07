package de.edvschuleplattling.rjertila.parkautomat.exceptions;

/**
 * Eine benutzerdefinierte Ausnahme, die ausgelöst wird, wenn nicht genügend Wechselgeld vorhanden ist.
 * Erweitert GeldException.
 */
public class WechselGeldException extends GeldException {
    /**
     * Erstellt eine neue WechselgeldException.
     * @param title Der Titel der Ausnahme.
     */
    public WechselGeldException(String title) {
        super(title, SprachFilter.getMessage("wechselGeldException"));
    }

    public WechselGeldException() {
        super(SprachFilter.getMessage("wechselGeldException"));
    }
}