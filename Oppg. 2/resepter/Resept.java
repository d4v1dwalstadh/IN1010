package resepter;

import legemidler.Legemiddel;
import personer.Lege;

public abstract class Resept {
    protected final Legemiddel legemiddel;
    protected final int id;
    private static int idTeller;
    protected final Lege utskrivendeLege;
    protected final int pasientId;
    protected int reit;
    
    // konstruktør
    protected Resept(Legemiddel legemiddel, Lege lege, int pasientId, int reit) {
        this.legemiddel = legemiddel;
        this.id = idTeller++;
        this.utskrivendeLege = lege;
        this.pasientId = pasientId;
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
        return pasientId;
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
        return "Resept: " +
                "ID=" + id +
                ", Legemiddel=" + legemiddel.navn +
                ", Lege=" + utskrivendeLege.hentNavn() +
                ", Pasient ID=" + pasientId +
                ", Reit=" + reit +
                ", Farge=" + farge() +
                ", Pris å betale=" + prisAaBetale();
    }
}   
