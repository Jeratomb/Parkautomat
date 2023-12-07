package de.edvschuleplattling.rjertila.parkautomat.exceptions;

/**
 * Eine benutzerdefinierte Ausnahme, die ausgelöst wird, wenn kein passendes Rückgeld vorhanden ist.
 * Erweitert GeldException.
 * @author rjertila
 */
public class KeinPassendesRueckgeldException extends GeldException {
    /**
     * Erstellt eine neue KeinPassendesRueckgeldException mit einem spezifischen Titel.
     * @param title Der Titel der Ausnahme.
     */
    public KeinPassendesRueckgeldException(String title) {
        super(title, SprachFilter.getMessage("KeinPassendesRueckgeldException"));
    }

    /**
     * Erstellt eine neue KeinPassendesRueckgeldException mit einem Standardtitel.
     */
    public KeinPassendesRueckgeldException() {
        this(SprachFilter.getMessage("Fehler"));
    }
}