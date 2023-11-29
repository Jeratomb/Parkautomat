package de.edvschuleplattling.rjertila.parkautomat.parkautomat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Geldmenge {

    // in cent
    public static final Integer[] GELDSTUECK_ARTEN = {2000, 1000, 500, 200, 100, 50, 20, 10};
    private final Map<Integer,Integer> GELD = new HashMap<>();


    public Geldmenge() {
        this(0,0,0,0,0,0,0,0);
    }

    public Geldmenge(Geldmenge gm){
        this(gm.getGeld());
    }

    public Geldmenge(Map<Integer,Integer> geld){
        Arrays.stream(GELDSTUECK_ARTEN).toList().forEach(i -> getGeld().put(i,geld.get(i)));
    }

    public Geldmenge(int ... geld){
        if (geld.length > GELDSTUECK_ARTEN.length) throw new IllegalArgumentException("Es gibt nur " + GELDSTUECK_ARTEN.length + " arten an geldstuecken");
        if (Arrays.stream(geld).anyMatch(i -> i < 0)) throw new IllegalArgumentException("Keine negativen Betraege");
        for (int i = 0; i < geld.length; i++) {
//            System.out.println(geld[i]);
//            System.out.println(GELDSTUECK_ARTEN[GELDSTUECK_ARTEN.length - 1 - i]);
//            System.out.println();
            GELD.put(GELDSTUECK_ARTEN[GELDSTUECK_ARTEN.length - 1 - i], geld[i]);
        }
    }

    /**
     * gibt die anzahl einer muenzart zurueck
     * @param muenzart die muenzart deren menge gesucht werden soll
     * @return die menge
     */
    public int getAnzahl(int muenzart){
        return getGeld().get(muenzart);
    }

    /**
     * Zieht eine geldmenge ab
     * @param gm geldmenge zum abzuiehen
     * @throws IllegalArgumentException wenn einer der muenzbestaende negativ werden wuerde
     */
    public void abziehen(Geldmenge gm) throws IllegalArgumentException{
        if(gm.hasNegativBetraege()) throw new IllegalArgumentException("Nur positive werte koennen abgezogen werden");
        if(gm.getGeld().keySet().stream().anyMatch(i -> getGeld().get(i) - gm.getGeld().get(i) < 0)) throw new IllegalArgumentException("Werte duerfen nicht negativ werden");
        getGeld().keySet().forEach(i -> setAnzahl(i,getAnzahl(i) - gm.getAnzahl(i)));
    }

    /**
     * Fuegt eine geldmenge hinzu
     * @param gm geldmenge zum hinzufuegen
     * @throws IllegalArgumentException wenn ein oder mehrere geldstuecke negeativ sind
     */
    public void hinzufuegen(Geldmenge gm) throws IllegalArgumentException{
        if(gm.hasNegativBetraege()) throw new IllegalArgumentException("Nur positive werte koennen addiert werden");
        getGeld().keySet().forEach(i -> setAnzahl(i,getAnzahl(i) + gm.getAnzahl(i)));
    }

    /**
     * Erzeugt neue geldmenge aus der Summe beider Geldmengen
     * @param gm1 geldmenge1
     * @param gm2 geldmenge2
     * @return neue geldmenge
     * @deprecated nicht static hinzufuegen benutzen mit new copy konstruktor
     */
    @Deprecated
    public static Geldmenge hinzufuegen(Geldmenge gm1, Geldmenge gm2) {
        Geldmenge gm = new Geldmenge(gm1);
        gm.hinzufuegen(gm2);
        return gm;
    }

    /**
     * @return Gesammtbetrag
     */
    public int getBetrag(){
        int sum = 0;
        for(int i : getGeld().keySet()){
            sum += getAnzahl(i) * i;
        }
        return sum;
    }

    /**
     * Setzt die anzahl einer bestimmten muenzart
     * @param muenzart die muenze deren menge gesetzt werden soll
     * @param anzahl die anzahl der muenzen
     */
    public void setAnzahl(int muenzart, int anzahl) {
        if(anzahl < 0) throw new IllegalArgumentException("Wert darf nicht negativ sein");
        if(!getGeld().containsKey(muenzart)) throw new IllegalArgumentException("Muenzart \"" + muenzart + "\" unglueltig");
        getGeld().replace(muenzart, anzahl);
    }

    /**
     * erhoeht die anzahl einer muenzart um 1
     * @param muenzart die muenzart die erhoeht werden soll
     */
    public void addEins(int muenzart) {
        setAnzahl(muenzart, getAnzahl(muenzart) + 1);
    }

    /**
     * Ueberpfueft ob muenzart Gueltig ist
     * @param muenzart muenzart in cent
     * @return True = ungueltig, False = gueltig
     */
    @Deprecated
    private boolean isMuenzartNichtValide(int muenzart){
        return !getGeld().containsKey(muenzart);
    }


    private Map<Integer, Integer> getGeld() {
        return GELD;
    }

    private boolean hasNegativBetraege(){
        return getGeld().values().stream().anyMatch(i -> i < 0);
    }

    @Override
    public String toString() {
        return "Geldmenge{" +
                "geld=" + GELD +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Geldmenge geldmenge = (Geldmenge) o;
        return getGeld().equals(geldmenge.getGeld());
    }
}
