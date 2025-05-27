public class Bilbruk3 {
    public static void main(String[] args) {
        Bil3 bil = new Bil3("EK54949");
        Person person = new Person(bil);
        person.skrivUt();
    }
}
