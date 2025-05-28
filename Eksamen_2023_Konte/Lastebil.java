package IN1010.Eksamen_2023_Konte;

public class Lastebil extends Bil {
    protected int lasteevne; // enhet: kg

    public Lastebil(int maksHastighet, int lasteevne) {
        super(maksHastighet);
        this.lasteevne = lasteevne;
    }
}
