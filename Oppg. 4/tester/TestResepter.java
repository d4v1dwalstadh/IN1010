package tester;

import legemidler.*;
import resepter.*;
import personer.*;

public class TestResepter {
    public static void main(String[] args) {
        // oppretter legemidlene og lege
        Legemiddel paracet = new Vanlig("Paracet", 50, 500);
        Lege lege = new Lege("Dr. Hansen");
        Pasient pasient = new Pasient("Jon", "12345000000");

        // oppretter ulike resepter
        Resept hvit = new HvitResept(paracet, lege, pasient, 5);
        Resept blaa = new BlaaResept(paracet, lege, pasient, 3);
        Resept militaer = new MilitaerResept(paracet, lege, pasient);
        Resept pResept = new PResept(paracet, lege, pasient, 4);

        System.out.println(hvit.farge() == "hvit");
        System.out.println(blaa.prisAaBetale() == StrictMath.round(50/4));
        System.out.println(militaer.prisAaBetale() == 0);
        System.out.println(pResept.prisAaBetale() == 0);
    }
}
