package Legesystem.personer;

import Legesystem.liste_skjelett.IndeksertListe;
import Legesystem.resepter.*;
import Legesystem.legemidler.*;

public class Lege implements Comparable<Lege> {
    protected final String navn;
    protected IndeksertListe<Resept> utskrevneResepter;

    // konstruktør
    public Lege(String navn) {
        this.navn = navn;
        utskrevneResepter = new IndeksertListe<>();
    }

    public String hentNavn() {
        return navn;
    }

    public IndeksertListe<Resept> hentUtskrevneResepter() {
        return utskrevneResepter;
    }

    @Override
    public int compareTo(Lege annenLege) {
        return this.navn.compareTo(annenLege.hentNavn());
    }

    public Resept skrivResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk) {
            throw new UlovligUtskrift(this, legemiddel);
        }

        Resept ny = new HvitResept(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(ny);

        return ny;
    }

    private class UlovligUtskrift extends Exception {
        UlovligUtskrift(Lege lege, Legemiddel legemiddel) {
            super("Legen " + lege.hentNavn() + " har ikke lov til å ,→ skrive ut " + legemiddel.hentNavn());
        }
    }

    @Override
    public String toString() {
        return "Lege: " + navn;
    }
}
