package Eksamen_2023_Konte;

public class LederPersonbil extends Personbil implements Lederbil {
    private int egnethet; // enhet: heltall

    public LederPersonbil(int maksHastighet, int antPassasjerer, int egnethet) {
        super(maksHastighet, antPassasjerer);
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
