package Legesystem.hoved;

import java.io.*;
import java.util.*;

// import liste_skjelett.*;
import Legesystem.liste_skjelett.*;
import Legesystem.legemidler.*;
import Legesystem.personer.*;
import Legesystem.resepter.*;

public class Legesystem {
    private IndeksertListe<Pasient> pasienter = new IndeksertListe<>();
    private IndeksertListe<Legemiddel> legemidler = new IndeksertListe<>();
    private Prioritetskø<Lege> leger = new Prioritetskø<>();
    private IndeksertListe<Resept> resepter = new IndeksertListe<>();

    public void lesFraFil(String filnavn) {
        try (Scanner scanner = new Scanner(new File(filnavn))) {
            String seksjon = ""; // Holder styr på hvilken seksjon vi er i
    
            while (scanner.hasNextLine()) {
                String linje = scanner.nextLine().trim();
    
                if (linje.isEmpty()) continue; // Hopp over tomme linjer
    
                // Oppdaterer seksjon når vi møter en overskrift
                if (linje.startsWith("#")) {
                    seksjon = linje; 
                    continue;
                }
    
                IndeksertListe<String> deler = new IndeksertListe<>();
                for (String del : linje.split(",")) {
                    deler.leggTil(del.trim());
                }
    
                // Pasienter
                if (seksjon.equals("# Pasienter (navn, fnr)")) {
                    if (deler.størrelse() == 2) {
                        pasienter.leggTil(new Pasient(deler.hent(0), deler.hent(1)));
                    } else {
                        System.out.println("Feil ved lesing av pasient: " + linje);
                    }
                }
                // Legemidler
                else if (seksjon.equals("# Legemidler (navn,type,pris,virkestoff,[styrke])")) {
                    try {
                        String navn = deler.hent(0);
                        String type = deler.hent(1);
                        int pris = Integer.parseInt(deler.hent(2));
                        double virkestoff = Double.parseDouble(deler.hent(3));
    
                        if (type.equalsIgnoreCase("narkotisk")) {
                            int styrke = Integer.parseInt(deler.hent(4));
                            legemidler.leggTil(new Narkotisk(navn, pris, virkestoff, styrke));
                        } else if (type.equalsIgnoreCase("vanedannende")) {
                            int styrke = Integer.parseInt(deler.hent(4));
                            legemidler.leggTil(new Vanedannende(navn, pris, virkestoff, styrke));
                        } else {
                            legemidler.leggTil(new Vanlig(navn, pris, virkestoff));
                        }
                    } catch (Exception e) {
                        System.out.println("Feil ved lesing av legemiddel: " + linje);
                    }
                }
                // Leger
                else if (seksjon.equals("# Leger (navn,kontrollid / 0 hvis vanlig lege)")) {
                    try {
                        String navn = deler.hent(0);
                        String kontrollID = deler.hent(1);
                        if (kontrollID.equals("0")) {
                            leger.leggTil(new Lege(navn));
                        } else {
                            leger.leggTil(new Spesialist(navn, kontrollID));
                        }
                    } catch (Exception e) {
                        System.out.println("Feil ved lesing av lege: " + linje);
                    }
                }
                // Resepter
                else if (seksjon.equals("# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])")) {
                    try {
                        int legemiddelNummer = Integer.parseInt(deler.hent(0));
                        String legeNavn = deler.hent(1);
                        int pasientID = Integer.parseInt(deler.hent(2));
                        String type = deler.hent(3);
                        int reit = (deler.størrelse() == 5) ? Integer.parseInt(deler.hent(4)) : 0;
    
                        Legemiddel legemiddel = legemidler.hent(legemiddelNummer);
                        Lege lege = finnLege(legeNavn);
                        Pasient pasient = pasienter.hent(pasientID);
    
                        if (lege != null && pasient != null) {
                            Resept resept = switch (type.toLowerCase()) {
                                case "hvit" -> lege.skrivResept(legemiddel, pasient, reit);
                                case "blaa" -> new BlaaResept(legemiddel, lege, pasient, reit);
                                case "militaer" -> new MilitaerResept(legemiddel, lege, pasient);
                                case "p" -> new PResept(legemiddel, lege, pasient, reit);
                                default -> null;
                            };
    
                            if (resept != null) {
                                resepter.leggTil(resept);
                            } else {
                                System.out.println("Ugyldig resept-type: " + type);
                            }
                        } else {
                            System.out.println("Feil ved oppretting av resept: " + linje);
                        }
                    } catch (Exception e) {
                        System.out.println("Feil ved lesing av resept: " + linje);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Filen ikke funnet: " + filnavn);
        }
    }
    

    private Lege finnLege(String navn) {
        for (Lege lege : leger) {
            if (lege.hentNavn().equals(navn))
                return lege;
        }
        return null;
    }

    public void startMeny() {
        Scanner scanner = new Scanner(System.in);
        boolean kjører = true;

        while (kjører) {
            System.out.println("\nVelg en handling:");
            System.out.println("1 - Vis oversikt");
            System.out.println("2 - Legg til element");
            System.out.println("3 - Bruk resept");
            System.out.println("4 - Vis statistikk");
            System.out.println("5 - Lagre til fil");
            System.out.println("0 - Avslutt");

            String valg = scanner.nextLine();
            switch (valg) {
                case "1":
                    visOversikt();
                    break;
                case "2":
                    leggTilElement(scanner);
                    break;
                case "3":
                    brukResept(scanner);
                    break;
                case "4":
                    visStatistikk();
                    break;
                case "5":
                    skrivTilFil("utdata.txt");
                    break;
                case "0":
                    kjører = false;
                    break;
                default:
                    System.out.println("Ugyldig valg!");
            }
        }
    }

    private void visOversikt() {
        System.out.println("\nPasienter:");
        for (Pasient p : pasienter)
            System.out.println(p);
        System.out.println("\nLeger:");
        for (Lege l : leger)
            System.out.println(l);
        System.out.println("\nLegemidler:");
        for (Legemiddel lm : legemidler)
            System.out.println(lm);
        System.out.println("\nResepter:");
        for (Resept r : resepter)
            System.out.println(r);
    }

    private void leggTilElement(Scanner scanner) {
        System.out.println("\nHva vil du legge til? 1: Pasient, 2: Lege, 3: Legemiddel, 4: Resept");
        String valg = scanner.nextLine();
        switch (valg) {
            case "1":
                leggTilPasient(scanner);
                break;
            case "2":
                leggTilLege(scanner);
                break;
            case "3":
                leggTilLegemiddel(scanner);
                break;
            case "4":
                leggTilResept(scanner);
                break;
            default:
                System.out.println("Ugyldig valg!");
        }
    }

    private void leggTilPasient(Scanner scanner) {
        System.out.println("Oppgi pasient navn: ");
        String navn = scanner.nextLine();

        System.out.println("Oppgi pasient fødselsnummer: ");
        String foedselsnummer = scanner.nextLine();

        // if (!pasienter.contains foedselsnummer)
        pasienter.leggTil(new Pasient(navn, foedselsnummer));
    }

    private void leggTilLege(Scanner scanner) {
        System.out.println("Oppgi lege navn: ");
        String navn = scanner.nextLine();

        // if (!pasienter.contains foedselsnummer)
        leger.leggTil(new Lege(navn));
    }

    private void leggTilLegemiddel(Scanner scanner) {
        System.out.println("Oppgi legemiddel navn: ");
        String navn = scanner.nextLine();

        System.out.println("Oppgi legemiddel pris : ");
        int pris = Integer.parseInt(scanner.nextLine());

        System.out.println("Oppgi legemiddel mengde virkestoff: ");
        Double mengdeVirkestoff = Double.parseDouble(scanner.nextLine());

        System.out.println("Oppgi legemiddel type: ");
        String type = scanner.nextLine();

        if (type.equals("narkotisk")) {
            System.out.println("Oppgi legemiddel styrke: ");
            int styrke = Integer.parseInt(scanner.nextLine());
            legemidler.leggTil(new Narkotisk(navn, pris, mengdeVirkestoff, styrke));

        } else if (type.equals("vanedannende")) {
            System.out.println("Oppgi legemiddel styrke: ");
            int styrke = Integer.parseInt(scanner.nextLine());
            legemidler.leggTil(new Vanedannende(navn, pris, mengdeVirkestoff, styrke));

        } else if (type.equals("vanlig")) {
            legemidler.leggTil(new Vanlig(navn, pris, mengdeVirkestoff));

        } else {
            System.out.println("Du har skrevet noe feil, prøv på nytt");
        }
    }

    private void leggTilResept(Scanner scanner) {
        System.out.println("Oppgi resept type: ");
        String type = scanner.nextLine();
        
        System.out.println("Oppgi legemiddel id: ");
        int legemiddelId = Integer.parseInt(scanner.nextLine());
        Legemiddel legemiddel = legemidler.hent(legemiddelId);


        Lege utskrivendeLege = null;
        System.out.println("Oppgi lege: ");
        String oppgittLege = scanner.nextLine();
        for (Lege lege : leger) {
            if (lege.hentNavn().equals(oppgittLege)) {
                utskrivendeLege = lege;
            }
        }

        System.out.println("Oppgi pasient id: ");
        int pasientid = Integer.parseInt(scanner.nextLine());
        Pasient pasient = pasienter.hent(pasientid);

        System.out.println("Oppgi reit: ");
        int reit = Integer.parseInt(scanner.nextLine());

        if (type.equals("blå")) {
            resepter.leggTil(new BlaaResept(legemiddel, utskrivendeLege, pasient, reit));
        } else if (type.equals("hvit")) {
            resepter.leggTil(new HvitResept(legemiddel, utskrivendeLege, pasient, reit));
        } else if (type.equals("p")) {
            resepter.leggTil(new BlaaResept(legemiddel, utskrivendeLege, pasient, reit));
        } else if (type.equals("militær")) {
            resepter.leggTil(new BlaaResept(legemiddel, utskrivendeLege, pasient, 3));
        } else {
            System.out.println("Du har skrevet noe feil, prøv på nytt");
        }
    }

    private void visStatistikk() {
            System.out.println("\nStatistikk");
        
            int narkotiskTeller = 0, vanedannendeTeller = 0, vanligTeller = 0;
            System.out.println("\nLegemidler totalt: " + legemidler.størrelse());
        
            for (Legemiddel lm : legemidler) {
                switch (lm) {
                    case Narkotisk n -> narkotiskTeller++;
                    case Vanedannende v -> vanedannendeTeller++;
                    case Vanlig va -> vanligTeller++;
                    default -> throw new IllegalStateException("Ukjent legemiddeltype: " + lm.getClass().getSimpleName());
                }
            }
            System.out.printf("Narkotiske: %d, Vanedannende: %d, Vanlige: %d%n",
                              narkotiskTeller, vanedannendeTeller, vanligTeller);
        
        
        int legeTeller = 0, spesialistTeller = 0;
        System.out.println("\nPersoner totalt: " + leger.størrelse());

        for (Lege l : leger) {
            switch (l) {
                case Spesialist s -> spesialistTeller++;
                case Lege n -> legeTeller++;
                // default -> throw new IllegalStateException("Ukjent lege type: " + l.getClass().getSimpleName());
            }
        }
        System.out.println("Leger: " + legeTeller + ", Spesialister: " + spesialistTeller);


        int blaaTeller = 0, hvitTeller = 0, militaerTeller = 0, pTeller = 0;
        System.out.println("\nResepter totalt: " + resepter.størrelse());

        for (Resept r : resepter) {
            switch (r) {
                case MilitaerResept m -> militaerTeller++;
                case PResept p -> pTeller++;
                case BlaaResept b -> blaaTeller++;
                case HvitResept h -> hvitTeller++;
                default -> throw new IllegalStateException("Ukjent legemiddeltype: " + r.getClass().getSimpleName());
            }
        }
        System.out.printf("Blå: %d, Hvit: %d, Militær: %d, P: %d%n",
                            blaaTeller, hvitTeller, militaerTeller, pTeller);
    }


    private void brukResept(Scanner scanner) {
        System.out.println("Oppgi resept ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        Resept resept = resepter.hent(id); // Id starter på 0, så id vil ha samme plass som pos.
        resept.bruk();
    }

    private void skrivTilFil(String filnavn) {
        try (PrintWriter writer = new PrintWriter(new File(filnavn))) {
            for (Pasient p : pasienter)
                writer.println(p);
            for (Legemiddel lm : legemidler)
                writer.println(lm);
            for (Lege l : leger)
                writer.println(l);
            for (Resept r : resepter)
                writer.println(r);
            System.out.println("Data lagret til " + filnavn);
        } catch (IOException e) {
            System.out.println("Feil ved skriving til fil: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Legesystem system = new Legesystem();
        system.lesFraFil("legedata.txt");
        system.startMeny();
        
    }
}
