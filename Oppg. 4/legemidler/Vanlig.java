package legemidler;

public class Vanlig extends Legemiddel {

    public Vanlig(String navn, int pris, double mengdeVirkestoff) {
        super(navn, pris, mengdeVirkestoff);
    }

    @Override
    public String toString() {
        return "Type=vanlig, " + super.toString();
    }
}