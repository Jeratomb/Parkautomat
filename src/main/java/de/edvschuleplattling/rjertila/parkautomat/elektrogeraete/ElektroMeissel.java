package de.edvschuleplattling.rjertila.parkautomat.elektrogeraete;

public class ElektroMeissel extends ElektroGeraet {
    private double schlagKraft; // in kg
    public ElektroMeissel(double leistung, double schlagKraft) {
        super(leistung);
        this.schlagKraft = schlagKraft;
    }
    public double getSchlagKraft() {return schlagKraft;	}
    public String getSound(){
        if(schlagKraft>50){	return("PENG");}
        return("peng");
    }
    @Override
    public String toString() {
        return("ElektroMeissel["+getLeistung()+", "+getSchlagKraft()+"]");
    }
}
