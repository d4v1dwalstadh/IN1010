public class Celle {
    protected boolean levende; // private???
    protected Celle[] naboer;
    protected int antNaboer;
    protected int antLevendeNaboer;

    // konstruktør
    public Celle() {
        this.levende = false;
        this.naboer = new Celle[8];
        this.antNaboer = 0;
        this.antLevendeNaboer = 0;
    }

    public void settLevende() {
        this.levende = true;
    }

    public void settDoed() {
        this.levende = false;
    }

    public boolean erLevende() {
        return levende;
    }

    public char hentStatusTegn() {
        return levende ? 'O' : '.'; // ternær operasjon en forenklet if - else funksjon
    }

    public void leggTilNabo(Celle nabo) {
        naboer[antNaboer] = nabo;
        antNaboer++;
    }

    public void tellLevendeNaboer() {
        antLevendeNaboer = 0;
        for (int i = 0; i < antNaboer; i++) {
            if (naboer[i] != null && naboer[i].erLevende()) {
                antLevendeNaboer++;
            }
        }
    }

    public void oppdaterStatus() {
        if (levende) {
            if (antLevendeNaboer < 2 || antLevendeNaboer > 3) {
                settDoed();
            }
        } else {
            if (antLevendeNaboer == 3) {
                settLevende();
            }
        }
    }
}
