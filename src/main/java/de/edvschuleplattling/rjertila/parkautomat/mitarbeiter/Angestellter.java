package de.edvschuleplattling.rjertila.parkautomat.mitarbeiter;

import java.time.LocalDate;

public class Angestellter extends Mitarbeiter{
    private int gehaltsStufe;


    public int getGehaltsStufe() {
        return gehaltsStufe;
    }
    public void setGehaltsStufe(int gehaltsStufe) {
        if(gehaltsStufe<1||gehaltsStufe>6){
            throw new IllegalArgumentException("Gehaltsstufe ausserhalb: "+gehaltsStufe);
        }
        this.gehaltsStufe = gehaltsStufe;
    }


    public Angestellter(int nummer, LocalDate geburtstag, int gehaltsStufe) {
        super(nummer, geburtstag);
        setGehaltsStufe(gehaltsStufe);
    }

    public double getGrundgehalt() {
        double gehalt = 1800 * (1 + getGehaltsStufe() / 10.0);
        return gehalt;
    }

      @Override
    public String toString() {
        return "Angestellter{" +
                "nummer=" + nummer +
                ", geburtstag=" + getGeburtstag() +
                ", gehaltsStufe=" + getGehaltsStufe() +
                '}';
    }





}
