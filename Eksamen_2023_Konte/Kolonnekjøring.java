package IN1010.Eksamen_2023_Konte;

public class Kolonnekjøring {
    public static void main(String[] args) {
        KolonneG<Lastebil> kolonne = new KolonneG<>();
        LederLestebil lastebil = new LederLasteBil(120, 8000, 30);

        kolonne.settInn(lastebil);
        LederLastebil lastebil2 = kolonne.taUtSiste();

        System.out.println((Lederbil)lastebil2.egnethet);  // typekonvert fordi kolonne er fylt med lastebiler, ikke ledere. 
    }
}
