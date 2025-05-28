class Lokomotiv extends Skinnegaende implements Motordrevet {
    private final boolean fossilt;
    private final int trekkraft;  // enhet: kw

    public Lokomotiv(String id, int sporvidde, boolean fossilt, int trekkraft) {
        super(id, sporvidde);
        this.fossilt = fossilt;
        this.trekkraft = trekkraft;
    }

    @Override
    public boolean fossilt() {
        return fossilt;
    }

    @Override
    public int trekkraft() {
        return trekkraft;
    }
}
