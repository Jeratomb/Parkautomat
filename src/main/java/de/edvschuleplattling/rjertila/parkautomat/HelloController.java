// HelloController.java
package de.edvschuleplattling.rjertila.parkautomat;

import de.edvschuleplattling.rjertila.parkautomat.parkautomat.Geldmenge;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    private Geldmenge gezahlt = new Geldmenge();
    @FXML
    private Label lblBetrag;
    @FXML
    private Button btnRndm;
    @FXML
    private Button btn10c;
    @FXML
    private Button btn10E;
    @FXML
    private Button btn5E;
    @FXML
    private Button btn2E;
    @FXML
    private Button btn1E;
    @FXML
    private Button btn50c;
    @FXML
    private Button btn20c;
    @FXML
    private Button btnFertig;
    @FXML
    private Button btn20E;
    @FXML
    private Geldspeicher gezahltPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO: -> getting Nullpointerexception here
        gezahltPane.setGeldspeicher(new Geldmenge());
    }

    @FXML
    public void onAdd10C(ActionEvent actionEvent) {
        gezahlt.plusGeld(new Geldmenge(1, 0, 0, 0, 0, 0, 0, 0));
        updateGeldspeicher();
    }

    @FXML
    public void onRnd(ActionEvent actionEvent) {
        Random random = new Random();
        double randomBetrag = 1 + random.nextDouble() * 19; // Zwischen 1 und 20
        lblBetrag.setText(String.format("%.2f €", randomBetrag));
    }

    @FXML
    public void onAdd10E(ActionEvent actionEvent) {
        gezahlt.plusGeld(new Geldmenge(0, 0, 0, 0, 1, 0, 0, 0));
        updateGeldspeicher();
    }

    @FXML
    public void onAdd5E(ActionEvent actionEvent) {
        gezahlt.plusGeld(new Geldmenge(0, 0, 0, 0, 0, 1, 0, 0));
        updateGeldspeicher();
    }

    @FXML
    public void onAdd2E(ActionEvent actionEvent) {
        gezahlt.plusGeld(new Geldmenge(0, 0, 0, 0, 0, 0, 1, 0));
        updateGeldspeicher();
    }

    @FXML
    public void onAdd1E(ActionEvent actionEvent) {
        gezahlt.plusGeld(new Geldmenge(0, 0, 0, 0, 0, 0, 0, 1));
        updateGeldspeicher();
    }

    @FXML
    public void onAdd50C(ActionEvent actionEvent) {
        gezahlt.plusGeld(new Geldmenge(0, 0, 1, 0, 0, 0, 0, 0));
        updateGeldspeicher();
    }

    @FXML
    public void onAdd20C(ActionEvent actionEvent) {
        gezahlt.plusGeld(new Geldmenge(0, 1, 0, 0, 0, 0, 0, 0));
        updateGeldspeicher();
    }

    @FXML
    public void onFinish(ActionEvent actionEvent) {
        // Implementiere die Aktion, die beim Klicken auf "Fertig" ausgeführt werden soll
    }

    @FXML
    public void onAdd20E(ActionEvent actionEvent) {
        gezahlt.plusGeld(new Geldmenge(0, 0, 0, 0, 0, 0, 0, 1));
        updateGeldspeicher();
    }

    //TODO: Update label
    private void updateGeldspeicher() {
        gezahltPane.setGeldspeicher(gezahlt);
        gezahltPane.setLabel(String.format("%.2f €", gezahlt.getBetrag() / 100.0));
    }
}
