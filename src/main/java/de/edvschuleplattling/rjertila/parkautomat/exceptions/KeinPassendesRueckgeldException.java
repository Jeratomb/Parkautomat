package de.edvschuleplattling.rjertila.parkautomat.exceptions;

import de.edvschuleplattling.rjertila.parkautomat.parkautomat.Geldmenge;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
/**
 * Fehler Bei unzureichendem Wechselgeld im Bezahlvorgang
 * Fehler wird Automatisch in "fehler.log" geschrieben
 * Bei Fehlern im schreibvorang wird eine Meldung in der Konsole ausgegeben
 */
public class KeinPassendesRueckgeldException extends Exception {

    private final Geldmenge KASSENSTAND;
    private final Geldmenge GEZAHLT;
    private final int BETRAG;
    private final LocalDateTime ZEIT_STEMPEL;

    public KeinPassendesRueckgeldException(Geldmenge kassenstand, Geldmenge gezahlt, int betrag){
        this.KASSENSTAND = kassenstand;
        this.GEZAHLT = gezahlt;
        this.BETRAG = betrag;
        this.ZEIT_STEMPEL = LocalDateTime.now();
        try {
            Files.writeString(new File("src/main/java/de/edvschuleplattling/rjertila/parkautomat/exceptions/fehler.log").toPath(),this.toString(), StandardOpenOption.APPEND);
        } catch (IOException e){
            System.out.println("Fehler beim schreiben ders Errorlogs (./fehler.log)");
        }
    }

    public Geldmenge getKassenstand() {
        return KASSENSTAND;
    }

    public Geldmenge getGezahlt() {
        return GEZAHLT;
    }

    public int getBetrag() {
        return BETRAG;
    }

    public LocalDateTime getZeitStempel() {
        return ZEIT_STEMPEL;
    }

    @Override
    public String toString() {
        return "NichtGenugWechselgeldException{" +
                "kassenstand=" + KASSENSTAND.toString() +
                ", gezahlt=" + GEZAHLT.toString() +
                ", betrag=" + BETRAG +
                ", zeitStempel=" + ZEIT_STEMPEL.toString() +
                '}';
    }
}
