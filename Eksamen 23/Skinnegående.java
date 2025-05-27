abstract class Skinnegaende {
    protected final String id;
    protected final int sporvidde;  // enhet: cm
    protected Skinnegaende neste;  // For dobbeltlenket liste
    protected Skinnegaende forrige;

    public Skinnegaende(String id, int sporvidde) {
        this.id = id;
        this.sporvidde = sporvidde;
    }

    public String hentId() {
        return id;
    }

    public int hentSporvidde() {
        return sporvidde;
    }

    public Skinnegaende hentNeste() {
        return neste;
    }

    public Skinnegaende hentForrige() {
        return forrige;
    }

    public void settNeste(Skinnegaende neste) {
        this.neste = neste;
    }

    public void settForrige(Skinnegaende forrige) {
        this.forrige = forrige;
    }
}
