package tunti4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Tämä esimerkki havainnollistaa olio-ohjelmoinnin käsitteitä Abstrakti-luokka, super- ja instanceof 
 * -avainsanat.
 */
public class MuotoEsimerkki {
	public static void main(String[] args) {
		Muoto ympyra = new Ympyra(5.0);
		Muoto suorakulmio = new Suorakulmio(4.0, 6.0);
		
		System.out.println("Ympyrän pinta-ala: " + ympyra.laskePintaAla());
		System.out.println("Suorakulmion pinta-ala: " + suorakulmio.laskePintaAla());

		System.out.println("Ympyrä tulostettuna: " + ympyra);
		System.out.println("Suorakulmio tulostettuna: " + suorakulmio);
		
		if (ympyra instanceof Ympyra) {
			System.out.println("Ympyrä on todella ympyrä");
		}
		
		// Verrataan muotoja, myös olioiden castaus
		AbstraktiMuoto aYmpyra = (AbstraktiMuoto) ympyra;
		AbstraktiMuoto aSuorakulmio = (AbstraktiMuoto) suorakulmio;
		
		int comparisonResult = aYmpyra.compareTo(aSuorakulmio);
		if (comparisonResult < 0) {
			System.out.println("Ympyrä on pienempi kuin Suorakulmio.");
		} else if (comparisonResult > 0) {
			System.out.println("Ympyrä on suurempi kuin Suorakulmio.");
		} else {
			System.out.println("Ympyrä ja Suorakulmio ovat saman kokoisia.");
		}
		
	    List<AbstraktiMuoto> muodot = new ArrayList<>();
	    muodot.add(aYmpyra);
	    muodot.add(aSuorakulmio);
	     
	    // Järjestetään Collections.sort:in avulla pienimmästä suurimpaan
	    Collections.sort(muodot);
	    
		System.out.println("Muodot järjestyksessä: ");

        for (AbstraktiMuoto muoto : muodot) {
            System.out.println(muoto.laskePintaAla());
        }
	     
	}
}

interface Muoto {
	double laskePintaAla();
}

abstract class AbstraktiMuoto implements Muoto, Comparable<AbstraktiMuoto> {
	private int laskuri;
	
	public AbstraktiMuoto() {
		this.laskuri = 0;
	}
	
	protected void kasvataLaskuria() {
		laskuri++;
	}
	
	public double laskePintaAla() {
		kasvataLaskuria();
		return Math.PI;
	}
	
	public int getLaskurinSumma() {
		return this.laskuri;
	}
	
	@Override
	public String toString() {
		return "Laskettu: " + laskuri + " kertaa. \n\n";
	}
	
	// Comparable toteutus pinta-alan perusteella
	@Override
	public int compareTo(AbstraktiMuoto other) {
		return Double.compare(this.laskePintaAla(), other.laskePintaAla());
	}
}

class Ympyra extends AbstraktiMuoto {
	private double sade;

	public Ympyra(double sade) {
		super();
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