package Eksamen_2024;

public abstract class Fly {
    protected final String id;
    protected int motorAnt;
    protected int mtow;
    protected int totTrekkkraft;  // enhet: kw
    protected Fly neste;

    public Fly(String id, int motorAnt, int mtow, int totTrekkkraft) {
        this.id = id;
        this.motorAnt = motorAnt;
        this.mtow = mtow;
        this.totTrekkkraft = totTrekkkraft;
        this.neste = null;
    }

    public String hentId() {
        return id;
    }

    public int hentMotorAnt() {
        return motorAnt;
    }

    public int hentMtow() {
        return mtow;
    }

    public int finnMaksVektR() {
        if (neste == null) {
            return mtow;
        }
        int maxResten = neste.finnMaksVektR();
        if (mtow > maxResten) {
            return mtow;
        } else {
            return maxResten;
        }
    }
}
