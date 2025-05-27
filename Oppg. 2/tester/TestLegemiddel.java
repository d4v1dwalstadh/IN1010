package tester;

import legemidler.*;


public class TestLegemiddel {
    public static void main(String[] args) {
        // oppretter objekter
        Narkotisk narkotisk = new Narkotisk("Morfin", 200, 50.5, 10);
        Vanedannende vanedannende = new Vanedannende("Valium", 150, 30.0, 5);
        Vanlig vanlig = new Vanlig("Paracet", 50, 10.0, 4);

        // tester ID-er
        testLegemiddelId(narkotisk, 0);
        testLegemiddelId(vanedannende, 1);
        testLegemiddelId(vanlig, 2);

        // tester toString funksjoner
        // testToString(narkotisk);
        // testToString(vanedannende);
        // testToString(vanlig);
    }

    private static void testLegemiddelId(Legemiddel legemiddel, int forventetLegemiddelId) {
        if (legemiddel.id == forventetLegemiddelId) {
            System.out.println("Test bestått: ID er korrekt for " + legemiddel.navn);
        } else {
            System.out.println("Test FEILET: ID er feil for " + legemiddel.navn + " (Forventet: " + forventetLegemiddelId + ", Fikk: " + legemiddel.id + ")");
        }
    }

    private static void testToString(Legemiddel legemiddel) {
        System.out.println(legemiddel);
        System.out.println("Sjekk selv om utskrift stemmer");
    }
}
