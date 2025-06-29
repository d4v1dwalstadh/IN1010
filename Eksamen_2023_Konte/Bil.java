package Eksamen_2023_Konte;

public abstract class Bil {
    protected String id;
    protected Kolonne kolonne = null;
    protected int maksHastighet; // enhet: km/t
    protected Bil neste;
    protected Bil forrige;

    public Bil(int maksHastighet) {
        this.maksHastighet = maksHastighet;
    }

    public void settNeste(Bil neste) {
        this.neste = neste;
    }

    public void settForrige(Bil forrige) {
        this.forrige = forrige;
    }

    public void settKolonne(Kolonne kolonne) {
        this.kolonne = kolonne;
    }

    public Kolonne hentKolonne() {
        return kolonne;
    }

    public int hentMaksHastighet() {
        return maksHastighet;
    }

    public int finnLaveste() {
        if (neste == null) {
            throw new IllegalStateException("Kolonnen er tom");
        }
        int lavestHosNeste = neste.finnLaveste();
        return Math.min(maksHastighet, lavestHosNeste);
    }
}


