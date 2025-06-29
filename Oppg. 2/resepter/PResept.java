package resepter;

import legemidler.Legemiddel;
import personer.Lege;

public class PResept extends HvitResept {
    public PResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }

    @Override
    public int prisAaBetale() {
        if (legemiddel.hentPris() <= 108) {
            return 0;
        }
        return legemiddel.hentPris() - 108;
    }
}
