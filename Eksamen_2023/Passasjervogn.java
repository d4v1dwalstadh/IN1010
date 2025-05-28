class Passasjervogn extends Vogn {
    protected final int maksPassasjerer;

    public Passasjervogn(String id, int sporvidde, int lengde, int maksPassasjerer) {
        super(id, sporvidde, lengde);
        this.maksPassasjerer = maksPassasjerer;
    }

    public int hentMaksPassasjerer() {
        return maksPassasjerer;
    }
}
