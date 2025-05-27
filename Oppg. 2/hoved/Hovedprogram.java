package hoved;

import legemidler.*;
import personer.*;
import resepter.*;

public class Hovedprogram {
    public static void main(String[] args) {
        // Oppretter legemidler
        Narkotisk morfin = new Narkotisk("Morfin", 200, 50.5, 10);
        Vanedannende valium = new Vanedannende("Valium", 150, 30.0, 5);
        Vanlig paracet = new Vanlig("Paracet", 50, 10.0, 4);

        // Oppretter leger
        Lege lege1 = new Lege("Dr. Hansen");
        Spesialist spesialist1 = new Spesialist("Dr. Johansen", "ABC123");

        // Oppretter resepter
        Resept hvitResept = new HvitResept(morfin, lege1, 123, 5);
        Resept blaaResept = new BlaaResept(valium, spesialist1, 456, 3);
        Resept militaerResept = new MilitaerResept(paracet, spesialist1, 789);
        Resept pResept = new PResept(morfin, lege1, 321, 2);

        // Skriver ut informasjon om objektene
        System.out.println(morfin);
        System.out.println(valium);
        System.out.println(paracet);

        System.out.println(lege1);
        System.out.println(spesialist1);

        System.out.println(hvitResept);
        System.out.println(blaaResept);
        System.out.println(militaerResept);
        System.out.println(pResept);
    }
}


