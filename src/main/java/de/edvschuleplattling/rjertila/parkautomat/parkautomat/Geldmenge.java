package de.edvschuleplattling.rjertila.parkautomat.parkautomat;

import java.util.Arrays;

/**
 * Diese Klasse repräsentiert eine Menge an Geld in verschiedenen Münz- und Scheinwerten.
 * @author rjertila
 */
public class Geldmenge {
    private final int[] muenzen = new int[8];
    private final int[] multiplikatoren = {10, 20, 50, 100, 200, 500, 1000, 2000};

    /**
     * Konstruktor für Geldmenge mit spezifischen Mengen an Münzen und Scheinen.
     */
    public Geldmenge(int zehnCent, int zwanzigCent, int fünfzigCent, int einEuro, int zweiEuro, int fünfEuro, int zehnEuro, int zwanzigEuro) {
        muenzen[0] = zehnCent;
        muenzen[1] = zwanzigCent;
        muenzen[2] = fünfzigCent;
        muenzen[3] = einEuro;
        muenzen[4] = zweiEuro;
        muenzen[5] = fünfEuro;
        muenzen[6] = zehnEuro;
        muenzen[7] = zwanzigEuro;
    }

    /**
     * Konstruktor für Geldmenge mit spezifischen Mengen an Münzen.
     */
    public Geldmenge(int zehnCent, int zwanzigCent, int fünfzigCent, int einEuro, int zweiEuro) {
        this(zehnCent, zwanzigCent, fünfzigCent, einEuro, zweiEuro, 0, 0, 0);
    }

    /**
     * Konstruktor für Geldmenge mit einer Menge an Münzen.
     */
    public Geldmenge(int[] geld) {
        System.arraycopy(geld, 0, muenzen, 0, muenzen.length);
    }

    /**
     * Standardkonstruktor für Geldmenge.
     */
    public Geldmenge() {
        Arrays.fill(muenzen, 0);
    }

    /**
     * Getter für die Münzen.
     * @return Die Münzen in der Geldmenge.
     */
    public int[] getMuenzen() {
        return muenzen;
    }

    /**
     * Fügt einer Geldmenge eine andere Geldmenge hinzu.
     */
    public void addiereGeld(Geldmenge andere) {
        for (int i = 0; i < andere.muenzen.length; i++) {
            muenzen[i] += andere.muenzen[i];
        }
    }

    /**
     * Subtrahiert eine Geldmenge von einer anderen Geldmenge.
     */
    public void subtrahiereGeld(Geldmenge andere) {
        if (this.getGesamt() < andere.getGesamt()) {
            return; // Nicht genügend Gesamtwert, um die Transaktion durchzuführen
        }

        // Temporärer Speicher des Münzzustands
        int[] originalZustand = Arrays.copyOf(muenzen, muenzen.length);

        // Versuch, die Subtraktion wertmäßig durchzuführen
        for (int i = 0; i < muenzen.length; i++) {
            if (muenzen[i] < andere.muenzen[i]) {
                if (!macheWechselgeld(i, andere.muenzen[i] - muenzen[i])) {
                    // Wiederherstellung des Originalzustands, wenn das Wechseln nicht möglich ist
                    System.arraycopy(originalZustand, 0, muenzen, 0, muenzen.length);
                    return;
                }
            }
            muenzen[i] -= andere.muenzen[i];
        }
    }

    /**
     * Berechnet eine Geldmenge basierend auf einem Betrag.
     */
    private boolean macheWechselgeld(int wertIndex, int defizit) {
        for (int i = wertIndex + 1; i < muenzen.length; i++) {
            while (defizit > 0 && muenzen[i] > 0 && multiplikatoren[i] / multiplikatoren[wertIndex] > 0) {
                muenzen[i]--;
                defizit -= multiplikatoren[i] / multiplikatoren[wertIndex];
            }
            if (defizit <= 0) {
                break;
            }
        }
        return defizit <= 0;
    }

    /**
     * Gibt den Gesamtwert der Geldmenge zurück.
     */
    public int getGesamt() {
        int summe = 0;
        for (int i = 0; i < getMuenzen().length; i++) {
            summe += getMuenzen()[i] * multiplikatoren[i];
        }
        return summe;
    }


    /**
     * Überprüft, ob die Geldmenge für einen bestimmten Betrag gültig ist.
     */

    /**
     * Leert die Geldmenge.
     */
    public void leer() {
        Arrays.fill(muenzen, 0);
    }

    /**
     * Gibt eine String-Repräsentation der Geldmenge zurück.
     */
    @Override
    public String toString() {
        return "Muenzen: " + Arrays.toString(Arrays.stream(muenzen).toArray());
    }
}