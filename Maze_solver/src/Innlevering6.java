import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Innlevering6 {
    public static void main(String[] args) {
         if (args.length < 1) {
            System.out.println("Bruk: java Innlevering6 <filnavn>");
            return;
        }

        Labyrint labyrint = null;

        try {
            File fil = new File(args[0]);
            labyrint = new Labyrint(fil);

            System.out.println("Slik ser labyrinten ut:\n");
            System.out.println(labyrint);
        } catch (FileNotFoundException e) {
            System.out.println("Filen ble ikke funnet: " + args[0]);
            return;
        } catch (Exception e) {
            System.out.println("Noe gikk galt:");
            e.printStackTrace();
            return;
        }

        Scanner tastatur = new Scanner(System.in);

        while (true) {
            System.out.println("\nSkriv inn startkoordinatene <rad> <kolonne> ('-1' for å avslutte)");
            String linje = tastatur.nextLine();
            Scanner linjeleser = new Scanner(linje);
        
            if (!linjeleser.hasNextInt()) {
                System.out.println("Ugyldig input (rad mangler)");
                continue;
            }
        
            int rad = linjeleser.nextInt();
            if (rad == -1) {
                System.out.println("Avslutter programmet.");
                break;
            }
        
            if (rad < 0 || rad >= labyrint.hentRader()) {
                System.out.println("Rad utenfor gyldig område.");
                continue;
            }
        
            if (!linjeleser.hasNextInt()) {
                System.out.println("Ugyldig input (kolonne mangler)");
                continue;
            }
        
            int kol = linjeleser.nextInt();
            if (kol < 0 || kol >= labyrint.hentKolonner()) {
                System.out.println("Kolonne utenfor gyldig område.");
                continue;
            }
        
            System.out.println("\nÅpninger:");
            labyrint.finnUtveiFra(rad, kol);
            linjeleser.close();
        }

        tastatur.close();


    }

    


}
