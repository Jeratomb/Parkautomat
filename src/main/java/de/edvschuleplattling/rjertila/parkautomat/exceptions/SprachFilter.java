package de.edvschuleplattling.rjertila.parkautomat.exceptions;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Eine Hilfsklasse, die für die Internationalisierung von Ausnahmemeldungen verwendet wird.
 * Sie ermöglicht das Abrufen von Nachrichten in der aktuell ausgewählten Sprache.
 * @author rjertila
 */
public class SprachFilter {
    private static Locale currentLocale = Locale.getDefault();

    /**
     * Setzt die aktuell ausgewählte Sprache.
     * @param locale Die neue Sprache.
     */
    public static void setLocale(Locale locale) {
        currentLocale = locale;
    }

    /**
     * Gibt die Nachricht für einen gegebenen Schlüssel in der aktuell ausgewählten Sprache zurück.
     * @param key Der Schlüssel der Nachricht.
     * @return Die Nachricht in der aktuell ausgewählten Sprache.
     */
    public static String getMessage(String key) {
        ResourceBundle bundle = ResourceBundle.getBundle("nachrichten", currentLocale);
        return bundle.getString(key);
    }
}