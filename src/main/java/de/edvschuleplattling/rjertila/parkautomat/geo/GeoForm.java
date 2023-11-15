package de.edvschuleplattling.rjertila.parkautomat.geo;

public abstract class GeoForm {
    public void pruefeZahl(double zahl){
        if (zahl <= 0) {
            throw new IllegalArgumentException("Die Zahl muss größer 0 sein !");
        }
    }


    public abstract double berechneFlaeche();
    public abstract double berechneUmfang();

    public boolean groesser(GeoForm form){
        return this.berechneFlaeche() > form.berechneFlaeche();
    }
    public boolean kleiner(GeoForm form){
        return this.berechneFlaeche() < form.berechneFlaeche();
    }
}
