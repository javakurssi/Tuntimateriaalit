package tunti4;

public class MuotoEsimerkki {
	public static void main(String[] args) {
		Muoto ympyra = new Ympyra(5.0);
		Muoto suorakulmio = new Suorakulmio(4.0, 6.0);

		System.out.println("Ympyrän pinta-ala: " + ympyra.laskePintaAla());
		System.out.println("Suorakulmion pinta-ala: " + suorakulmio.laskePintaAla());
	}
}

interface Muoto {
	double laskePintaAla();
}

//Abstrakti luokka, joka toteuttaa Muoto-rajapinnan
abstract class AbstraktiMuoto implements Muoto {
	public double laskePintaAla() {
		return Math.PI; // Oletustoteutus abstraktissa luokassa
	}
}

class Ympyra extends AbstraktiMuoto {
	private double sade;

	public Ympyra(double sade) {
		this.sade = sade;
	}

	@Override
	public double laskePintaAla() {
		return Math.PI * sade * sade;
	}
}

class Suorakulmio extends AbstraktiMuoto {
	private double leveys;
	private double korkeus;

	public Suorakulmio(double leveys, double korkeus) {
		this.leveys = leveys;
		this.korkeus = korkeus;
	}

	@Override
	public double laskePintaAla() {
		return leveys * korkeus;
	}
}
