// HelloController.java
package de.edvschuleplattling.rjertila.parkautomat;

import de.edvschuleplattling.rjertila.parkautomat.exceptions.DataExportException;
import de.edvschuleplattling.rjertila.parkautomat.exceptions.DataImportException;
import de.edvschuleplattling.rjertila.parkautomat.exceptions.SprachFilter;
import de.edvschuleplattling.rjertila.parkautomat.exceptions.WechselGeldException;
import de.edvschuleplattling.rjertila.parkautomat.fehlerlog.FehlerLogger;
import de.edvschuleplattling.rjertila.parkautomat.parkautomat.Geldmenge;
import de.edvschuleplattling.rjertila.parkautomat.parkautomat.Kasse;
import de.edvschuleplattling.rjertila.parkautomat.transaction.FileUtil;
import de.edvschuleplattling.rjertila.parkautomat.transaction.Status;
import de.edvschuleplattling.rjertila.parkautomat.transaction.Transaktion;
import de.edvschuleplattling.rjertila.parkautomat.viewclasses.BetragLabel;
import de.edvschuleplattling.rjertila.parkautomat.viewclasses.Geldspeicher;
import de.edvschuleplattling.rjertila.parkautomat.viewclasses.Uebersicht;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Diese Klasse wird verwendet, um die GUI zu steuern und Benutzereingaben zu verarbeiten.
 * Sie behandelt auch die Geschäftslogik der Anwendung.
 *
 * @author rjertila
 */
public class HelloController implements Initializable {

    @FXML
    private BetragLabel txtValue;
    @FXML
    private Button btnBetrag;
    @FXML
    private Geldspeicher gmBestand;
    @FXML
    private Geldspeicher gmGezahlt;
    @FXML
    private Geldspeicher gmRueckgeld;
    @FXML
    private Button btn10c;
    @FXML
    private Button btn20c;
    @FXML
    private Button btn50c;
    @FXML
    private Button btn1Euro;
    @FXML
    private Button btn2Euro;
    @FXML
    private Button btn5Euro;
    @FXML
    private Button btn10Euro;
    @FXML
    private Button btn20Euro;
    @FXML
    private ChoiceBox<String> languageChoiceBox;

    private Button[] btns;

    private Kasse kasse;
    private Geldmenge temp;

    private double randomValue;
    private final FehlerLogger logger = new FehlerLogger();

    private Uebersicht uebersichtController;

    /**
     * Setzt den Uebersicht-Controller.
     *
     * @param uebersichtListController Der Uebersicht-Controller.
     */
    public void setUebersichtListController(Uebersicht uebersichtListController) {
        this.uebersichtController = uebersichtListController;
    }


