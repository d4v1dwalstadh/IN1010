public class Flettetråd implements Runnable{
    private final Monitor monitor;

    public Flettetråd(Monitor monitor) {
        this.monitor = monitor;
    }

    public void run() {
        while (true) {
            Frekvenstabell[] tabeller = monitor.taUtTo();

            if (tabeller == null || tabeller.length < 2) {
                break;
            }

            Frekvenstabell flettet = Frekvenstabell.flett(tabeller[0], tabeller[1]);
            monitor.settInn(flettet);
        }
    }
}
