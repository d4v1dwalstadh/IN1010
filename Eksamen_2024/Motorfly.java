package Eksamen_2024;

public abstract class Motorfly extends Fly implements Motordrevet {
    protected int motorEffekt;  // enhet: kw

    public Motorfly(String id, int motorAnt, int mtow, int totTrekkkraft, int motorEffekt) {
        super(id, motorAnt, mtow, totTrekkkraft);
        this.motorEffekt = motorEffekt;
    }

    @Override
    public int hentTrekkraft() {
        return totTrekkkraft;
    }

    public int hentMotorEffekt() {
        return motorEffekt;
    }
    
}
