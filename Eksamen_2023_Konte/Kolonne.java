package IN1010.Eksamen_2023_Konte;

public class Kolonne {
    protected Bil første;
    protected Bil siste;
    

    public Kolonne() {
        this.første = null;
        this.siste = null;
    }

    public void leggTilBil(Bil bil) throws UgyldigBilUnntak{
        if (første == null) {
            if (!(bil instanceof Lederbil)) {
                throw new UgyldigBilUnntak("Første bil må være en Lederbil");
            }
            første = bil;
            siste = bil;

        } else {
            if (bil instanceof Personbil && første instanceof Lastebil) {
                throw new UgyldigBilUnntak("Alle biler i kolonnen må være av samme type som første bil");
            } 

            if (bil instanceof Lastebil && første instanceof Personbil) {
                throw new UgyldigBilUnntak("Alle biler i kolonnen må være av samme type som første bil");
            }
            siste.settNeste(bil);
            bil.settForrige(siste);
            siste = bil;
        }
        bil.settKolonne(this);
    }

    public void taUt(Bil bil) throws UgyldigBilUnntak {
        if (bil.hentKolonne() != this) {
            throw new UgyldigBilUnntak("Bilen er ikke i denne kolonnen");
        }

        if (første == bil) {
            if (siste == bil) {
                første = siste = null;
            }
            else if (første.neste instanceof Lederbil) {
                første = første.neste;
                første.forrige = null;
                bil.neste = null;  // fjerne alle naboer for bilen vi fjerner
            }
            else {
                throw new UgyldigBilUnntak("Neste bil er ikke en lederbil");
            }
        }

        else if (bil == siste) {
            siste = siste.forrige;
            siste.neste = null;
            bil.forrige = null;
        }

        else {
            bil.forrige.neste = bil.neste;
            bil.neste.forrige = bil.forrige;
            bil.forrige = bil.neste = null;  // igjen fjerne alle naboer
        }

        bil.settKolonne(null);
    }
}
