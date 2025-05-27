abstract class Vogn extends Skinnegaende {
    protected final int lengde;  //enhet: cm

    public Vogn(String id, int sporvidde, int lengde) {
        super(id, sporvidde);
        this.lengde = lengde;
    }

    public int hentLengde() {
        return lengde;
    }
}