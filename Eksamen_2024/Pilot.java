package Eksamen_2024;

public class Pilot implements Runnable{
    protected Fly fly;
    protected Rullebane rullebane;

    public Pilot(Fly fly, Rullebane rullebane) {
        this.fly = fly;
        this.rullebane = rullebane;
    }

    @Override
    public void run() {
        rullebane.hentStartTillatelse(fly);
    }
}
