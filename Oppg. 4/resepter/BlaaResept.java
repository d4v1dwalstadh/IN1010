package resepter;

import legemidler.Legemiddel;
import personer.*;

public class BlaaResept extends Resept {
    public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
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

