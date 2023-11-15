package de.edvschuleplattling.rjertila.parkautomat.elektrogeraete;

public class Bohrmaschine extends ElektroGeraet {
    private int umin; // Umdrehungen pro Minute
    public Bohrmaschine(double leistung, int umin) {
        super(leistung);
        this.umin = umin;
    }
    public int getUmin() {return umin;	}
    public String getSound(){
        String erg="m";
        for (int i = 0; i <= umin/1000; i++) {
            erg+="i";
        }
        return(erg+"h");
    }
    @Override
    public String toString() {
        return("Bohrmaschine["+getLeistung()+", "+getUmin()+"]");
    }
}
