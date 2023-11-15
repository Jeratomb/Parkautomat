package de.edvschuleplattling.rjertila.parkautomat.geo;

public class Quadrat extends GeoForm{
    private double side;

    public Quadrat(double seite) {
        setSeite(seite);
    }

    public double getSeite() {
        return side;
    }

    public void setSeite(double seite) {
        pruefeZahl(seite);
        this.side = seite;
    }

    public double berechneFlaeche(){
        return side * side;
    }
    public double berechneUmfang(){
        return 4 * side;
    }

    @Override
    public String toString() {
        return "Quadrat{" +
                "seite=" + getSeite() +
                '}';
    }
}
