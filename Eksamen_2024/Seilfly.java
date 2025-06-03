package Eksamen_2024;

public class Seilfly extends Fly {
    protected int minSynkehastighet;  // enhet: cm/s

    public Seilfly(String id, int motorAnt, int mtow, int totTrekkkraft, int minSynkehastighet) {
        super(id, motorAnt, mtow, totTrekkkraft);
        this.minSynkehastighet = minSynkehastighet;
    }

    public int hentMinSynkehastighet() {
        return minSynkehastighet;
    }
} 
    

