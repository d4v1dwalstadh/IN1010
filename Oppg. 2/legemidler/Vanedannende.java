package legemidler;

public class Vanedannende extends Legemiddel {
    public final int styrke;

    public Vanedannende(String navn, int pris, double mengdeVirkestoff, int styrke) {
        super(navn, pris, mengdeVirkestoff);
        this.styrke = styrke;
    }

    @Override
    public String toString() {
        return super.toString() + ", type=vanedannende" + ", styrke=" + styrke;
    }
}

