package resepter;
import legemidler.Legemiddel;
import personer.*;


public abstract class Resept {
    protected final Legemiddel legemiddel;
    protected final int id;
    private static int idTeller;
    protected final Lege utskrivendeLege;
    protected final Pasient pasient;
    protected int reit;
    
    // konstruktør
    protected Resept(Legemiddel legemiddel, Lege lege, Pasient pasient, int reit) {
        this.legemiddel = legemiddel;
        this.id = idTeller++;
        this.utskrivendeLege = lege;
        this.pasient = pasient;
        this.reit = reit;
    }

    public int hentId() {
        return id;
    }

    public Legemiddel hentLegemiddel() {
        return legemiddel;
    }

    public Lege hentLege() {
        return utskrivendeLege;
    }

    public int hentPasientId() {
        return pasient.hentId();
    }

    public int hentReit() {
        return reit;
    }

    public boolean bruk() {
        if (reit != 0) {
            reit--; 
            return true;
        }
        return false;   // resepten er brukt opp
    }

    public abstract String farge();
    public abstract int prisAaBetale();

    @Override
    public String toString() {
        return  "ID=" + id +
                ", Legemiddel=" + legemiddel.navn +
                ", Lege=" + utskrivendeLege.hentNavn() +
                ", Pasient ID=" + pasient.hentId() +
                ", Reit=" + reit +
                ", Farge=" + farge() +
                ", Pris å betale=" + prisAaBetale();
    }
}   
