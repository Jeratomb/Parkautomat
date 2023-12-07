package de.edvschuleplattling.rjertila.parkautomat.transaction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Diese Klasse repräsentiert eine Transaktion im Parkautomaten.
 * Sie enthält Informationen über den Betrag, den bezahlten Betrag, die Zeit und den Status der Transaktion.
 *
 * @author rjertila
 */
public class Transaktion {
    private int betrag;
    private int paid;
    private LocalDateTime zeit;
    private Status status;

    /**
     * Erstellt eine neue Transaktion mit den gegebenen Werten.
     */
    public Transaktion(int betrag, int paid, LocalDateTime zeit, Status status) {
        this.betrag = betrag;
        this.paid = paid;
        this.zeit = zeit;
        this.status = status;
    }

    /**
     * Erstellt eine neue Transaktion mit dem gegebenen Betrag und Status.
     * Der bezahlte Betrag wird auf den gegebenen Betrag gesetzt und die Zeit auf die aktuelle Zeit.
     */
    public Transaktion(int betrag, Status status) {
        this.betrag = betrag;
        this.paid = betrag;
        this.status = status;
        this.zeit = LocalDateTime.now();
    }

    public int getBetrag() {
        return betrag;
    }

    public void setBetrag(int betrag) {
        this.betrag = betrag;
    }

    public LocalDateTime getZeit() {
        return zeit;
    }

    public void setZeit(LocalDateTime zeit) {
        this.zeit = zeit;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    /**
     * Konvertiert die Transaktion in eine CSV-Zeichenkette.
     * Die CSV-Zeichenkette enthält die Zeit, den Betrag, den bezahlten Betrag und den Status der Transaktion, getrennt durch Semikolons.
     *
     * @return Die CSV-Zeichenkette, die die Transaktion darstellt.
     */
    public String toCSV() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return this.getZeit().format(formatter) +
                ";" +
                this.getBetrag() +
                ";" +
                this.getPaid() +
                ";" +
                this.getStatus().toString() +
                "\n";
    }

    /**
     * Konvertiert die Transaktion in eine Zeichenkette für die Anzeige in einer ListView.
     * Die Zeichenkette enthält die Zeit, den Betrag, den bezahlten Betrag und den Status der Transaktion, formatiert für die Anzeige.
     *
     * @return Die formatierte Zeichenkette, die die Transaktion darstellt.
     */

    public String toListView() {
        String betragValue = String.format("%05.2f", (this.getBetrag() * 1.00) / 100);
        String paidValue = String.format("%05.2f", (this.getPaid() * 1.00) / 100);
        return String.format("%-30s %-20s %-20s %-20s",
                this.getZeit().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                betragValue + " €",
                paidValue + " €",
                (Objects.equals(this.getStatus().toString(), "SUCCESS") ? "Success" : "Cancelled")
        );
    }
}