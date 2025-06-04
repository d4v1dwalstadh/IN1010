package Eksamen_2024_konte;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Konkurransegruppe implements Iterable<Seilfly> {
    protected Seilfly første;
    protected Seilfly siste;

    public Konkurransegruppe(Seilfly første, Seilfly siste) {
        this.første = første;
        this.siste = siste;
    }

    public void leggTil(Seilfly ny) {
        if (første == null) {
            første = siste = ny;
        } else {
            siste.neste = ny;
            ny.forrige = siste;
            siste = ny;
        }
    }

    public boolean erMed(String id) {
        Seilfly curr = første;
        while (curr != null) {
            if (curr.id == id) {
                return true;
            }
            curr = curr.neste;
        }
        return false;
    }

    public Seilfly taUt(Seilfly fly) {
        Seilfly curr = første;
        while (curr != null) {
            if (curr == fly) {
                if (første == siste) {
                    første = siste = null;
                } else if (curr == første) {
                    første = første.neste;
                    første.forrige = null;
                } else if (curr == siste) {
                    siste = siste.forrige;
                    siste.neste = null;
                } else {
                    curr.forrige.neste = curr.neste;
                    curr.neste.forrige = curr.forrige;
                }
                curr.forrige = null;
                curr.neste = null;
                return curr;
            }
            curr = curr.neste;
        }
        return null;
    }

    @Override
    public Iterator<Seilfly> iterator() {
        return new SeilflyIterator();
    }

    private class SeilflyIterator implements Iterator<Seilfly> {
        Seilfly curr = første;

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public Seilfly next() {
            if (!(hasNext())) {
                throw new NoSuchElementException();
            }
            Seilfly temp = curr;
            curr = curr.neste;
            return temp;
        }
    }

    public Seilfly[] hentEkteSeilfly() {
        int teller = 0;
        for (Seilfly s : this) {
            if (s instanceof EkteSeilfly) {
                teller++;
            }
        }

        Seilfly[] svar = new Seilfly[teller];
        int i = 0;
        for (Seilfly s : this) {
            if (s instanceof EkteSeilfly) {
                svar[i] = s;
                i++;
            }
        }
        return svar;
    }

    public int besteGlidetall() {
        if (første == null) {
            return 0; // listen er tom
        }
        int max = 0;
        for (Seilfly s : this) {
            if (s.glideTall > max) {
                max = s.glideTall;
            }
        }
        return max;
    }

    public int størstSpennvidde() {  // rekursiv
        if (første == null) {
             return 0;
        }
        return første.finnMaxSpennviddeR();
    }

    public int[] histogramSpennvidde() {
        int[] histo = new int[100];
        for (Seilfly s : this) {
            histo[s.vingespenn] += 1;
        }
        return histo;
    }
}
