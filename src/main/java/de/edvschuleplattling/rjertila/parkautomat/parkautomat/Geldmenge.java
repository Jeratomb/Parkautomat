package de.edvschuleplattling.rjertila.parkautomat.parkautomat;

import java.util.Arrays;

public class Geldmenge {
    private final int[] speicher = new int[8];
    private final int[] mult = {10, 20, 50, 100, 200, 500, 1000, 2000};

    public Geldmenge(int c10, int c20, int c50, int e1, int e2, int s5, int s10, int s20) {
        speicher[0] = c10;
        speicher[1] = c20;
        speicher[2] = c50;
        speicher[3] = e1;
        speicher[4] = e2;
        speicher[5] = s5;
        speicher[6] = s10;
        speicher[7] = s20;
    }
    public Geldmenge(int c10, int c20, int c50, int e1, int e2) {
        this(c10, c20, c50, e1, e2,0,0,0);
    }

    public Geldmenge(int[] money) {
        System.arraycopy(money, 0, speicher, 0, speicher.length);
    }
    public Geldmenge() {
        Arrays.fill(speicher, 0);
    }

    public int getAnzahl(int muenzart) {
        return speicher[muenzart];
    }
    public int[] getSpeicher(){
        return speicher;
    }

    public void plusGeld(Geldmenge other){
        for (int i = 0; i < other.speicher.length; i++) {
            speicher[i] += other.speicher[i];
        }
    }

    public void minusGeld(Geldmenge other){
        for (int i = 0; i < other.speicher.length; i++) {
            speicher[i] -= other.speicher[i];
            if (speicher[i] < 0) {
                throw new IllegalArgumentException("Cannot have negative coin count!");
            }
        }
    }

    public int getTotal() {
        int sum = 0;
        for (int i = 0; i < getSpeicher().length; i++) {
            sum += getSpeicher()[i] * mult[i];
        }
        return sum;
    }

    public boolean isGood() {
        if (this.getTotal() % 10 != 0) return false;
        if (this.getTotal() < 0) return false;
        return true;
    }

    public boolean isGood(int betrag) {
        if (this.getTotal() < betrag) return false;
        return isGood();
    }
    public void setAnzahl(int muenzart, int anzahl) {
        speicher[muenzart] = anzahl;
    }

    @Override
    public String toString() {
        return "Muenzen: " + Arrays.toString(Arrays.stream(speicher).toArray());
    }
}


