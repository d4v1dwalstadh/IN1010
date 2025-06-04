package Eksamen_2024_konte;

public class SlepePilot implements Runnable{
    protected KlarTilStart monitor;

    public SlepePilot(KlarTilStart monitor) {
        this.monitor = monitor;
    }

    @Override 
    public void run() {
        while (true) {
            monitor.hentSeilfly();
            try {
                Thread.sleep(1000*360);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
