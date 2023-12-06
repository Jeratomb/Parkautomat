package de.edvschuleplattling.rjertila.parkautomat.exceptions;

/**
 * Diese Klasse stellt eine benutzerdefinierte Ausnahme dar, wenn kein passendes Rückgeld vorhanden ist.
 * Sie erweitert GeldException.
 * @author rjertila
 */
public class KeinPassendesRueckgeldException extends GeldException {
    /**
     * Konstruktor für KeinPassendesRueckgeldException mit einem spezifischen Titel.
     * @param title Der Titel der Ausnahme.
     */
    public KeinPassendesRueckgeldException(String title) {
        super(title, "Nicht genug Rückgeld vorhanden!");
    }

    /**
     * Standardkonstruktor für KeinPassendesRueckgeldException.
     */
    public KeinPassendesRueckgeldException() {
        this("Fehler");
    }
}