import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor {
    private ArrayList<Skinnegaende> funn = new ArrayList<>();
    private int antLetere;
    private int ferdigeLetere = 0;

    private Lock laas = new ReentrantLock();
    private Condition ikkeTom = laas.newCondition();


    public Monitor(int antLetere) {
        this.antLetere = antLetere;
    }

    public void leggTil(Skinnegaende s) {
        laas.lock();
        try {
            funn.add(s);
            ikkeTom.signal();
        } finally {
            laas.unlock();
        }
    }

    public void ferdigLeting() {
        laas.lock();
        try {
            ferdigeLetere++;
            ikkeTom.signal();
        } finally {
            laas.unlock();
        }
    }

    public Skinnegaende hentNeste() {
        laas.lock();
        try {
            while (funn.isEmpty() && ferdigeLetere < antLetere) {
                ikkeTom.await();
            }
            if (funn.isEmpty()) return null;
            return funn.remove(0);
        } catch (InterruptedException e) {
            return null;
        } finally {
            laas.unlock();
        }
    }
}
