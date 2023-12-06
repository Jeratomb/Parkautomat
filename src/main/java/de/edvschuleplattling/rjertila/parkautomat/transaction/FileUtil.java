package de.edvschuleplattling.rjertila.parkautomat.transaction;

import de.edvschuleplattling.rjertila.parkautomat.exceptions.DataExportException;
import de.edvschuleplattling.rjertila.parkautomat.exceptions.DataImportException;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used for writing and reading to and from a given path
 * @author rjertila
 */
public class FileUtil {
    private static final String PATH = "data/protocol.csv";
    public static void saveTransaction(Transaktion transaction) throws DataExportException {
        try {
            File file = new File(PATH);

            file.getParentFile().mkdirs();
            file.createNewFile();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));) {
                bw.write(transaction.toCSV());
            } catch (IOException e) {
                e.printStackTrace();
                throw new DataExportException(PATH, e);
            }
        } catch (IOException e) {
            throw new DataExportException(PATH, e);
        }
    }

    public static List<Transaktion> getTransactions() throws DataImportException {
        int zeilenNr = 0;
        List<Transaktion> transactions = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                LocalDateTime zeit = LocalDateTime.parse(values[0], formatter);
                int betrag = Integer.parseInt(values[1]);
                int paid = Integer.parseInt(values[2]);
                Status status = Status.valueOf(values[3]);

                transactions.add(new Transaktion(betrag, paid, zeit, status));

                zeilenNr += 1;
            }
        } catch (IOException e) {
            throw new DataImportException(PATH, zeilenNr);
        }

        return transactions;
    }

    public static boolean exists() {
        return new File(PATH).exists();
    }

    public static void clearData() throws DataExportException {
        try {
            new FileWriter(PATH, false).close();
        } catch (IOException e) {
            throw new DataExportException(PATH, e);
        }
    }
}

