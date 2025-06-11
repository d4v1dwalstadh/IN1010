public class Lesetråd implements Runnable{
    protected String filnavn;
    protected Monitor monitor;

    public Lesetråd(String filnavn, Monitor monitor) {
        this.filnavn = filnavn;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        Frekvenstabell f = Subsekvensregister.les(filnavn);
        monitor.settInn(f);
    }
}
