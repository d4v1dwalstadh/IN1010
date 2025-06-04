package Eksamen_2024_konte;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class KlarTilStart {
    private Lock lås;
    private Condition ventPåSeilfly = lås.newCondition();
    protected ArrayList<Seilfly> kø = new ArrayList<>();

    public void seilFlyKlar(Seilfly fly) {
        lås.lock();
        try {
            kø.add(fly);
            ventPåSeilfly.signalAll();
        } finally {
            lås.unlock();
        }
    }

    public Seilfly hentSeilfly() {
        lås.lock();
        try {
            while (kø.isEmpty()) {
                ventPåSeilfly.await();
            }
            return kø.remove(0);
        } catch (InterruptedException e) {
            return null;
        }finally {
            lås.unlock();
        }
    }
}
