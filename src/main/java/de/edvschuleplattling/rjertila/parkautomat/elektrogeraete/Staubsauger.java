package de.edvschuleplattling.rjertila.parkautomat.elektrogeraete;

public class Staubsauger extends ElektroGeraet{
    private double volumen;

    public Staubsauger(double leistung, double volumen) {
        super(leistung);
        this.volumen = volumen;
    }

    public double getVolumen() {
        return volumen;
    }

    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }

    public String getSound(){
        return "VRUUUUUUUUUUUM";
    }

    @Override
    public String toString() {
         return("Staubsauger["+getLeistung()+", "+ getVolumen()+"]");
    }
}
