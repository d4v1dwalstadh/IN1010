public class Koordinat {
    int rad, kol;

    public Koordinat(int r, int k) {
        this.rad = r;
        this.kol = k;
    }

    public int hentRader() {
        return rad;
    }

    public int hentKolonner() {
        return kol;
    }

    @Override
    public String toString() {
        return "(" + rad + "," + kol + ")";
    }
}

