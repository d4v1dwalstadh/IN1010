public class Resultat implements Runnable{
    private Monitor monitor;

    public Resultat(Monitor monitor) {
        this.monitor = monitor;
    }

    public void run() {
        while (true) {
            Skinnegaende s = monitor.hentNeste();
            if (s == null) break;
            System.out.println(s.hentId());
        }
    }
}
