class IndeksertListe<E> extends Lenkeliste<E> {
    public void leggTil(int pos, E x) {
        if (pos < 0 || pos > antall) {
            throw new UgyldigListeindeks("Ugyldig indeks: " + pos);
        }

        if (pos == 0) {
            foerste = new Node(x, foerste);
            if (siste == null) {
                siste = foerste;
            }
        } else {
            Node temp = foerste;
            for (int i = 0; i < pos - 1; i++) {
                temp = temp.neste;
            }
            Node ny = new Node(x, temp.neste);
            temp.neste = ny;
            if (pos == antall) { // hvis man la til på slutten, oppdater siste
                siste = ny;
            }
        }

        antall++;
    }

    public void sett(int pos, E x) {
        if (pos < 0 || pos >= antall) {
            throw new UgyldigListeindeks("ugyldig indeks: " + pos);
        }

        Node temp = foerste;
        for (int i = 0; i < pos; i++) {
            temp = temp.neste;
        }

        temp.elem = x;
    }

    public E hent(int pos) {
        if (pos < 0 || pos >= antall) {
            throw new UgyldigListeindeks("Ugyldig indeks:  " + pos);
        }

        Node temp = foerste;
        for (int i = 0; i < pos; i++) {
            temp = temp.neste;
        }

        return temp.elem;
    }

    public E fjern(int pos) {
        if (pos < 0 || pos >= antall) {
            throw new UgyldigListeindeks("Ugyldig indeks: " + pos);
        }

        E fjernetElement;
        if (pos == 0) {
            fjernetElement = foerste.elem;
            foerste = foerste.neste;
            if (foerste == null) {
                siste = null;
            }
        } else {
            Node temp = foerste;
            for (int i = 0; i < pos - 1; i++) {
                temp = temp.neste;
            }
            fjernetElement = temp.neste.elem;
            temp.neste = temp.neste.neste;
            if (temp.neste == null) {
                siste = temp;
            }
        }

        antall--;
        return fjernetElement;
    }
}