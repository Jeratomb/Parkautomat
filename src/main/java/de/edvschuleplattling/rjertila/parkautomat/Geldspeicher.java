package de.edvschuleplattling.rjertila.parkautomat;

        import de.edvschuleplattling.rjertila.parkautomat.parkautomat.Geldmenge;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.control.Label;
        import javafx.scene.control.TextField;
        import javafx.scene.layout.Pane;

        import java.io.IOException;

public class Geldspeicher extends Pane {
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

    private final TextField[] iterFields;

    public Geldspeicher() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("geldspeicher.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
            iterFields = new TextField[]{txt10C, txt20C, txt50C, txt1E, txt2E, txt5E, txt10E, txt20E};
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void setGeldspeicher(Geldmenge g) {
        var data = g.getSpeicher();
        for (int i = 0; i < 8; i++) {
            iterFields[i].setText(String.valueOf(data[i]));
        }
    }

    public Geldmenge getGeldspeicher(){
        return new Geldmenge(
                Integer.parseInt(txt10C.getText()),
                Integer.parseInt(txt20C.getText()),
                Integer.parseInt(txt50C.getText()),
                Integer.parseInt(txt1E.getText()),
                Integer.parseInt(txt2E.getText()),
                Integer.parseInt(txt5E.getText()),
                Integer.parseInt(txt10E.getText()),
                Integer.parseInt(txt20E.getText())
        );
    }

    public void setLabel(String data) {
        lblBetrag.setText(data);
    }
}
