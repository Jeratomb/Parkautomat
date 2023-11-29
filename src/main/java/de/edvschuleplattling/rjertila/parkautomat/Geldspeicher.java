package de.edvschuleplattling.rjertila.parkautomat;

import de.edvschuleplattling.rjertila.parkautomat.parkautomat.Geldmenge;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.*;

public class Geldspeicher extends Pane implements Initializable {
    @FXML
    private GridPane grid;
    private final static NumberFormat EUR = NumberFormat.getCurrencyInstance(new Locale("de","DE"));

    public Geldspeicher() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("geldspeicher.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setGeldmenge(new Geldmenge());
    }

    /**
     * Liest Die daten aus den Textfeldern und returned ein neues Object der Klasse Geldmenge.
     * Aenderungen an diesem Object werden nicht in UI uebernommen
     * fuer anderungen die methode setGeldmenge() benutzen
     * @return new Geldmenge
     */
    public Geldmenge getGeldmengeUnmodifiable(){
        List<Integer> ints = new ArrayList<>(grid.getChildren()
                .stream()
                .filter(o -> o instanceof TextField)
                .mapToInt(t -> parseOderZerro(((TextField) t).getText()))
                .boxed()
                .toList());
        //Collections.reverse(ints);
        return new Geldmenge(ints.stream().mapToInt(Integer::intValue).toArray());
    }

    /**
     * Setzy
     * @param
     */
    public void setGeldmenge(Geldmenge gm){
        for(Node n: grid.getChildren()){
            if(n != null && GridPane.getRowIndex(n) != null && GridPane.getRowIndex(n) == 1){
                if(n instanceof TextField){
                    ((TextField) n).setText(Integer.toString(gm.getAnzahl(Arrays.stream(Geldmenge.GELDSTUECK_ARTEN).sorted().toList().get(GridPane.getColumnIndex(n)))));
                }
            }
        }
        getLabelGesammt().setText(EUR.format(gm.getBetrag() / 100f));
    }

    public String getTitle(){
        return ((TitledPane)(this.getChildren().get(0))).getText();
    }

    public void setTitle(String text){
        ((TitledPane)(this.getChildren().get(0))).setText(text);
    }

    private static int parseOderZerro(String s){
        try{
            return Integer.parseInt(s);
        } catch (NumberFormatException e){
            return 0;
        }
    }

    private Label getLabelGesammt(){
        for(Node n : grid.getChildren()){
            if(GridPane.getRowIndex(n) == 0) continue;
            if(n instanceof Label) return (Label)n;
        }
        throw new RuntimeException("Textfeld Gesammt Nicht gefunden");
    }
}
