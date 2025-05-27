package personer;

public class Lege{
    protected final String navn;

    // konstruktør
    public Lege(String navn) {
        this.navn = navn;
    }

    public String hentNavn() {
        return navn;
    }

    @Override
    public String toString() {
        return "lege: " + navn;
    }
}
