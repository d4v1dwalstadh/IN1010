import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class KlargjørData {
    private static final int ANTALL_TRÅDER = 8;

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Bruk: java KlargjørData <metadata-fil>");
            return;
        }

        String filnavn = args[0];
        File fil = new File(filnavn);
        String mappe = fil.getParent() + "/";

        // String filnavn = "data/TestData/metadata.csv";
        // File fil = new File(filnavn);
        // String mappe = fil.getParent() + "/";

        Monitor smittetMonitor = new Monitor();
        Monitor ikkeSmittetMonitor = new Monitor();

        ArrayList<Thread> lesetråder = new ArrayList<>();
        // Les metadata.csv og opprett Lesetråd for hver fil
        try (Scanner scanner = new Scanner(new File(filnavn))) {      // scanner enkelt for å håndtere skilletegn
            while (scanner.hasNextLine()) {
                String linje = scanner.nextLine();
                String[] deler = linje.split(",");
                if (deler.length < 2) continue;

                String dataFil = mappe + deler[0];
                String kategori = deler[1];

                Monitor valgtMonitor = kategori.equals("True") ? smittetMonitor : ikkeSmittetMonitor;
                Thread lt = new Thread(new Lesetråd(dataFil, valgtMonitor));
                lesetråder.add(lt);
                lt.start();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Finner ikke metadatafilen: " + filnavn);
            return;
        }
        // Vent på at alle lesetråder er ferdige
        for (Thread t : lesetråder) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        // Start flettetråder for begge monitorene
        startFlettetråder(smittetMonitor);
        startFlettetråder(ikkeSmittetMonitor);

        // Hent de ferdige frekvenstabellene
        Frekvenstabell smittetResultat = smittetMonitor.taUt();
        Frekvenstabell ikkeSmittetResultat = ikkeSmittetMonitor.taUt();

        // Skriv resultatene til filer
        skrivTilFil(mappe + "smittet", smittetResultat);
        skrivTilFil(mappe + "ikke_smittet", ikkeSmittetResultat);
    }

    private static void startFlettetråder(Monitor monitor) {
        ArrayList<Thread> flettetråder = new ArrayList<>();
        for (int i = 0; i < ANTALL_TRÅDER; i++) {
            Thread ft = new Thread(new Flettetråd(monitor));
            flettetråder.add(ft);
            ft.start();
        }
    
        for (Thread t : flettetråder) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    
    }

    private static void skrivTilFil(String filnavn, Frekvenstabell tabell) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filnavn))) {
            writer.write(tabell.toString());
        } catch (IOException e) {
            System.out.println("Feil ved skriving til fil: " + filnavn);
        }
    }
    
}
