package tunti4;

/**
 * Lasketaan oliomallinnuksen mukaan mallinnetulla oliolaskurilla lasku
 * (1+(2*(3+2))) = 11. Tarkoitus on havainnollistaa olioiden monimuotoisuutta ja
 * oliomallinnusta sekä tarjota vertailua muihin paradigmoihin. Huomionarvoista
 * on myös se, että tämä toteutus ei sisällä yhtään if lausetta tai for-looppia
 * toisin kuin imperatiivinen toteutus. Tästä voi myös huomata, että ongelman
 * mallintaminen olioiksi tuottaa aika paljon koodirivejä.
 * 
 * Vrt. ImperatiivinenLaskuri.java
 */
public class Oliolaskuri {

    public static void main(final String[] args) {
        int tulos = new Sum(new Id(1), new Mul(new Id(2), (new Sum(new Id(3), new Id(2))))).laske();
        System.out.println("Summa = " + tulos);
    }
}

interface Operaatio {
    public int laske();
}

class Sum implements Operaatio {

    private Operaatio x1, x2;

    public Sum(Operaatio x1, Operaatio x2) {
        this.x1 = x1;
        this.x2 = x2;
    }

    @Override
    public int laske() {
        return x1.laske() + x2.laske();
    }

}

class Mul implements Operaatio {

	private Operaatio x1, x2;

    public Mul(Operaatio x1, Operaatio x2) {
        this.x1 = x1;
        this.x2 = x2;
    }

    @Override
    public int laske() {
        return x1.laske() * x2.laske();
    }

}

class Id implements Operaatio {

	private int x1;

    public Id(int x1) {
        this.x1 = x1;
    }

    @Override
    public int laske() {
        return x1;
    }

}
