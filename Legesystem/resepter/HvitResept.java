package Legesystem.resepter;

import Legesystem.legemidler.Legemiddel;
import Legesystem.personer.*;


public class HvitResept extends Resept {
    public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    @Override
    public String farge() {
        return "hvit";
    }

    @Override
    public int prisAaBetale() {
        return legemiddel.hentPris();
    }
}
