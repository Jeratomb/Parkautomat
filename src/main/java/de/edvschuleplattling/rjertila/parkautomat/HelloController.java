// HelloController.java
package de.edvschuleplattling.rjertila.parkautomat;

import de.edvschuleplattling.rjertila.parkautomat.exceptions.KeinPassendesRueckgeldException;
import de.edvschuleplattling.rjertila.parkautomat.parkautomat.Geldmenge;
import de.edvschuleplattling.rjertila.parkautomat.parkautomat.Kasse;
import de.edvschuleplattling.rjertila.parkautomat.protokoll.Protokolierer;
import de.edvschuleplattling.rjertila.parkautomat.transaction.Transaktion;
import de.edvschuleplattling.rjertila.parkautomat.transaction.TransaktionsStatus;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

public class HelloController implements Initializable {

    @FXML
    private Button btnRandom;
    @FXML
    private Geldspeicher gmPaneBestand;
    @FXML
    private HBox boxButtons;
    @FXML
    private Geldspeicher gmPaneGezahlt;
    @FXML
    private Geldspeicher gmPaneRueckgeld;
    @FXML
    private Label lblBetrag;

    private final NumberFormat EUR = NumberFormat.getCurrencyInstance(new Locale("de","DE"));
    private Kasse kasse;
    private int zuZahlen;

    public void initialize(URL url, ResourceBundle resourceBundle) {

        randBetrag();

        try{
            kasse = new Kasse(new Geldmenge(Arrays.stream(Files.readAllLines(new File("init.csv").toPath())
                            .get(0)
                            .split(","))
                    .mapToInt(Integer::parseInt)
                    .toArray()));
        } catch (Exception e){
            throw new RuntimeException("Fehler beim lesen der datei " + e.toString());
        }

        gmPaneBestand.setGeldmenge(kasse.getInhalt());

        for(Button b: boxButtons.getChildren().stream().map(n -> (Button)n).toList()){
            b.setOnAction(actionEvent -> {
                try {
                    onClickBetrag(
                            Arrays.stream(Geldmenge.GELDSTUECK_ARTEN)
                                    .sorted()
                                    .toList()
                                    .get(boxButtons.getChildren().indexOf(b))
                    );
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    @FXML
    public void onClickRandom(ActionEvent actionEvent) {
        randBetrag();
    }

    public void randBetrag(){
        gmPaneRueckgeld.setGeldmenge(new Geldmenge());
        gmPaneGezahlt.setGeldmenge(new Geldmenge());
        setButtonDisabled(false);
        btnRandom.setDisable(true);

        zuZahlen = ThreadLocalRandom.current().nextInt(10,201) * 10;
        lblBetrag.setText(EUR.format(zuZahlen/100f));
    }
    private void onClickBetrag(int betrag) throws IOException {
        Geldmenge gm = gmPaneGezahlt.getGeldmengeUnmodifiable();
        gm.addEins(betrag);
        gmPaneGezahlt.setGeldmenge(gm);
        int restbetrag = zuZahlen - gm.getBetrag();
        if(restbetrag <= 0){
            lblBetrag.setText(EUR.format(0f));
            try {
                gmPaneRueckgeld.setGeldmenge(kasse.bezahle(zuZahlen,gm));
                gmPaneBestand.setGeldmenge(kasse.getInhalt());
                Protokolierer.schreibeTransaktion(new Transaktion(
                        kasse,
                        gmPaneGezahlt.getGeldmengeUnmodifiable(),
                        gmPaneRueckgeld.getGeldmengeUnmodifiable(),
                        zuZahlen,
                        TransaktionsStatus.Erfolgreich
                ));

            } catch (KeinPassendesRueckgeldException e){
                Protokolierer.schreibeTransaktion(new Transaktion(
                        kasse,
                        gmPaneGezahlt.getGeldmengeUnmodifiable(),
                        null,
                        zuZahlen,
                        TransaktionsStatus.KeinWechselgeld
                ));

                gmPaneRueckgeld.setGeldmenge(gm);
                gmPaneGezahlt.setGeldmenge(new Geldmenge());

                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setTitle("Fehler");
                a.setHeaderText("Parkautomat hat kein passendes Wechselgeld mehr");
                a.setContentText("Bitte entnehmen Sie ihr Geld.\nSie können den Betrag passend einwerfen, oder einen anderen Parkautomaten aufsuchen");
                a.showAndWait();
            } catch (Exception e){
                System.err.println(e);
                //TODO handling
            }
            btnRandom.setDisable(false);
            setButtonDisabled(true);
        }
        else{
            lblBetrag.setText(EUR.format(restbetrag / 100f));
        }
    }


    private void setButtonDisabled(boolean state){
        boxButtons.getChildren().forEach(b -> b.setDisable(state));
    }
}
