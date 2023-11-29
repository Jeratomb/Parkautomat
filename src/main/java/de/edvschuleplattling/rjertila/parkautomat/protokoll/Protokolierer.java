package de.edvschuleplattling.rjertila.parkautomat.protokoll;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import de.edvschuleplattling.rjertila.parkautomat.transaction.Transaktion;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Protokolierer {

    private final static String PFAD = "src/main/java/de/edvschuleplattling/rjertila/parkautomat/protokoll/protokoll.json";

    public static void schreibeTransaktion(Transaktion t) throws IOException {
        try(FileReader r = new FileReader(PFAD)) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Transaktion>>() {}.getType();
            List<Transaktion> transaktionList = gson.fromJson(r, listType);
            if(transaktionList == null) transaktionList = new ArrayList<>();
            transaktionList.add(t);
            try(FileWriter w = new FileWriter(PFAD)) {
                for(Transaktion x : transaktionList) {
                    gson.toJson(x, w);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}