package liste_skjelett;

class Stabel <E> extends Lenkeliste<E> {
    @Override
    public void leggTil(E x) {
        Node ny = new Node(x);
        ny.neste = foerste;
        foerste = ny;
        if (siste == null) {
            siste = ny;
        }
        antall++;
    }
}
