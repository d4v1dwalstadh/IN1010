package Eksamen_2024;

public class Passasjerfly extends Motorfly {
    protected int passasjerAntall;  // enhet: antall passasjerer

    public Passasjerfly(String id, int motorAnt, int mtow, int totTrekkkraft, int motorEffekt, int passasjerAntall) {
        super(id, motorAnt, mtow, totTrekkkraft, motorEffekt);
        this.passasjerAntall = passasjerAntall;
    }

    public int hentPassasjerAntall() {
        return passasjerAntall;
    }
}
