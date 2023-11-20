package de.edvschuleplattling.rjertila.parkautomat.parkautomat;

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
        int providedTotal = zahlMenge.getTotal();
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
}
