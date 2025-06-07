package Legesystem.resepter;

import Legesystem.legemidler.Legemiddel;
import Legesystem.personer.*;

public class MilitaerResept extends HvitResept {
    public MilitaerResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient) {
        super(legemiddel, utskrivendeLege, pasient, 3);
    }

@Override
public int prisAaBetale() {
    return 0;   // 100% rabatt
}
}
