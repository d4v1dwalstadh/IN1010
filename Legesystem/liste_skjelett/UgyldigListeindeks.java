package Legesystem.liste_skjelett;

class UgyldigListeindeks extends RuntimeException {
    UgyldigListeindeks (String indeks) {
        super("Ugyldig listeindeks: "+indeks);
    }
}
