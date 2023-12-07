package de.edvschuleplattling.rjertila.parkautomat;

import de.edvschuleplattling.rjertila.parkautomat.viewclasses.Uebersicht;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Diese Klasse wird verwendet, um die Anwendung zu starten.
 * Sie lädt das Hauptfenster und das Transaktionslistenfenster und stellt die Verbindung zwischen den Controllern her.
 * @author rjertila
 */
public class HelloApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        // Laden des Hauptfensters
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Parkautomat");
        stage.setScene(scene);
        stage.show();

        // Laden des Transaktionslistenfensters
        FXMLLoader uebersichtLoader = new FXMLLoader(Uebersicht.class.getResource("uebersicht.fxml"));
        Scene uebersichtScene = new Scene(uebersichtLoader.load());
        Stage uebersichtStage = new Stage();
        uebersichtStage.setTitle("Übersicht");
        uebersichtStage.setScene(uebersichtScene);
        uebersichtStage.show();

        // Abrufen der Controller aus den Loadern
        HelloController mainController = fxmlLoader.getController();
        Uebersicht uebersichtController = uebersichtLoader.getController();

        // Setzen des Uebersicht-Controllers in HelloController
        mainController.setUebersichtListController(uebersichtController);

        // Behandlung des Schließens der Hauptbühne
        stage.setOnCloseRequest(e -> {
            uebersichtStage.close();
        });

        // Positionierung
        stage.setX(200);
        uebersichtStage.setX(800);
    }
}