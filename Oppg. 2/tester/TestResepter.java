package tester;

import legemidler.*;
import resepter.*;
import personer.Lege;

public class TestResepter {
    public static void main(String[] args) {
        // oppretter legemidlene og lege
        Legemiddel paracet = new Vanlig("Paracet", 50, 500, 4);
        Lege lege = new Lege("Dr. Hansen");

        // oppretter ulike resepter
        Resept hvit = new HvitResept(paracet, lege, 1, 5);
        Resept blaa = new BlaaResept(paracet, lege, 2, 3);
        Resept militaer = new MilitaerResept(paracet, lege, 3);
        Resept pResept = new PResept(paracet, lege, 4, 4);

        System.out.println(hvit.farge() == "hvit");
        System.out.println(blaa.prisAaBetale() == StrictMath.round(50/4));
        System.out.println(militaer.prisAaBetale() == 0);
        System.out.println(pResept.prisAaBetale() == 0);
    }
}
