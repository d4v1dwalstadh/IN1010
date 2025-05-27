public class Verden {
    protected Rutenett rutenett;
    protected int genNr;

    // Konstruktør
    public Verden(int rader, int kolonner) {
        this.rutenett = new Rutenett(rader, kolonner);
        this.genNr = 0;
        rutenett.fyllMedTilfeldigeCeller();
        rutenett.kobleAlleCeller();
    }

    public void tegn() {
        rutenett.tegnRutenett();
        System.out.println("Generasjon nr: " + genNr + ", Antall levende: " + rutenett.antallLevende());
    }

    public void oppdatering() {
        // 1. Tell levende naboer for alle celler
        for (int i = 0; i < rutenett.antRader; i++) {
            for (int j = 0; j < rutenett.antKolonner; j++) {
                if (rutenett.hentCelle(i, j) != null) {
                    rutenett.hentCelle(i, j).tellLevendeNaboer();
                }
            }
        }

        // 2. Oppdater status for alle celler
        for (int i = 0; i < rutenett.antRader; i++) {
            for (int j = 0; j < rutenett.antKolonner; j++) {
                if (rutenett.hentCelle(i, j) != null) {
                    rutenett.hentCelle(i, j).oppdaterStatus();
                }
            }
        }

        // 3. Øk generasjonsnummeret
        genNr++;
    }


}
