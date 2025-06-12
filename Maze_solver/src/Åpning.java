import java.util.ArrayList;

public class Åpning extends HvitRute {
    public Åpning(int rad, int kol, Labyrint labyrint) {
        super(rad, kol, labyrint);
    }

    @Override
    public void finn(Rute fra, ArrayList<Koordinat> sti) {
        if (besøkNummer != 0) return;

        // for nr stien
        besøkNummer = labyrint.nesteBesøkNummer();
        System.out.println("(" + rad + "," + kol + ")");

        // for array med utvei
        ArrayList<Koordinat> nySti = new ArrayList<>(sti);
        nySti.add(new Koordinat(rad, kol));
        labyrint.leggTilLøsning(nySti);
    }

    @Override
    public String toString() {
        if (besøkNummer == 0) return " . ";
        return String.format("%3d", besøkNummer);
    }
}
