import java.util.ArrayList;

public class SortRute extends Rute {
    public SortRute(int rad, int kol, Labyrint labyrint) {
        super(rad, kol, labyrint);
    }

    @Override
    public void finn(Rute Fra, ArrayList<Koordinat> sti) {
        // går ikke videre, møtt en vegg
    }

    @Override
    public String toString() {
        return " # ";
    }
}
