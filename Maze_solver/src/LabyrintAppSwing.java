import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

public class LabyrintAppSwing {
    private static Labyrint labyrint;
    private static final int RUTE_STORRELSE = 30;
    private static JLabel[][] ruteLabels;

    public static void main(String[] args) {
        try {
            labyrint = new Labyrint(new File("labyrinter/13x13.labyrint"));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        JFrame vindu = new JFrame("Labyrint GUI (Swing)");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel gridPanel = new JPanel(new GridLayout(labyrint.hentRader(), labyrint.hentKolonner()));

        ruteLabels = new JLabel[labyrint.hentRader()][labyrint.hentKolonner()];

        for (int rad = 0; rad < labyrint.hentRader(); rad++) {
            for (int kol = 0; kol < labyrint.hentKolonner(); kol++) {
                Rute r = labyrint.hentRute(rad, kol);
                JLabel label = new JLabel();
                label.setOpaque(true);
                label.setPreferredSize(new Dimension(RUTE_STORRELSE, RUTE_STORRELSE));
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setVerticalAlignment(SwingConstants.CENTER);
                label.setBorder(BorderFactory.createLineBorder(Color.GRAY));

                if (r instanceof SortRute) {
                    label.setBackground(Color.BLACK);
                } else {
                    label.setBackground(Color.WHITE);
                    int finalRad = rad;
                    int finalKol = kol;
                    label.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            resetLabyrint();
                            labyrint.finnUtveiFra(finalRad, finalKol);
                            visLøsning();
                        }
                    });
                }

                ruteLabels[rad][kol] = label;
                gridPanel.add(label);
            }
        }

        vindu.add(gridPanel);
        vindu.pack();
        vindu.setVisible(true);
    }

    private static void resetLabyrint() {
        for (int rad = 0; rad < labyrint.hentRader(); rad++) {
            for (int kol = 0; kol < labyrint.hentKolonner(); kol++) {
                Rute r = labyrint.hentRute(rad, kol);
                if (r instanceof SortRute) {
                    ruteLabels[rad][kol].setBackground(Color.BLACK);
                } else {
                    ruteLabels[rad][kol].setBackground(Color.WHITE);
                }
            }
        }
    }

    private static void visLøsning() {
        ArrayList<ArrayList<Koordinat>> utveier = labyrint.hentUtveier();
        if (utveier.isEmpty()) return;

        // Finn korteste vei
        ArrayList<Koordinat> kortest = utveier.get(0);
        for (ArrayList<Koordinat> vei : utveier) {
            if (vei.size() < kortest.size()) {
                kortest = vei;
            }
        }

        // Tegn korteste vei
        for (Koordinat k : kortest) {
            ruteLabels[k.hentRader()][k.hentKolonner()].setBackground(Color.GREEN);
        }
    }
}
