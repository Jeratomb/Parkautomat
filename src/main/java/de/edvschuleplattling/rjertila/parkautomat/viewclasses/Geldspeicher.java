package de.edvschuleplattling.rjertila.parkautomat.viewclasses;

import de.edvschuleplattling.rjertila.parkautomat.parkautomat.Geldmenge;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

/**
 * Diese Klasse wird verwendet, um die Geldmenge in der GUI anzuzeigen.
 * @author rjertila
 */
public class Geldspeicher extends AnchorPane {

    @javafx.fxml.FXML
    private GridPane grid;
    @javafx.fxml.FXML
    private TitledPane pane;
    @javafx.fxml.FXML
    private BetragLabel lblOutput;
    private Geldmenge geld;

    /**
     * Konstruktor für Geldspeicher.
     * @throws IOException Wenn ein Fehler beim Laden der FXML-Datei auftritt.
     */
    public Geldspeicher() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("geldspeicher.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            throw e;
        }
        geld = new Geldmenge();
        pane = new TitledPane();
        lblOutput = new BetragLabel();
    }

    /**
     * Setzt den Namen des TitledPane.
     * @param value Der neue Name des TitledPane.
     */
    public void setPaneName(String value) {
        pane.setText(value);
    }


    /**
     * Gibt den Namen des TitledPane zurück.
     * @return Der Name des TitledPane.
     */
    public String getPaneName() {
        return pane.getText();
    }


    /**
     * Aktualisiert die Werte der Geldmenge und der GUI.
     * @param gm Die neue Geldmenge.
     */
    public void updateValues(Geldmenge gm) {
        for (int i = 0; i < 8; i++) {
            geld.getMuenzen()[i] = gm.getMuenzen()[i];
            setNodeByRowColumnIndexText(i, grid, String.valueOf(gm.getMuenzen()[i]));
        }
        lblOutput.setValue((double) geld.getGesamt() / 100);
    }

    /**
     * Setzt den Text eines Nodes in einem GridPane anhand des Zeilen- und Spaltenindexes.
     * @param column Der Spaltenindex des Nodes.
     * @param gridPane Das GridPane, das den Node enthält.
     * @param text Der neue Text des Nodes.
     */
    public void setNodeByRowColumnIndexText(final int column, GridPane gridPane, String text) {
        final int row = 1;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column && node instanceof TextField n) {
                n.setText(text);
                break;
            }
        }

    }

}
