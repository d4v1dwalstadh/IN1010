import java.io.*;

public class TestFrekvenstabell {
    public static void main(String[] args) {
        String fil1 = "data/TestData/ikke_smittet.txt"; // Erstatt med faktisk filnavn
        String fil2 = "data/TestData/smittet.txt"; // Erstatt med faktisk filnavn
        String utFil = "data/flettet.txt"; // Filen som skal inneholde det flettede resultatet

        // Les frekvenstabeller fra filene
        Frekvenstabell f1 = lesFraFil(fil1);
        Frekvenstabell f2 = lesFraFil(fil2);

        // Flett frekvenstabellene
        Frekvenstabell flettet = Frekvenstabell.flett(f1, f2);

        // Skriv det flettede resultatet til en fil
        flettet.skrivTilFil(utFil);

        // Skriv ut resultatet til konsollen for verifisering
        System.out.println("Flettet frekvenstabell:");
        System.out.println(flettet);
    }

    // Metode for å lese en Frekvenstabell fra en fil
    private static Frekvenstabell lesFraFil(String filnavn) {
        Frekvenstabell tabell = new Frekvenstabell();
        try (BufferedReader reader = new BufferedReader(new FileReader(filnavn))) {
            String linje;
            while ((linje = reader.readLine()) != null) {
                String[] deler = linje.split(" ");
                if (deler.length == 2) {
                    String subsekvens = deler[0];
                    int frekvens = Integer.parseInt(deler[1]);
                    tabell.put(subsekvens, frekvens);
                }
            }
        } catch (IOException e) {
            System.err.println("Feil ved lesing av fil: " + filnavn);
            e.printStackTrace();
        }
        return tabell;
    }
}
