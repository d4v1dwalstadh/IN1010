package Eksamen_2023_Konte;

public class KolonneG <E extends Bil> {
    private E første, siste;
    private int antall;

    public void settInn(E ny) {
        if (første == null) {
            første = ny;
            siste = ny;
        } else {
            siste.neste = ny;
            ny.forrige = siste;
            siste = ny;
        }
    }

    public E taUtSiste() {
        if (første == null) {
            return null;
        } 
        E temp = siste;
        if (første == siste) {
            første = siste = null;
            return temp;
        } else {
            siste = (E) siste.forrige;
            siste.neste = null;
            temp.forrige = null;
            return temp;
        }
    }
}
