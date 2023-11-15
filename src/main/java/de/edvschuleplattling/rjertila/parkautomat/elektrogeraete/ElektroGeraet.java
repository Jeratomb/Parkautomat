package de.edvschuleplattling.rjertila.parkautomat.elektrogeraete;

public abstract class ElektroGeraet {
    private double leistung; // Leistung in Watt
    public ElektroGeraet(double leistung) {
        this.leistung = leistung;
    }

    public double getLeistung() {return leistung;}
    public boolean willStarkenStrom(){
        return(getLeistung()>2000.0);
    }



    public void doSound(){

        String sound = getSound();
        System.out.println(sound);


    }

    public abstract String getSound();
}
