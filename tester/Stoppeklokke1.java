package tester;

import java.util.Scanner;

// Stoppeklokke som ikke stopper når main() terminerer
class Stoppeklokke1 {
    public static void main(String[] arg) {
        Scanner tastatur = new Scanner(System.in);
        System.out.print("Trykk Return for å starte... ");
        tastatur.nextLine();

        Thread klokke = new Thread(new Klokke());
        klokke.setDaemon(true);
        klokke.start();

        System.out.print("Trykk Return for å stoppe...");
        tastatur.nextLine();
        System.out.println("Takk for nå");
    }
}

class Klokke implements Runnable {
    @Override
    public void run() {
        int tid = 0;
        while (true) {
            System.out.print(tid + " ");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("feil");
            }
            if (tid == 10000) {
                break;
            }
            tid++;
        }
    }
}