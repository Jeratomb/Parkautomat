package de.edvschuleplattling.rjertila.parkautomat.geo;

public class Dreieck extends GeoForm{
    private double seite1;
    private double seite2;
    private double seite3;

    public Dreieck(double seite1, double seite2, double seite3) {
        setSeite1(seite1);
        setSeite2(seite2);
        setSeite3(seite3);
    }

    public double getSeite1() {
        return seite1;
    }

    public void setSeite1(double seite1) {
        pruefeZahl(seite1);
        this.seite1 = seite1;
    }

    public double getSeite2() {
        return seite2;
    }

    public void setSeite2(double seite2) {
        pruefeZahl(seite2);
        this.seite2 = seite2;
    }

    public double getSeite3() {
        return seite3;
    }

    public void setSeite3(double seite3) {
        pruefeZahl(seite3);
        this.seite3 = seite3;
    }
    public double berechneS(){
        return (seite1 + seite2 + seite3) / 2;
    }

    public double berechneFlaeche(){
        double s = berechneS();
        return Math.sqrt(s*(s-seite1)*(s-seite2)*(s-seite3));
    }
    public double berechneUmfang(){
        return seite1 + seite2 + seite3;
    }

    @Override
    public String toString() {
        return "Dreieck{" +
                "seite1=" + getSeite1() +
                ", seite2=" + getSeite2() +
                ", seite3=" + getSeite3() +
                '}';
    }
}
