package personer;

import liste_skjelett.*;
import resepter.*;

public class Pasient {
    protected final String navn;
    protected final String foedselsNr;
    protected final int id;
    protected static int teller = 0;
    protected IndeksertListe<Resept> resepter;

    // konstruktør
    public Pasient(String navn, String foedselsNr) {
        this.navn = navn;
        this.foedselsNr = foedselsNr;
        id = teller++;
        resepter = new IndeksertListe<>();
    }

    public int hentId() {
        return id;
    }

    public String hentNavn() {
        return navn;
    }

    public String hentFodselsnummer() {
        return foedselsNr;
    }

    public void leggTilResept(Resept r) {
        resepter.leggTil(r);
    }

    public Lenkeliste<Resept> hentResepter() {
        return resepter;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ", Navn: " + navn +
                ", Fødselsnummer: " + foedselsNr;

    }

}
