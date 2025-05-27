public class Person {
    private Bil3 minBil;

    public Person(Bil3 bilObj) {
        minBil = bilObj;
    }

    public void skrivUt() {
        System.out.println(minBil.hentNummer());
    }
}
