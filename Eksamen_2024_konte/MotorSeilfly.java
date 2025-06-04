package Eksamen_2024_konte;

public abstract class MotorSeilfly extends Seilfly implements Motordrevet {
    protected int trekkKraft;
    protected String motorType;

    public MotorSeilfly(String id, int glideTall, int vingespenn, int trekkKraft, String motorType) {
        super(id, glideTall, vingespenn);
        this.trekkKraft = trekkKraft;
        this.motorType = motorType;
    }

    @Override
    public int hentTrekkKraft() {
        return trekkKraft;
    }

    @Override
    public String hentMotorType() {
        return motorType;
    }
}