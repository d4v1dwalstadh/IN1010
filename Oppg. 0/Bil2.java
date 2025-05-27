public class Bil2 {
    private String bilnummer;
    
    public Bil2(String bilNr) {
        bilnummer = bilNr;
    }

    public void skrivUt() {
        System.out.println("Bilens nummer: " + bilnummer);
    }
}

