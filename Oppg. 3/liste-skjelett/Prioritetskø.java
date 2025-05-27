class Prioritetskø <E extends Comparable<E>> extends Lenkeliste<E> {
    @Override
    public void leggTil(E x) {
        Node ny = new Node(x, null);
        if (foerste == null || foerste.elem.compareTo(x) > 0) {
            ny.neste = foerste;
            foerste = ny;
            if (siste == null) { // hvis liste var tom
                siste = ny;
            }
        } else {
            Node temp = foerste;
            if (temp.neste != null && temp.neste.elem.compareTo(x) <= 0) {
                temp = temp.neste;
            }
            ny.neste = temp.neste;
            temp.neste = ny;
            if (ny.neste == null) {
                siste = ny;
            }
        }
        antall++;
    }

    // hent og fjern fungerer allerede i tråd med oppgaven
}
