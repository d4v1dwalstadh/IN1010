package resepter;

import legemidler.Legemiddel;
import personer.Lege;

public class MilitaerResept extends HvitResept {
    public MilitaerResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId) {
        super(legemiddel, utskrivendeLege, pasientId, 3);
    }

@Override
public int prisAaBetale() {
    return 0;   // 100% rabatt
}
}
