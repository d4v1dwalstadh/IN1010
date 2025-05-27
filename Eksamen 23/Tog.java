import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.swing.text.DefaultEditorKit.PasteAction;

public class Tog implements Iterable<Skinnegaende> {
    protected Skinnegaende første;
    protected Skinnegaende siste;

    public Tog() {
        første = null;
        siste = null;
    }

    public void leggTil(Skinnegaende ny) { // legger til bakerst, pop()
        if (siste == null) {
            siste = ny;
            første = ny;
        } else {
            siste.neste = ny;
            ny.forrige = siste;
            siste = ny;
        }
    }

    public Skinnegaende taUt(Skinnegaende obj) { // tar ut gitt obj
        if (obj == første) {
            første = obj.neste;
        } else {
            obj.forrige.neste = obj.neste;
        }

        if (obj == siste) {
            siste = obj.forrige;
        } else {
            obj.forrige.neste = null;
        }

        return obj;
    }

    public Skinnegaende finnOgTaUt(String id) { // finner obj basert på id og tar ut
        Skinnegaende temp = første;
        while (temp != null) {
            if (temp.id.equals(id)) {
                return taUt(temp);
            } else {
                temp = temp.neste;
            }
        }
        return null;
    }

    public void leggTilForan(Skinnegaende obj, Skinnegaende ny) { // legger til foran et obj i liste
        ny.neste = obj;
        ny.forrige = obj.forrige;

        if (obj != første) {
            obj.forrige.neste = ny;
        } else {
            første = ny;
        }

        obj.forrige = ny;
    }

    @Override
    public Iterator<Skinnegaende> iterator() {
        return new TogIterator();
    }

    private class TogIterator implements Iterator<Skinnegaende> {
        private Skinnegaende curr = første;

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public Skinnegaende next() {
            if (!hasNext()) {
                throw new NoSuchElementException(); // throw new IllegalStateException("Ingen flere elementer");
            }
            Skinnegaende temp = curr;
            curr = curr.neste;
            return temp;
        }
    }

    public Passasjervogn[] hentPassasjervogner() {
        int antall = 0;
        for (Skinnegaende s : this) {
            if (s instanceof Passasjervogn) {
                antall++;
            }
        }

        Passasjervogn[] arr = new Passasjervogn[antall];
        int i = 0;
        for (Skinnegaende s : this) {
            if (s instanceof Passasjervogn) {
                arr[i++] = (Passasjervogn) s;
            }
        }
        return arr;
    }

    public void sjekkSporvidde() throws FeilSporvidde {
        if (første == null)
            return;

        int fasit = første.sporvidde;
        Skinnegaende nå = første.neste;

        while (nå != null) {
            if (nå.sporvidde != fasit) {
                throw new FeilSporvidde("Ulik sporvidde oppdaget: " + nå.sporvidde + " vs " + fasit);
            }
            nå = nå.neste;
        }
    }

    public void leggTilSikker(Skinnegaende obj) throws FeilSporvidde {
        if (første == null) {
            leggTil(obj);
        }

        Skinnegaende nå = første.neste;

        while (nå != null) {
            if (nå.sporvidde != obj.sporvidde) {
                throw new FeilSporvidde("Kan ikke legge til, ulik sporvidde");
            }
            leggTil(obj);
        }
    }

    public void sjekkSporviddeRekursivt() throws FeilSporvidde {
        if (første != null) {
            sjekkSporviddeRekursivt(første, første.sporvidde);
        }
    }
    private void sjekkSporviddeRekursivt(Skinnegaende nå, int fasit) throws FeilSporvidde {
        if (nå == null) return;  // slutt på listen
        if (nå.sporvidde != fasit) {
            throw new FeilSporvidde("Feil sporvidde: " + nå.sporvidde);
        }
        sjekkSporviddeRekursivt(nå.neste, fasit);  // rekursivt kall
    }

}
