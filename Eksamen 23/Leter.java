public class Leter implements Runnable {
    private Tog tog;
    private Monitor monitor;
    private String søk;

    public Leter(Tog tog, Monitor monitor, String søk) {
        this.tog = tog;
        this.monitor = monitor;
        this.søk = søk;
    }

    @Override
    public void run() {
        for (Skinnegaende s : tog) {
            if (s.id.startsWith(søk)) {
                monitor.leggTil(s);
            }
        }
        monitor.ferdigLeting();
    }
    
}
