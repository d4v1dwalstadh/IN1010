package resepter;

import legemidler.Legemiddel;
import personer.Lege;

public class BlaaResept extends Resept {
    public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }

    @Override
    public String farge() {
        return "blå";
    }

    @Override
    public int prisAaBetale() {
        return StrictMath.round((legemiddel.hentPris() / 4));
    }
}

