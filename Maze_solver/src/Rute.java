import java.util.ArrayList;

abstract class Rute {
    protected int rad;
    protected int kol;
    protected Labyrint labyrint;

    protected int besøkNummer = 0;
    
    protected Rute nord, syd, øst, vest;

    public Rute(int rad, int kol, Labyrint labyrint) {
        this.rad = rad;
        this.kol = kol;
        this.labyrint = labyrint;
    }

    public void settNaboer(Rute nord, Rute syd, Rute vest, Rute øst) {
        this.nord = nord;
        this.syd = syd;
        this.øst = øst;
        this.vest = vest;
    }

    public int hentRad() {
        return rad;
    }

    public int hentKol() {
        return kol;
    }

    public int hentBesøknummer() {
        return besøkNummer;
    }

    public void settBesøkNummer(int nr) {
        besøkNummer = nr;
    }

    // subklasser tilegner egen versjon
    public  abstract void finn(Rute fra, ArrayList<Koordinat> sti);
}
