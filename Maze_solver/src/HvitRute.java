import java.util.ArrayList;

public class HvitRute extends Rute {
    public HvitRute(int rad, int kol, Labyrint labyrint) {
        super(rad, kol, labyrint);
    }

    @Override
    public void finn(Rute fra, ArrayList<Koordinat> sti) {
        if (besøkNummer != 0)
            return;

        besøkNummer = Labyrint.nesteBesøkNummer();

        ArrayList<Koordinat> nySti = new ArrayList<>(sti);
        nySti.add(new Koordinat(rad, kol));

        // Prøv å gå til alle naboer, unntatt der vi kom fra
        if (nord != null && nord != fra)
            nord.finn(this, nySti);
        if (syd != null && syd != fra)
            syd.finn(this, nySti);
        if (vest != null && vest != fra)
            vest.finn(this, nySti);
        if (øst != null && øst != fra)
            øst.finn(this, nySti);
    }

    @Override
    public String toString() {
        if (besøkNummer == 0) return " . ";
        return String.format("%3d", besøkNummer);
    }

}
