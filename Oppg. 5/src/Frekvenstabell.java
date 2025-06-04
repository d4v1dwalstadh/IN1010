import java.io.*;
import java.util.*;

public class Frekvenstabell extends TreeMap<String, Integer> {
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : this.entrySet()) {
            sb.append(entry.getKey()).append(" ").append(entry.getValue()).append("\n");
        }
        return sb.toString().trim();
    }

    public static Frekvenstabell flett(Frekvenstabell f1, Frekvenstabell f2) {
        Frekvenstabell flettet = new Frekvenstabell();

        for (Map.Entry<String, Integer> entry : f1.entrySet()) {
            flettet.put(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<String, Integer> entry : f2.entrySet()) {
            flettet.put(entry.getKey(), flettet.getOrDefault(entry.getKey(), 0) + entry.getValue());
        }
        
        return flettet;
    }

    public void skrivTilFil(String filnavn) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filnavn))) {
            writer.write(this.toString());
        } catch (IOException e) {
            System.err.println("Feil ved skriving til fil: " + e.getMessage());
        }
    }
}