    /**
     * Initialisiert die GUI und lädt die Anfangsdaten.
     *
     * @param url            Der Pfad zur FXML-Datei.
     * @param resourceBundle Die Ressourcen für die Lokalisierung.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtValue.setTextValue("00.00 €");


        btns = new Button[]{btn10c, btn20c, btn50c, btn1Euro, btn2Euro, btn5Euro, btn10Euro, btn20Euro};

        try {
            kasse = new Kasse(dateiEinlesen());
            gmBestand.updateValues(kasse.getGeldmenge());
        } catch (DataImportException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle(SprachFilter.getMessage("errorTitle"));
            a.setContentText(SprachFilter.getMessage("dataImportException"));
            a.show();
        }
        temp = new Geldmenge();
        // Initialisieren der ChoiceBox mit den unterstützten Sprachen
        languageChoiceBox.getItems().addAll("Deutsch", "English", "Français", "العربية");
        languageChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case "Deutsch":
                    SprachFilter.setLocale(Locale.GERMAN);
                    break;
                case "English":
                    SprachFilter.setLocale(Locale.ENGLISH);
                    break;
                case "Français":
                    SprachFilter.setLocale(Locale.FRENCH);
                    break;
                case "العربية":
                    SprachFilter.setLocale(new Locale("ar"));
                    break;
            }
        });
        languageChoiceBox.getSelectionModel().selectFirst();
    }

    /**
     * Liest die Anfangsdaten aus einer Datei ein.
     *
     * @return Die eingelesene Geldmenge.
     * @throws DataImportException Wenn ein Fehler beim Einlesen der Datei auftritt.
     */
    private Geldmenge dateiEinlesen() throws DataImportException {
        String pfad = "data/init.csv";
        Geldmenge res;
        int zeilenNr = 1;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pfad));
            String[] tokens = reader.readLine().split(",");
            int[] ints = new int[tokens.length];
            for (int i = 0; i < tokens.length; i++) {
                ints[i] = Integer.parseInt(tokens[i]);
                zeilenNr++;
            }
            res = new Geldmenge(ints);
        } catch (IOException e) {
            throw new DataImportException(pfad, zeilenNr);
        }
        return res;
    }

    /**
     * Wird aufgerufen, wenn der Benutzer auf den "Betrag" Button klickt.
     * Generiert einen zufälligen Betrag und aktualisiert die GUI.
     *
     * @param actionEvent Das ausgelöste Ereignis.
     */
    @FXML
    public void onBetrag(ActionEvent actionEvent) {
        double res = 0;
        int max = 100;
        Random rand = new Random();
        int randomInt = rand.nextInt(max + 1);
        res = randomInt * 0.10;
        txtValue.setValue(res);
        randomValue = res;
        activateButtons();
        gmGezahlt.updateValues(new Geldmenge());
        gmRueckgeld.updateValues(new Geldmenge());
    }

    @FXML
    public void handle10c(ActionEvent actionEvent) {
        updateMoney(new Geldmenge(1, 0, 0, 0, 0, 0, 0, 0));
    }

    @FXML
    public void handle20c(ActionEvent actionEvent) {
        updateMoney(new Geldmenge(0, 1, 0, 0, 0, 0, 0, 0));
    }

    @FXML
    public void handle50c(ActionEvent actionEvent) {
        updateMoney(new Geldmenge(0, 0, 1, 0, 0, 0, 0, 0));
    }

    @FXML
    public void handle1Euro(ActionEvent actionEvent) {
        updateMoney(new Geldmenge(0, 0, 0, 1, 0, 0, 0, 0));
    }

    @FXML
    public void handle2Euro(ActionEvent actionEvent) {
        updateMoney(new Geldmenge(0, 0, 0, 0, 1, 0, 0, 0));
    }

    @FXML
    public void handle5Euro(ActionEvent actionEvent) {
        updateMoney(new Geldmenge(0, 0, 0, 0, 0, 1, 0, 0));
    }

    @FXML
    public void handle10Euro(ActionEvent actionEvent) {
        updateMoney(new Geldmenge(0, 0, 0, 0, 0, 0, 1, 0));
    }

    @FXML
    public void handle20Euro(ActionEvent actionEvent) {
        updateMoney(new Geldmenge(0, 0, 0, 0, 0, 0, 0, 1));
    }

    /**
     * Aktualisiert die gezahlte Geldmenge und die GUI.
     *
     * @param amount Die hinzuzufügende Geldmenge.
     */
    private void updateMoney(Geldmenge amount) {
        temp.addiereGeld(amount);
        gmGezahlt.updateValues(temp);
        double totalAmount = temp.getGesamt();
        double res = (randomValue * 100 - totalAmount) / 100;
        if (res <= 0) {
            res = 0;
            deactivateButtons();
            handleFinish(null);
        }
        txtValue.setValue(res);
        uebersichtController.aktualisiereDaten();
    }

    /**
     * Deaktiviert alle Geld-Buttons.
     */
    private void deactivateButtons() {
        for (Button i : btns) {
            i.setDisable(true);
        }
    }

    /**
     * Aktiviert alle Geld-Buttons.
     */
    private void activateButtons() {
        for (Button i : btns) {
            i.setDisable(false);
        }
    }

    /**
     * Wird aufgerufen, wenn der Benutzer auf den "Fertig" Button klickt.
     * Führt die Bezahlung durch und aktualisiert die GUI.
     *
     * @param actionEvent Das ausgelöste Ereignis.
     */
    @FXML
    public void handleFinish(ActionEvent actionEvent) {
        Geldmenge change = null;
        Status curStatus = Status.SUCCESS;
        try {
            change = kasse.bezahle((int) (randomValue * 100), temp);

            gmBestand.updateValues(kasse.getGeldmenge());
            gmGezahlt.updateValues(temp);
            gmRueckgeld.updateValues(change);
        } catch (WechselGeldException e) {
            logger.log(e);
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle(SprachFilter.getMessage("errorTitle"));
            a.setContentText(SprachFilter.getMessage("wechselGeldException"));
            a.show();
            curStatus = Status.CANCELED;
        } finally {
            Transaktion transaction = new Transaktion((int) (randomValue * 100), temp.getGesamt(), LocalDateTime.now(), curStatus);
            try {
                FileUtil.saveTransaction(transaction);
            } catch (DataExportException e) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle(SprachFilter.getMessage("errorTitle"));
                a.setContentText(SprachFilter.getMessage("dataExportException"));
                a.show();
            }
        }

        txtValue.updateTextValue();
        temp.leer();
        txtValue.setValue(0);
    }
}