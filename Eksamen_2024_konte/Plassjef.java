package Eksamen_2024_konte;

public class Plassjef implements Runnable {
    protected int antSlepefly;
    protected Konkurransegruppe kgruppe;
    protected KlarTilStart monitor;

    public Plassjef(int antSlepefly, Konkurransegruppe kgruppe, KlarTilStart monitor) {
        this.antSlepefly = antSlepefly;
        this.kgruppe = kgruppe;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        for (int i = 0; i < antSlepefly; i++) {
            SlepePilot p = new SlepePilot(monitor);
            new Thread(p).start();
        }

        for (Seilfly s : kgruppe) {
            monitor.seilFlyKlar(s);
        }
    } 
}