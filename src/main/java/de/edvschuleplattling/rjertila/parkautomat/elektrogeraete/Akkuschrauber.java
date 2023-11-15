package de.edvschuleplattling.rjertila.parkautomat.elektrogeraete;

public class Akkuschrauber extends ElektroGeraet {
    private double akkuLaufzeit;

    public Akkuschrauber(double leistung, double akkuLaufzeit) {
        super(leistung);
        this.akkuLaufzeit = akkuLaufzeit;
    }

    public double getAkkuLaufzeit() {
        return akkuLaufzeit;
    }

    public void setAkkuLaufzeit(double akkuLaufzeit) {
        this.akkuLaufzeit = akkuLaufzeit;
    }

    public String getSound(){
        return "DREHDREHDREH";
    }

    @Override
    public String toString() {return("Akkuschrauber["+getLeistung()+", "+getAkkuLaufzeit()+"]");
    }
}
