package de.edvschuleplattling.rjertila.parkautomat.viewclasses;

import de.edvschuleplattling.rjertila.parkautomat.exceptions.DataExportException;
import de.edvschuleplattling.rjertila.parkautomat.exceptions.DataImportException;
import de.edvschuleplattling.rjertila.parkautomat.transaction.FileUtil;
import de.edvschuleplattling.rjertila.parkautomat.transaction.Transaktion;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Diese Klasse wird verwendet, um die GUI zu steuern und Benutzereingaben zu verarbeiten.
 * Sie behandelt auch die Geschäftslogik der Anwendung.
 * @author rjertila
 */
public class Uebersicht implements Initializable {
    @javafx.fxml.FXML
    private ListView<String> transaktionsListView;

    // Boolean-Flags für die Sortierreihenfolge
    private boolean sortTagAufsteigend = true;
    private boolean sortUhrzeitAufsteigend = true;
    private boolean sortBetragAufsteigend = true;
    private boolean sortBezahltAufsteigend = true;
    private boolean sortStatusAufsteigend = true;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (FileUtil.exists()) aktualisiereDaten();
    }

    public void onAktualisieren(ActionEvent actionEvent) {
        aktualisiereDaten();
    }

    public void onLeeren(ActionEvent actionEvent) {
        try {
            FileUtil.clearData();
        } catch (DataExportException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText(e.getMessage());
            a.setContentText(stackTraceToString(e.getStackTrace()));
            a.show();
        }
        transaktionsListView.getItems().clear();
    }

    public void aktualisiereDaten() {
        try {
            transaktionsListView.getItems().clear();
            transaktionsListView.getItems().addAll(getStrings());
        } catch (DataImportException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText(e.getMessage());
            a.setContentText(stackTraceToString(e.getStackTrace()));
            a.show();
        }
    }

    public void aktualisiereDaten(List<Transaktion> list) {
        transaktionsListView.getItems().clear();
        for (Transaktion i : list) {
            transaktionsListView.getItems().add(i.toListView());
        }
    }

    public List<String> getStrings() throws DataImportException {
        List<String> res = new ArrayList<>();
        List<Transaktion> tl = FileUtil.getTransactions();

        for (Transaktion i : tl) {
            res.add(i.toListView());
        }

        return res;
    }

    // Modifizierte Sortiermethoden
    public void sortiereTag() {
        sortiereNach("Tag", sortTagAufsteigend);
        sortTagAufsteigend = !sortTagAufsteigend; // Umschalten der Sortierreihenfolge
    }

    public void sortiereUhrzeit() {
        sortiereNach("Uhrzeit", sortUhrzeitAufsteigend);
        sortUhrzeitAufsteigend = !sortUhrzeitAufsteigend; // Umschalten der Sortierreihenfolge
    }

    public void sortiereBetrag() {
        sortiereNach("Betrag", sortBetragAufsteigend);
        sortBetragAufsteigend = !sortBetragAufsteigend; // Umschalten der Sortierreihenfolge
    }

    public void sortiereBezahlt() {
        sortiereNach("Bezahlt", sortBezahltAufsteigend);
        sortBezahltAufsteigend = !sortBezahltAufsteigend;
    }

    public void sortiereStatus() {
        sortiereNach("Status", sortStatusAufsteigend);
        sortStatusAufsteigend = !sortStatusAufsteigend; // Umschalten der Sortierreihenfolge
    }

    // Aktualisierte sortiereNach Methode
    private void sortiereNach(String kriterium, boolean aufsteigend) {
        try {
            List<Transaktion> transaktionen = FileUtil.getTransactions();
            Comparator<Transaktion> comparator;

            switch (kriterium) {
                case "Tag":
                    comparator = Comparator.comparing(t -> t.getZeit().toLocalDate());
                    break;
                case "Uhrzeit":
                    comparator = Comparator.comparing(t -> t.getZeit().toLocalTime());
                    break;
                case "Betrag":
                    comparator = Comparator.comparing(Transaktion::getBetrag);
                    break;
                case "Bezahlt":
                    comparator = Comparator.comparing(Transaktion::getPaid);
                    break;
                case "Status":
                    comparator = Comparator.comparing(Transaktion::getStatus);
                    break;
                default:
                    return;
            }

            if (!aufsteigend) {
                comparator = comparator.reversed();
            }

            transaktionen.sort(comparator);
            aktualisiereDaten(transaktionen);
        } catch (DataImportException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText(e.getMessage());
            a.show();
        }
    }

    private String stackTraceToString(StackTraceElement[] stack) {
        StringBuilder b = new StringBuilder();

        if (stack.length == 0)
            return "[]";

        for (StackTraceElement i : stack) {
            b.append(i.toString());
            b.append("\n");
        }

        return b.toString();
    }
}