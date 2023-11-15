package de.edvschuleplattling.rjertila.parkautomat.parkautomat;

public class Kasse {
    private final Geldmenge geldspeicher;

    public Kasse(Geldmenge startMenge) {
        geldspeicher = startMenge;
    }

    public int getBetrag() {
        return geldspeicher.getAnzahl(0) * 10 + geldspeicher.getAnzahl(1) * 20 +
                geldspeicher.getAnzahl(2) * 50 + geldspeicher.getAnzahl(3) * 100 +
                geldspeicher.getAnzahl(4) * 200 + geldspeicher.getAnzahl(5) * 500 +
                geldspeicher.getAnzahl(6) * 1000 + geldspeicher.getAnzahl(7) * 2000;
    }

    public Geldmenge bezahle(int betrag, Geldmenge zahlung) {
        // Implementierung der Zahlungslogik hier

        // Beispiel: Rückgabe von Münzen
        Geldmenge rueckgabe = new Geldmenge(0, 0, 0, 0, 0, 0, 0, 0);
        rueckgabe.setAnzahl(0, 4); // Beispiel: Vier 10c Münzen zurückgeben
        // ...

        // Aktualisierung des Geldspeichers
        // Beispiel: Reduziere die Anzahl der vorhandenen 10c Münzen um 4
        geldspeicher.setAnzahl(0, geldspeicher.getAnzahl(0) - 4);
        // ...

        return rueckgabe;
    }

    // Weitere private/protected Methoden können hier definiert werden
}
