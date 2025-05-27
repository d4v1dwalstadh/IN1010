package personer;

import legemidler.Legemiddel;
import legemidler.Narkotisk;
import resepter.BlaaResept;
import resepter.HvitResept;
import resepter.Resept;

public class Spesialist extends Lege implements Godkjenningsfritak {
    private String kontrollkode;

    public Spesialist(String navn, String kontrollkode) {
        super(navn);
        this.kontrollkode = kontrollkode;
    }

    @Override
    public String hentKontrollkode() {
        return kontrollkode;
    }

    @Override
    public Resept skrivResept(Legemiddel legemiddel, Pasient pasient, int reit) {
        Resept ny;
        if (legemiddel instanceof Narkotisk) {
            ny = new BlaaResept(legemiddel, this, pasient, reit);
        } else {
            ny = new HvitResept(legemiddel, this, pasient, reit);
        }
        utskrevneResepter.leggTil(ny);

        return ny;
    }

    @Override
    public String toString() {
        return "Spesialist: " + navn + ", Kontrollkode: " + kontrollkode;
    }
}
