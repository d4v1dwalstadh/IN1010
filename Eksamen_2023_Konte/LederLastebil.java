package Eksamen_2023_Konte;

public class LederLastebil extends Lastebil implements Lederbil {
    public int egnethet; // enhet: heltall

    public LederLastebil(int maksHastighet, int lasteevne, int egnethet) {
        super(maksHastighet, lasteevne);
        this.egnethet = egnethet;
    }

    @Override
    public int hentMaksHastighet() {
        return maksHastighet;
    }

    @Override
    public int hentEgnethet() {
        return egnethet;
    }
}
