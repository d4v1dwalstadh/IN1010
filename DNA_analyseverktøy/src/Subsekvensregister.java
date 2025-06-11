import java.io.*;
import java.util.*;

class Subsekvensregister {
    private static final int SUBSEKVENSLENGDE = 3;
    private List<Frekvenstabell> register;

    public Subsekvensregister() {
        register = new ArrayList<>();
    }

    public void settInn(Frekvenstabell f) {
        register.add(f);
    }

    public Frekvenstabell taUt() {
        if (!register.isEmpty()) {
            return register.remove(0);
        }
        return register.get(0);     // vil være tom, så feilmelding
    }

    public Frekvenstabell[] taUtTo() {
        if (register.size() < 2) {
            return new Frekvenstabell[0];
        }
        Frekvenstabell[] arr = new Frekvenstabell[2];
        arr[0] = taUt();
        arr[1] = taUt();
        return arr;
    }

    public int antall() {
        return register.size();
    }

    public static Frekvenstabell les(String filnavn) {
        Frekvenstabell f = new Frekvenstabell();
        try (BufferedReader reader = new BufferedReader(new FileReader(filnavn))) {
            String linje;
            while ((linje = reader.readLine()) != null) {
                for (int i = 0; i <= linje.length() - SUBSEKVENSLENGDE; i++) {
                    String subsekvens = linje.substring(i, i + SUBSEKVENSLENGDE);
                    f.put(subsekvens, 1);
                }
            }
        } catch (IOException e) {
            System.err.println("Feil ved lesing av fil: " + e.getMessage());
        }
        return f;
    }
}