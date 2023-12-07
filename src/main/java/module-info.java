module de.edvschuleplattling.rjertila.parkautomat {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires com.opencsv;
    requires java.logging;


    opens de.edvschuleplattling.rjertila.parkautomat to javafx.fxml;
    exports de.edvschuleplattling.rjertila.parkautomat;
    exports de.edvschuleplattling.rjertila.parkautomat.viewclasses;
    opens de.edvschuleplattling.rjertila.parkautomat.viewclasses to javafx.fxml;
}