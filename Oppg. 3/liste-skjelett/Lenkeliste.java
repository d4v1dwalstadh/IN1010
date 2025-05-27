import java.util.Iterator;

abstract class Lenkeliste<E> implements Liste<E> {
    protected class Node {
        E elem;
        Node neste;

        Node(E elem, Node neste) {
            this.elem = elem;
            this.neste = neste;
        }
    }

    protected Node foerste;
    protected Node siste;
    protected int antall;

    Lenkeliste() {
        foerste = null;
        siste = null;
        antall = 0;
    }

    @Override
    public String toString() {
        StringBuilder resultat = new StringBuilder("[");
        Node temp = foerste;

        while (temp != null) {
            resultat.append(temp.elem);
            temp = temp.neste;
            if (temp != null) { // Sjekker før vi går videre
                resultat.append(", ");
            }
        }

        resultat.append("]");
        return resultat.toString();
    }

    @Override
    public int størrelse() {
        return antall; // ...
    }

    @Override
    public void leggTil(E x) { // sett inn sist funksjon
        Node ny = new Node(x, null);
        if (foerste == null) {
            foerste = ny;
            siste = ny;
        } else {
            siste.neste = ny;
            siste = ny;
        }
        antall++;
    }

    @Override
    public E hent() { // hent første element
        if (foerste == null) {
            throw new UgyldigListeindeks("Listen er tom");
        }
        return foerste.elem; // ...
    }

    @Override
    public E fjern() { // fjern og returner første element
        if (foerste == null) {
            throw new UgyldigListeindeks("Listen er tom");
        }
        E fjernetElement = foerste.elem;
        foerste = foerste.neste;
        if (foerste == null) { // Hvis vi fjernet siste element
            siste = null;
        }
        antall--;
        return fjernetElement;
    }

    private class LenkelisteIterator implements Iterator<E> {
        private Node it = foerste;

        @Override
        public boolean hasNext() {
            return it != null;
        }

        @Override
        public E next() {
            // if (!hasNext()) {
            //     throw new IllegalStateException("Ingen flere elementer");
            // }
            E verdi = it.elem;
            it = it.neste;
            return verdi;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new LenkelisteIterator();
    }
}
