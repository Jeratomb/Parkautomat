package de.edvschuleplattling.rjertila.parkautomat.mitarbeiter;

import java.time.LocalDate;

/**
 * Mitarbeiter eines Betriebs
 * @author dieter roth
 *
 */
public abstract class Mitarbeiter {
    public int nummer;			// Identifiziert den Mitarbeiter
    private LocalDate geburtstag;
    //private int gehaltsStufe;	// Bestimmt zusammen mit dem Alter das Gehalt

    public Mitarbeiter(int nummer, LocalDate geburtstag) {
        setNummer(nummer);
        setGeburtstag(geburtstag);
    }

    public Mitarbeiter() {
        this(99999,LocalDate.of(1963,10,13));
    }

    public Mitarbeiter(Mitarbeiter alt) {
        this(alt.getNummer(),alt.getGeburtstag());
    }

    // Getter und Setter mit Plausiprüfngen
    public int getNummer() {
        return nummer;
    }
    public void setNummer(int nummer) {
        if(nummer<10000||nummer>99999){
            throw new IllegalArgumentException("Nummer ausserhalb: "+nummer);
        }
        this.nummer = nummer;
    }

    public LocalDate getGeburtstag() {
        return geburtstag;
    }

    public void setGeburtstag(LocalDate geburtstag) {
        this.geburtstag = geburtstag;
    }

    private void pruefeDatum(int jahr, int monat) {
        int[] akt = jetzt();
        if(jahr>akt[0] || (jahr==akt[0]&&monat>akt[1])){
            throw new IllegalArgumentException("Datum in der Zukunft: "+monat+"."+jahr);
        }
    }

    public static int[] jetzt(){
        int erg[] = {
                java.time.LocalDate.now().getYear(),
                java.time.LocalDate.now().getMonthValue()
        };
        return erg;
    }


    protected double getGebzulage(int jahr, int monat) {
        double gebzulage = 0;
        if(monat==getGeburtstag().getMonthValue()){
            int alter = jahr-getGeburtstag().getYear();
            switch (alter) {
                case 40:
                    gebzulage = 175.0;
                    break;
                case 50:
                    gebzulage = 350.0;
                    break;
                case 60:
                    gebzulage = 500.0;
                    break;
                case 65:
                    gebzulage = 900.0;
                    break;
                default:
                    gebzulage = 0;
                    break;
            }
        }
        return gebzulage;
    }

    public abstract double getGrundgehalt();
    public double getGehalt(int jahr , int monat){
        return getGrundgehalt() + getGebzulage(jahr,monat);
    }

      public double getGehalt(){
        int[] akt = jetzt();
        return getGehalt(akt[0],akt[1]);
    }

    // Methoden zum Vergleichen von Mitarbeitern
    public boolean equals(Mitarbeiter derAndere) {
        return this.getNummer()==derAndere.getNummer();
    }

    public boolean kleiner(Mitarbeiter derAndere) {
        return this.getNummer()<derAndere.getNummer();
    }

    public boolean groesser(Mitarbeiter derAndere) {
        return this.getNummer()>derAndere.getNummer();
    }

}