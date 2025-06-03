package Eksamen_2024;

public class Lastefly extends Motorfly{
    protected int lastevekt;// enhet: kg

    public Lastefly(String id, int motorAnt, int mtow, int totTrekkkraft, int motorEffekt, int lastevekt) {
        super(id, motorAnt, mtow, totTrekkkraft, motorEffekt);
        this.lastevekt = lastevekt;
    }

    public int hentLastevekt() {
        return lastevekt;
    }
}
