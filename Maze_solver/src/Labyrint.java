import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Labyrint {
    private Rute[][] ruter;
    private int antRader;
    private int antKolonner;
    protected static int veiTeller = 1;
    private ArrayList<ArrayList<Koordinat>> utveier;


    public Labyrint(File fil) throws FileNotFoundException {
        Scanner sc = new Scanner(fil);

        antRader = sc.nextInt();
        antKolonner = sc.nextInt();
        sc.nextLine();

        ruter = new Rute[antRader][antKolonner];

        for (int rad = 0; rad < antRader; rad++) { // Oppretter celler
            String linje = sc.nextLine();
            for (int kol = 0; kol < antKolonner; kol++) {
                char tegn = linje.charAt(kol);
                Rute nyRute;

                if (tegn == '#') {
                    nyRute = new SortRute(rad, kol, this);
                } else { // sjekker åpning
                    if (rad == 0 || rad == antRader - 1 || kol == 0 || kol == antKolonner - 1) {
                        nyRute = new Åpning(rad, kol, this);
                    } else {
                        nyRute = new HvitRute(rad, kol, this);
                    }
                }
                ruter[rad][kol] = nyRute;
            }
        }

        for (int rad = 0; rad < antRader; rad++) {
            for (int kol = 0; kol < antKolonner; kol++) {
                Rute nord = (rad > 0) ? ruter[rad - 1][kol] : null;
                Rute syd = (rad < antRader - 1) ? ruter[rad + 1][kol] : null;
                Rute øst = (kol < antKolonner - 1) ? ruter[rad][kol + 1] : null;
                Rute vest = (kol > 0) ? ruter[rad][kol - 1] : null;

                ruter[rad][kol].settNaboer(nord, syd, vest, øst);
            }
        }
        sc.close();
    }

    public void settRute(int rad, int kol, Rute rute) {
        ruter[rad][kol] = rute;
    }

    public Rute hentRute(int rad, int kol) {
        return ruter[rad][kol];
    }

    public int hentRader() {
        return antRader;
    }

    public int hentKolonner() {
        return antKolonner;
    }

    public ArrayList<ArrayList<Koordinat>> hentUtveier() {
        return utveier;
    }    

    public static void nullstillTeller() {
        veiTeller = 1;
    }

    public static int nesteBesøkNummer() {
        return veiTeller++;
    }

    private void nullstillBesøk() {
        for (int r = 0; r < antRader; r++) {
            for (int k = 0; k < antKolonner; k++) {
                ruter[r][k].settBesøkNummer(0);
            }
        }
        nullstillTeller();
    }    

    public void leggTilLøsning(ArrayList<Koordinat> løsning) {
        utveier.add(løsning);
    }
    

    public void finnUtveiFra(int rad, int kol) {
        utveier = new ArrayList<>();
        nullstillBesøk();

        Rute start = hentRute(rad, kol);

        if (start instanceof SortRute) {
            System.out.println("Du kan ikke starte i en sort rute.");
        } else {
            start.finn(null, new ArrayList<Koordinat>());
            System.out.println("\nHer er labyrinten slik du gikk gjennom den:\n");
            System.out.println(this); // Bruker toString med tallene
            System.out.println("\nUtveier:");
            int nr = 0;
            for (ArrayList<Koordinat> utvei : utveier) {
                System.out.println("Løsning nr. " + nr++ + " (" + utvei.size() +"):");
                for (Koordinat k : utvei) {
                    System.out.print(k + " ");
                }
                System.out.println("\n");
            }
            System.out.println(utveier.size() + " løsninger funnet");
        }
    }   

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ruter.length; i++) {
            for (int j = 0; j < ruter[0].length; j++) {
                sb.append(ruter[i][j].toString());
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}