package Eksamen_2024;

public class Flygeleder implements Runnable {
    protected Rullebane rullebane;

    public Flygeleder(Rullebane rullebane) {
        this.rullebane = rullebane;
    }

    @Override
    public void run() {
        while (true) {
            rullebane.sjekkAvganger();
            try {
                Thread.sleep(1000 * 60);
            } 
            catch (InterruptedException e) {
                return;
            }
        }
    }
}
