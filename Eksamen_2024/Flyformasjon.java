package Eksamen_2024;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Flyformasjon implements Iterable<Fly> {
    protected Fly første;

    public Flyformasjon() {
        this.første = null;
    }

    public void leggTil(Fly ny) { // legger til første i lenken
        ny.neste = første;
        første = ny;
    }

    public boolean erMed(String id) {
        Fly curr = første;
        while (curr != null) {
            if (curr.hentId().equals(id)) {
                return true;
            } else {
                curr = curr.neste;
            }
        }
        return false;
    }

    public Fly taUt(String id) { // tar ut bakerst
        Fly curr = første;
        Fly forrige = null;

        while (curr != null) {
            if (curr.hentId().equals(id)) {
                if (forrige == null) { // flyet er første i lenken
                    første = curr.neste;
                } else {
                    forrige.neste = curr.neste;
                }
                return curr;
            } else {
                forrige = curr;
                curr = curr.neste;
            }
        }
        return null;
    }

    @Override
    public Iterator<Fly> iterator() {
        return new FlyIterator();
    }

    private class FlyIterator implements Iterator<Fly> {
        private Fly curr = første;

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public Fly next() {
            if (!(hasNext())) {
                throw new NoSuchElementException();
            }
            Fly temp = curr;
            curr = curr.neste;
            return temp;
        }
    }

    public Passasjerfly[] hentPassesjerFly() {
        int antPassasjerFly = 0;
        for (Fly f : this) {
            if (f instanceof Passasjerfly)
                antPassasjerFly++;
        }

        Passasjerfly[] svar = new Passasjerfly[antPassasjerFly];

        int i = 0;
        for (Fly f : this) {
            if (f instanceof Passasjerfly) {
                svar[i] = (Passasjerfly) f;
                i++;
            }
        }
        return svar;
    }

    public int totalVekt() {
        if (første == null) {
            System.out.println("Ingen fly i denne flyformasjonen");
        }
        int totalVekt = 0;
        for (Fly f : this) {
            totalVekt += f.hentMtow();
        }
        return totalVekt;
    }

    public int maksVekt() { // rekursiv gjennomgang
        if (første == null) {
            return 0;
        }
        return første.finnMaksVektR();
    }

}
