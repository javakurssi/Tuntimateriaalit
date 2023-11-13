package tunti4;

/**
 * Tämä esimerkki havainnollistaa olio-ohjelmoinnin käsitteitä Abstrakti-luokka ja super-avainsana.
 */
public class MuotoEsimerkki {
	public static void main(String[] args) {
		Muoto ympyra = new Ympyra(5.0);
		Muoto suorakulmio = new Suorakulmio(4.0, 6.0);
		
		System.out.println("Ympyrän pinta-ala: " + ympyra.laskePintaAla());
		System.out.println("Suorakulmion pinta-ala: " + suorakulmio.laskePintaAla());

		System.out.println("Ympyrä tulostettuna: " + ympyra);
		System.out.println("Suorakulmio tulostettuna: " + suorakulmio);
	}
}

interface Muoto {
	public double laskePintaAla();
}

// Abstrakti luokka, joka toteuttaa Muoto-rajapinnan
abstract class AbstraktiMuoto implements Muoto {
	private int laskuri;
	
	public AbstraktiMuoto() {
		this.laskuri = 0;
	}
	
	protected void kasvataLaskuria() {
		laskuri++;
	}
	
	public double laskePintaAla() {
		kasvataLaskuria();
		return Math.PI; // Oletustoteutus abstraktissa luokassa
	}
	
	public int getLaskurinSumma() {
		return this.laskuri;
	}
	
	@Override
	public String toString() {
		return "Laskettu: "+laskuri+" kertaa. \n\n";
	}
}

class Ympyra extends AbstraktiMuoto {
	private double sade;

	public Ympyra(double sade) {
		super(); // Kutsutaan AbstraktiMuoto construktoria
		this.sade = sade;
	}

	@Override
	public double laskePintaAla() {
		kasvataLaskuria();
		return Math.PI * sade * sade;
	}
	
	@Override
	public String toString() {
		return super.toString() + "Sade: " + sade;
	}
}

class Suorakulmio extends AbstraktiMuoto {
	private double leveys;
	private double korkeus;

	public Suorakulmio(double leveys, double korkeus) {
		super();
		this.leveys = leveys;
		this.korkeus = korkeus;
	}

	@Override
	public double laskePintaAla() {
		kasvataLaskuria();
		return leveys * korkeus;
	}
	
	@Override
	public String toString() {
		return super.toString() + "Leveys: " + leveys + " Korkeus: " + korkeus;
	}
	
}
