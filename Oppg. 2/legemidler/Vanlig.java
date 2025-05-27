package legemidler;

public class Vanlig extends Legemiddel {
    public final int styrke;

    public Vanlig(String navn, int pris, double mengdeVirkestoff, int styrke) {
        super(navn, pris, mengdeVirkestoff);
        this.styrke = styrke;
    }

    @Override
    public String toString() {
        return super.toString() + ", type=vanlig" + ", styrke=" + styrke;
    }
}