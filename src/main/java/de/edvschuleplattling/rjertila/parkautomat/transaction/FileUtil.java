package de.edvschuleplattling.rjertila.parkautomat.transaction;

import de.edvschuleplattling.rjertila.parkautomat.exceptions.DataExportException;
import de.edvschuleplattling.rjertila.parkautomat.exceptions.DataImportException;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse wird verwendet, um Transaktionen zu speichern und zu lesen.
 * Sie bietet Methoden zum Speichern einer Transaktion, zum Abrufen aller Transaktionen,
 * zum Überprüfen, ob eine Datei existiert, und zum Löschen aller Daten.
 * Die Daten werden in einer CSV-Datei im Pfad "data/protocol.csv" gespeichert.
 * @author rjertila
 */
public class FileUtil {
    private static final String PATH = "data/protocol.csv";

    /**
     * Speichert eine Transaktion in der Datei.
     * @param transaction Die zu speichernde Transaktion.
     * @throws DataExportException Wenn beim Speichern der Transaktion ein Fehler auftritt.
     */
    public static void saveTransaction(Transaktion transaction) throws DataExportException {
        try {
            File file = new File(PATH);

            file.getParentFile().mkdirs();
            file.createNewFile();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
                bw.write(transaction.toCSV());
            } catch (IOException e) {
                e.printStackTrace();
                throw new DataExportException(PATH, e);
            }
        } catch (IOException e) {
            throw new DataExportException(PATH, e);
        }
    }

    /**
     * Liest alle Transaktionen aus der Datei.
     * @return Eine Liste aller Transaktionen.
     * @throws DataImportException Wenn beim Lesen der Transaktionen ein Fehler auftritt.
     */
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

    /**
     * Überprüft, ob die Datei existiert.
     * @return true, wenn die Datei existiert, sonst false.
     */
    public static boolean exists() {
        return new File(PATH).exists();
    }

    /**
     * Löscht alle Daten aus der Datei.
     * @throws DataExportException Wenn beim Löschen der Daten ein Fehler auftritt.
     */
    public static void clearData() throws DataExportException {
        try {
            new FileWriter(PATH, false).close();
        } catch (IOException e) {
            throw new DataExportException(PATH, e);
        }
    }
}