package tunti4;

/**
 * Tämän esimerkin tarkoituksena on havainnollistaa olioiden monimuotoisuutta ja
 * Dependency Injection -patternia.
 */
public class LentokoneConfig {

    private final Lentokone superlentokone;
    private final Lentokone halpalentokone;

    public LentokoneConfig() {
    	
    	// Konfiguroidaan superlentokone, laitetaan supermoottori tähän konfiguraatioon
        superlentokone = new Lentokone();
        Moottori moottori = new SuperMoottori();
        superlentokone.setMoottori(moottori);

    	// Konfiguroidaan halpa lentokone, laitetaan halpa moottori tähän konfiguraatioon
        halpalentokone = new Lentokone();
        Moottori moottori2 = new HalpaMoottori();
        halpalentokone.setMoottori(moottori2);
    }

}

class Lentokone {

	private Moottori moottori; // Pelkkä rajapinta riittää Lentokoneelle, sen ei kuulu tietää toteutusyksityiskohtia.

    // Setter metodilla voidaan asettaa haluttu moottori lentokoneelle
    public void setMoottori(Moottori moottori) {
        this.moottori = moottori;
    }

    // Lentokoneen tehtävä on osata lentäminen kokonaisuutena, moottori huolehtii omista yksityiskohdistaan. 
    private void lenna() {
        moottori.kaynnista();
        moottori.kaytaMoottoria();
    }
}

interface Moottori {
    public void kaytaMoottoria();

    public boolean kaynnista();
}

class SuperMoottori implements Moottori {

    public void kaytaMoottoria() {
        // tee jotain superjuttuja
    }

    public boolean kaynnista() {
        // Tee supermoottorin käynnistämiseen tarvittavat toimet
        return false;
    }

}

class HalpaMoottori implements Moottori {

    public void kaytaMoottoria() {
        // aja hitaasti
    }

    public boolean kaynnista() {
        // Tee halvan moottorin käynnistämiseen tarvittavat toimet
        return false;
    }

}