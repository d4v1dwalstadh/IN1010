package legemidler;

public abstract class Legemiddel {
    public final String navn;
    public int pris;
    public final double mengdeVirkestoff;
    public final int id;
    private static int idTeller;

    // konstruktør
    protected Legemiddel(String navn, int pris, double mengdeVirkestoff) {
        this.navn = navn;
        this.pris = pris;
        this.mengdeVirkestoff = mengdeVirkestoff;
        this.id = idTeller++;
    }

    public String hentNavn() {
        return navn;
    }

    public int hentPris() {
        return pris;
    }

    public void settNyPris(int pris) {
        this.pris = pris;
    }

    @Override
    public String toString() {
        return  "ID=" + id +
                ", Navn=" + navn +
                ", Pris=" + pris +
                ", Mengde virkestoff=" + mengdeVirkestoff;
    }
}