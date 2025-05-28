package IN1010.Eksamen_2023_Konte;

public class LederLastebil extends Lastebil implements Lederbil {
    private int egnethet; // enhet: heltall

    public LederLastebil(int maksHastighet, int lasteevne, int egnethet) {
        super(maksHastighet, lasteevne);
        this.egnethet = egnethet;
    }

    @Override
    public int hentEgnethet() {
        return egnethet;
    }
}
