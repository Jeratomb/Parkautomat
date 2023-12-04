package de.edvschuleplattling.rjertila.parkautomat.parkautomat;

import de.edvschuleplattling.rjertila.parkautomat.exceptions.KeinPassendesRueckgeldException;
public class Kasse {

    private final Geldmenge speicher;

    public Kasse(Geldmenge inhalt) {
        this.speicher = inhalt;
    }
    public Kasse(){
        this(new Geldmenge());
    }

    /**
     * Gesammtwert des Inhaltes
     * @return Gesammtwert
     */
    public int getBetrag(){
        return speicher.getBetrag();
    }

    /**
     * Bezahlen am Kassenautomaten
     * @param betrag zu zahlender betrag
     * @param geld gezahlte geldmenge
     * @return Geldmenge wechselgeld
     * @throws KeinPassendesRueckgeldException wenn die kasse nicht ausreichend wechselgeld hat
     * @throws IllegalArgumentException wenn zu wenig geld zum bezahlen gegeben wurde
     */
    public Geldmenge bezahle(int betrag, Geldmenge geld) throws KeinPassendesRueckgeldException, IllegalArgumentException {
        if(geld.getBetrag() < betrag) throw new IllegalArgumentException("Gegebenes geld muss >= zu zahlender betrag sein");

        Geldmenge rueckgeld = new Geldmenge();
        Geldmenge ausgang = new Geldmenge(getInhalt());
        ausgang.hinzufuegen(geld);

        int restbetrag = geld.getBetrag() - betrag;

        for(int i : Geldmenge.GELDSTUECK_ARTEN){
            if(i > 200) continue;
            if(ausgang.getAnzahl(i) == 0) continue;
            if(i > restbetrag) continue;

            int anzahl = Math.floorDiv(restbetrag,i);
            if (anzahl > ausgang.getAnzahl(i)) anzahl = ausgang.getAnzahl(i);

            rueckgeld.setAnzahl(i,anzahl);
            restbetrag -= anzahl * i;
        }

        if(restbetrag > 0) throw new KeinPassendesRueckgeldException(getInhalt(),geld,betrag);

        getInhalt().hinzufuegen(geld);
        getInhalt().abziehen(rueckgeld);

        return rueckgeld;
    }

    /**
     * Gibt den gesammten inhalt zurueck
     * @return Geldmenge inhalt
     */
    public Geldmenge getInhalt() {
        return speicher;
    }

    @Override
    public String toString() {
        return "Kasse{" +
                "inhalt=" + speicher +
                '}';
    }
}
