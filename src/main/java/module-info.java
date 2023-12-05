module de.edvschuleplattling.rjertila.parkautomat {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires com.opencsv;


    opens de.edvschuleplattling.rjertila.parkautomat to javafx.fxml;
    exports de.edvschuleplattling.rjertila.parkautomat;
}