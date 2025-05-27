public class Rutenett {
    protected int antRader;
    protected int antKolonner;
    protected Celle[][] rutene;

    // konstruktør
    public Rutenett(int rader, int kolonner) {
        this.antRader = rader;
        this.antKolonner = kolonner;
        this.rutene = new Celle[rader][kolonner];
    }

    public void lagCelle(int rad, int kol) {
        if (0 <= rad && rad < antRader && 0 <= kol && kol < antKolonner) {
            rutene[rad][kol] = new Celle();
            if (Math.random() <= 0.3333) {
                rutene[rad][kol].settLevende();
            }
        }
    }

    public void fyllMedTilfeldigeCeller() {
        for (int i = 0; i < antRader; i++) {
            for (int j = 0; j < antKolonner; j++) {
                lagCelle(i, j);
            }
        }
    }

    public Celle hentCelle(int rad, int kol) {
        if (0 <= rad && rad < antRader && 0 <= kol && kol < antKolonner) {
            return rutene[rad][kol];
        }
        return null;
    }

    public void tegnRutenett() {
        for (int i = 0; i < antRader; i++) {
            for (int j = 0; j < antKolonner; j++) {
                System.out.print(rutene[i][j].hentStatusTegn());
            }
            System.err.println();
        }
    }

    public void settNaboer(int rad, int kol) {
        Celle celle = hentCelle(rad, kol);
        if (celle == null)
            return;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0)
                    continue;
                Celle nabo = hentCelle(rad + i, kol + j);
                if (nabo != null) {
                    celle.leggTilNabo(nabo);
                }
            }
        }
    }

    public void kobleAlleCeller() {
        for (int i = 0; i < antRader; i++) {
            for (int j = 0; j < antKolonner; j++) {
                settNaboer(i, j);
            }
        }
    }

    public int antallLevende() {
        int teller = 0;
        for (int i = 0; i < antRader; i++) {
            for (int j = 0; j < antKolonner; j++) {
                if (rutene[i][j] != null && rutene[i][j].erLevende()) {
                    teller++;
                }
            }
        }
        return teller;
    }   

}
