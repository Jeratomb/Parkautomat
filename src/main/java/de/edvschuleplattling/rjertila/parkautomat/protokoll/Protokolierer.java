package de.edvschuleplattling.rjertila.parkautomat.protokoll;

import com.opencsv.CSVWriter;
import de.edvschuleplattling.rjertila.parkautomat.transaction.Transaktion;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Protokolierer {

    private final static String PFAD = "src/main/java/de/edvschuleplattling/rjertila/parkautomat/protokoll/protokoll.csv";

    public static void schreibeTransaktion(Transaktion t) throws IOException {
        try (CSVWriter w = new CSVWriter(new FileWriter(PFAD))) {
        //TODO write into file with line numbers
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
