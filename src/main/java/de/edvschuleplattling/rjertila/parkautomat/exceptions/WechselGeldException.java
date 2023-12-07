package de.edvschuleplattling.rjertila.parkautomat.exceptions;

/**
 * Diese Klasse stellt eine benutzerdefinierte Ausnahme dar, wenn nicht genügend Wechselgeld vorhanden ist.
 * Sie erweitert GeldException.
 * @author rjertila
 */
public class WechselGeldException extends GeldException {
    /**
     * Konstruktor für WechselGeldException.
     */
    public WechselGeldException() {
        super("Nicht genügend Wechselgeld", "In der Kasse ist aktuell zu wenig Wechselgeld!");
    }
}