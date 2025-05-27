class Stabel <E> extends Lenkeliste<E> {
    @Override
    public void leggTil(E x) {
        Node ny = new Node(x, foerste);
        foerste = ny;
        if (siste == null) {
            siste = ny;
        }
        antall++;
    }
}
