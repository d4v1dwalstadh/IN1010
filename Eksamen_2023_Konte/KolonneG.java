package IN1010.Eksamen_2023_Konte;

public class KolonneG <E extends Bil> {
    private E første, siste;
    private int antall;

    public void settInn(E ny) {
        if (første == 0) {
            første = ny;
            siste = ny;
        } else {
            siste.neste = ny;
            ny.forrige = siste;
            siste = ny;
        }
    }

    public E taUtSiste() {
        if (første == 0) {
            return null;
        } 
        E temp = siste;
        if (første == siste) {
            første = siste = null;
            return temp;
        } else {
            siste = siste.forrige;
            siste.neste = null;
            temp.forrige = null;
            return temp;
        }
    }
}
