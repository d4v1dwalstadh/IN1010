package Legesystem.resepter;

import Legesystem.legemidler.Legemiddel;
import Legesystem.personer.*;

public class PResept extends HvitResept {
    public PResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    @Override
    public int prisAaBetale() {
        if (legemiddel.hentPris() <= 108) {
            return 0;
        }
        return legemiddel.hentPris() - 108;
    }
}
