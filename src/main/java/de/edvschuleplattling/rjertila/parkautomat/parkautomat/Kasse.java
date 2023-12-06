package de.edvschuleplattling.rjertila.parkautomat.parkautomat;

import de.edvschuleplattling.rjertila.parkautomat.exceptions.WechselGeldException;

/**
 * Diese Klasse repräsentiert die Kasse des Parkautomaten.
 * @author rjertila
 */
public class Kasse {
    private Geldmenge geldmenge;
    private final int[] multiplikatoren = {10, 20, 50, 100, 200, 500, 1000, 2000};

    /**
     * Konstruktor für Kasse mit einer bestimmten Anfangsmenge an Geld.
     * @param startMenge Die Anfangsmenge an Geld.
     */
    public Kasse(Geldmenge startMenge) {
        this.geldmenge = startMenge;
    }

    /**
     * Standardkonstruktor für Kasse.
     */
    public Kasse() {
        this.geldmenge = new Geldmenge();
    }

    /**
     * Getter für die Geldmenge.
     * @return Die Geldmenge in der Kasse.
     */
    public Geldmenge getGeldmenge() {
        return geldmenge;
    }

    /**
     * Setter für die Geldmenge.
     * @param geldmenge Die neue Geldmenge in der Kasse.
     */
    public void setGeldmenge(Geldmenge geldmenge) {
        this.geldmenge = geldmenge;
    }

    /**
     * Führt eine Bezahlung durch und gibt das Wechselgeld zurück.
     * @param betrag Der zu zahlende Betrag.
     * @param zahlMenge Die gezahlte Geldmenge.
     * @return Die Geldmenge des Wechselgelds.
     * @throws WechselGeldException Wenn nicht genügend Wechselgeld vorhanden ist.
     */
    public Geldmenge bezahle(int betrag, Geldmenge zahlMenge) throws WechselGeldException {
        int gesamtBetrag = zahlMenge.getGesamt();
        int wechselgeldBetrag = gesamtBetrag - betrag;

        if (wechselgeldBetrag < 0) {
            throw new WechselGeldException();
        }

        Geldmenge wechselgeld;
        try {
            wechselgeld = berechneGeld(wechselgeldBetrag);
        } catch (WechselGeldException e) {
            throw e;
        }

        this.geldmenge.subtrahiereGeld(wechselgeld);
        this.geldmenge.addiereGeld(zahlMenge);

        return wechselgeld;
    }


    /**
     * Berechnet eine Geldmenge basierend auf einem Betrag.
     * @param betrag Der Betrag.
     * @return Die berechnete Geldmenge.
     * @throws WechselGeldException Wenn nicht genügend Wechselgeld vorhanden ist.
     */
    private Geldmenge berechneGeld(int betrag) throws WechselGeldException {
        Geldmenge ergebnis = new Geldmenge();
        int wechselgeldBenoetigt = betrag;

        if (wechselgeldBenoetigt == 0) return ergebnis;

        for (int i = multiplikatoren.length - 1; i >= 0; i--) {
            int münzwert = multiplikatoren[i];
            int münzenBenötigt = wechselgeldBenoetigt / münzwert;
            int verfügbareMünzen = geldmenge.getMuenzen()[i];

            if (münzenBenötigt > 0 && verfügbareMünzen > 0) {
                int münzenZuGeben = Math.min(münzenBenötigt, verfügbareMünzen);
                ergebnis.getMuenzen()[i] = münzenZuGeben;
                wechselgeldBenoetigt -= münzenZuGeben * münzwert;
            }

            if (wechselgeldBenoetigt == 0) {
                break;
            }
        }

        if (wechselgeldBenoetigt > 0) {
            throw new WechselGeldException();
        }

        return ergebnis;
    }

    /**
     * Gibt eine String-Repräsentation der Kasse zurück.
     * @return Eine String-Repräsentation der Kasse.
     */
    @Override
    public String toString() {
        return geldmenge.toString();
    }
}