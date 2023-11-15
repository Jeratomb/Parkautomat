package de.edvschuleplattling.rjertila.parkautomat.geo;

public class Rechteck extends GeoForm {
    private double breite;
    private double hoehe;
    public void setBreite(double breite) {
        this.breite = breite;
    }
    public Rechteck(double breite, double hoehe) {
        setBreite(breite);
        setHoehe(hoehe);
    }

    public double getBreite() {
        return breite;
    }



    public double getHoehe() {
        return hoehe;
    }

    public void setHoehe(double hoehe) {
        pruefeZahl(hoehe);
        this.hoehe = hoehe;
    }

    public double berechneFlaeche(){
        return breite * hoehe;
    }
    public double berechneUmfang(){
        return 2 * (breite + hoehe);
    }

    @Override
    public String toString() {
        return "Rechteck{" +
                "breite=" + getBreite() +
                ", hoehe=" + getHoehe() +
                '}';
    }
}
