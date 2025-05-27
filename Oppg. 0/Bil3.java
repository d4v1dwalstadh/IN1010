public class Bil3 {
    private String bilnummer;
    
    public Bil3(String bilNr) {
        bilnummer = bilNr;
    }

    public void skrivUt() {
        System.out.println("Bilens nummer: " + bilnummer);
    }

    public String hentNummer() {
        return bilnummer;
    }
}



