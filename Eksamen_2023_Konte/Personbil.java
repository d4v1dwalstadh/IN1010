package Eksamen_2023_Konte;

public class Personbil extends Bil{
    protected int antPassasjerer; // enhet: antall personer

    public Personbil(int maksHastighet, int antPassasjerer) {
        super(maksHastighet);
        this.antPassasjerer = antPassasjerer;
    }
}

