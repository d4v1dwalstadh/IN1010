package Eksamen_2024_konte;

public abstract class Seilfly {
    protected String id;
    protected int glideTall;
    protected int vingespenn;
    protected Seilfly neste;
    protected Seilfly forrige;

    public Seilfly(String id, int glideTall, int vingespenn) {
        this.id = id;
        this.glideTall = glideTall;
        this.vingespenn = vingespenn;
        this.neste = null;
        this.forrige = null;
    }

    public String hentId() {
        return id;
    }

    public int hentGlideTall() {
        return glideTall;
    }

    public int hentVingespenn() {
        return vingespenn;
    }

    public int finnMaxSpennviddeR() {
        if (neste == null) {
            return vingespenn;
        }
        return Math.max(vingespenn, neste.hentVingespenn());
    }
}
