package de.edvschuleplattling.rjertila.parkautomat;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Geldspeicher extends Pane
{
    @javafx.fxml.FXML
    private TextField txt10C;
    @javafx.fxml.FXML
    private TextField txt20C;
    @javafx.fxml.FXML
    private TextField txt2E;
    @javafx.fxml.FXML
    private TextField txt1E;
    @javafx.fxml.FXML
    private TextField txt50C;
    @javafx.fxml.FXML
    private TextField txt5E;
    @javafx.fxml.FXML
    private TextField txt20E;
    @javafx.fxml.FXML
    private TextField txt10E;
    @javafx.fxml.FXML
    private Label lblBetrag;


    public Geldspeicher() {
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("geldspeicher.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }

}