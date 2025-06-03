package Eksamen_2024;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Rullebane {
    private int antVentendeFly = 0;
    private Lock laas = new ReentrantLock();
    private Condition avventStartTillatelse = laas.newCondition(); // piloter venter på å fly
    private Condition avventFly = laas.newCondition(); // flygeleder venter med å sjekke til


    public void sjekkAvganger() {
        laas.lock();
        try {
            while (antVentendeFly == 0) avventFly.await();
            avventStartTillatelse.signal();
            antVentendeFly --;
        }
        catch (InterruptedException e) {
            return;
        }
        finally {
            laas.unlock();
        }
    }

    public void hentStartTillatelse(Fly fly) {
        laas.lock();
        try {
            antVentendeFly++;
            avventStartTillatelse.await();
            avventFly.signalAll();
        } 
        catch (InterruptedException E) {
            return;
        }
        finally {
            laas.unlock();
        }
    }
    
}


