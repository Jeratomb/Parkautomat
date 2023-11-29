package de.edvschuleplattling.rjertila.parkautomat.transaction;

import de.edvschuleplattling.rjertila.parkautomat.parkautomat.Geldmenge;
import de.edvschuleplattling.rjertila.parkautomat.parkautomat.Kasse;

import java.util.Objects;

public class Transaktion {

    private Kasse kasse;
    private Geldmenge gezahlt;
    private Geldmenge rueckgeld;
    private int Betrag;
    private TransaktionsStatus transaktionsStatus;

    public Transaktion(Kasse kasse, Geldmenge gezahlt, Geldmenge rueckgeld, int betrag, TransaktionsStatus transaktionsStatus) {
        this.kasse = kasse;
        this.gezahlt = gezahlt;
        this.rueckgeld = rueckgeld;
        Betrag = betrag;
        this.transaktionsStatus = transaktionsStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaktion that = (Transaktion) o;

        if (Betrag != that.Betrag) return false;
        if (!Objects.equals(kasse, that.kasse)) return false;
        if (!Objects.equals(gezahlt, that.gezahlt)) return false;
        if (!Objects.equals(rueckgeld, that.rueckgeld)) return false;
        return transaktionsStatus == that.transaktionsStatus;
    }

    @Override
    public String toString() {
        return "Transaktion{" +
                "kasse=" + kasse +
                ", gezahlt=" + gezahlt +
                ", rueckgeld=" + rueckgeld +
                ", Betrag=" + Betrag +
                ", transaktionsStatus=" + transaktionsStatus +
                '}';
    }
}
