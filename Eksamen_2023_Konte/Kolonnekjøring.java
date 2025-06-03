package Eksamen_2023_Konte;

public class Kolonnekjøring {
    public static void main(String[] args) {
        KolonneG<Lastebil> kolonne = new KolonneG<>();
        LederLastebil lastebil = new LederLastebil(120, 8000, 30);

        kolonne.settInn(lastebil);
        LederLastebil lastebil2 = (LederLastebil) kolonne.taUtSiste();

        System.out.println(((Lederbil)lastebil2).hentEgnethet());  // typekonvert fordi kolonne er fylt med lastebiler, ikke ledere. 
    }
}
