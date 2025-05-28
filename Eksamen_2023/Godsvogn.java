class Godsvogn extends Vogn {
    protected final double maksLastevekt;  //enhet: kilo

    public Godsvogn(String id, int sporvidde, int lengde, double maksLastevekt) {
        super(id, sporvidde, lengde);
        this.maksLastevekt = maksLastevekt;
    }
    
    public double hentLastevekt() {
        return maksLastevekt;
    }
}
