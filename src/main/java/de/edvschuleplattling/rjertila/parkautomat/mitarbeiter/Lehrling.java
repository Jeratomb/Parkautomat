package de.edvschuleplattling.rjertila.parkautomat.mitarbeiter;

import java.time.LocalDate;

public class Lehrling extends Mitarbeiter{
    private int lehrJahr;

    public int getLehrJahr() {
        return lehrJahr;
    }

    public void setLehrJahr(int lehrJahr) {
        if (lehrJahr < 1 || lehrJahr > 4){
            throw new IllegalArgumentException("Das Lehrjahr muss größer 0 und kleiner als 5 sein!");
        }
        this.lehrJahr = lehrJahr;
    }

    public Lehrling(int nummer, LocalDate geburtstag, int lehrJahr) {
        super(nummer, geburtstag);
        this.lehrJahr = lehrJahr;
    }

    public double getGrundgehalt(){
        double gehalt = 0;
        switch(getLehrJahr()){
            case 1 : gehalt =  360.14;
            break;
            case 2 : gehalt =  545.12;
            break;
            case 3 : gehalt = 780.45;
            break;
            case 4 : gehalt = 910.24;
            break;
        }
        return gehalt;
    }

    public void erhoeheLehrjahr(){
        if (lehrJahr + 1 > 5) {
            throw new IllegalArgumentException("Das Lehrjahr wird das maximale Lehrjahr übersteigen!");
        }
        lehrJahr += 1;
    }

    public Angestellter macheAngestellter(){
        Angestellter a = new Angestellter(this.getNummer(),this.getGeburtstag(),1);
        return a;
    }

    @Override
    public String toString() {
        return "Lehrling{" +
                "nummer=" + nummer +
                ", geburtstag=" + getGeburtstag() +
                ", gehaltsStufe=" + getLehrJahr() +
                '}';
    }
}
