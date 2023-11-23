package de.edvschuleplattling.rjertila.parkautomat.parkautomat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Kasse {
    public Geldmenge geld;
    private final int[] mult = {10, 20, 50, 100, 200, 500, 1000, 2000};

    public Kasse(Geldmenge startMenge) {
        this.geld = startMenge;
    }
    public Kasse() {
        this.geld = new Geldmenge();
    }

    public Geldmenge bezahle(int betrag, Geldmenge zahlMenge) {
        if (!zahlMenge.isGood(betrag)) throw new IllegalArgumentException("Not enough money provided");
        int providedTotal = zahlMenge.getBetrag();
        int changeAmount = providedTotal - betrag;

        Geldmenge change;
        try {
            change = berechneGeld(changeAmount);
            this.geld.minusGeld(zahlMenge);
            this.geld.plusGeld(change);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Geldmenge();
        }

        return change;
    }

    private Geldmenge berechneGeld(int betrag) {
        Geldmenge res = new Geldmenge();
        int changeNeeded = betrag;

        for (int i = mult.length - 1; i >= 0; i--) {
            int coinValue = mult[i];
            int coinsNeeded = changeNeeded / coinValue;
            int coinsAvailable = geld.getSpeicher()[i];

            if (coinsNeeded > 0 && coinsAvailable > 0) {
                int coinsToGive = Math.min(coinsNeeded, coinsAvailable);
                res.getSpeicher()[i] = coinsToGive;
                changeNeeded -= coinsToGive * coinValue;
            }

            if (changeNeeded == 0) {
                break;
            }
        }

        if (changeNeeded > 0) {
            throw new IllegalArgumentException("Nicht genügend Wechselgeld!");
        }

        return res;
    }

    @Override
    public String toString() {
        return geld.toString();
    }

    public void readStartGeldmengeFromCSV() {
        String csvFile = "init.csv"; // Name der CSV-Datei im aktuellen Verzeichnis
        String line;
        String cvsSplitBy = ","; // Trennzeichen in der CSV-Datei

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            line = br.readLine(); // Lese die erste Zeile (Münzwerte)
            String[] values = line.split(cvsSplitBy);

            // Fülle die Startgeldmenge mit den Werten aus der CSV-Datei
            for (int i = 0; i < values.length; i++) {
                int coinValue = Integer.parseInt(values[i].trim());
                geld.setAnzahl(i, coinValue);
            }

            System.out.println("Startgeldmenge erfolgreich aus der CSV-Datei geladen.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
