import java.util.Scanner;

public class GameOfLife {
    public static void main(String Args[]) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Oppgi antall rader: ");
        int rader = scanner.nextInt();
        System.out.println("Oppgi antall kolonner: ");
        int kolonner = scanner.nextInt();

        Verden verden = new Verden(rader, kolonner);
        verden.tegn();


        while (true) {
            System.out.println("Ønsker du å fortsette? (ja/nei): ");
            String svar = scanner.next().toLowerCase();

            if (!svar.equals("ja")){
                break;
            }

            verden.oppdatering();
            verden.tegn();
        }

        System.out.println("Simulering avsluttet");
        scanner.close();
    }
}
