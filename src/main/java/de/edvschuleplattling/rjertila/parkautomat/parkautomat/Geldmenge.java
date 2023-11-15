package de.edvschuleplattling.rjertila.parkautomat.parkautomat;

public class Geldmenge {
    private final int[] geldspeicher;

    public Geldmenge(int c10, int c20, int c50, int e1, int e2, int s5, int s10, int s20) {
        geldspeicher = new int[]{c10, c20, c50, e1, e2, s5, s10, s20};
    }

    public int getAnzahl(int muenzart) {
        return geldspeicher[muenzart];
    }

    public void setAnzahl(int muenzart, int anzahl) {
        geldspeicher[muenzart] = anzahl;
    }
}


