class UgyldigListeindeks extends RuntimeException {
    UgyldigListeindeks (String indeks) {
        super("Ugyldig listeindeks: "+indeks);
    }
}
