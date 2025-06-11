import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor {
    private final Subsekvensregister register;
    private final Lock lock = new ReentrantLock();
    private final Condition ikkeTom = lock.newCondition();

    public Monitor() {
        register = new Subsekvensregister();
    }

    public void settInn(Frekvenstabell f) {
        lock.lock();
        try {
            register.settInn(f);
            ikkeTom.signalAll(); // Liste ikke lenger tom
        } finally {
            lock.unlock();
        }
    }

    public Frekvenstabell taUt() {
        lock.lock();
        try {
            while (register.antall() == 0) {
                ikkeTom.await();
            }
            return register.taUt();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return new Frekvenstabell();
        } finally {
            lock.unlock();
        }
    }

    // public Frekvenstabell[] taUtTo() {
    // lock.lock();
    // try {
    // while (register.antall() < 2) {
    // ikkeTom.await();
    // }
    // return register.taUtTo();
    // } catch (InterruptedException e) {
    // Thread.currentThread().interrupt();
    // return new Frekvenstabell[0];
    // } finally {
    // lock.unlock();
    // }
    // }

    public Frekvenstabell[] taUtTo() {
        lock.lock();
        try {
            if (register.antall() < 2) { // I stedet for å vente, returner null/tom liste
                return null;
            }
            return register.taUtTo();
        } finally {
            lock.unlock();
        }
    }

    public int antall() {
        lock.lock();
        try {
            return register.antall();
        } finally {
            lock.unlock();
        }
    }
}
