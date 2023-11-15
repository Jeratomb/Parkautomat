package de.edvschuleplattling.rjertila.parkautomat.geo;

public class  Kreis extends GeoForm {
    private double radius;

    public Kreis(double radius) {
        setRadius(radius);
    }

    public Kreis() {
        this(1);
    }

    public Kreis(Kreis alt){
        this(alt.getRadius());
    }


    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        pruefeZahl(radius);
        this.radius = radius;
    }


    public double berechneFlaeche() {
        return (Math.PI * (this.getRadius() * this.getRadius()));
    }

    public double berechneUmfang() {
        return (2 * Math.PI * this.getRadius());
    }

    public void setFlaeche(double wert) {
        setRadius(Math.sqrt(wert / Math.PI));
    }

    public void setUmfang(double wert) {
        setRadius((wert / Math.PI) / 2);

    }

    public void erhoeheRadius() {
        erhoeheRadius(1);
    }

    public void erhoeheRadius(double wert) {
        setRadius(this.getRadius() + wert);
    }

    @Override
    public String toString() {
        return "Kreis [ radius = " + String.format("%8.2f",radius) + "]";
    }


    /*
    /**
     * Kleiner abhängig von der Fläche?
     * @param k2 Vergleichskreis
     * @return true/false

    public boolean kleiner(Kreis k2) {
        return this.getFlaeche() < k2.getFlaeche();
    }

    /**
     * Größer abhängig von der Fläche?
     * @param k2 Vergleichskreis
     * @return true/false

    public boolean groesser(Kreis k2) {
        return (this.getFlaeche() > k2.getFlaeche()) ;
    }

    /**
     * Deckungsgleich (kongruent)
     * @param k2 Vergleichskreis
     * @return true/false
     */

    public boolean equals(Kreis k2) {
        return this.getRadius() == k2.getRadius();
    }

}
