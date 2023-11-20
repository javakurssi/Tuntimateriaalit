package tunti5;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import tunti5.apuluokat.Kaupunki;

public class PerusFunktionaalisenEsimerkit {

    public static void main(String[] args) {
    	
    	List<Kaupunki> kaupungit = List.of(new Kaupunki("Helsinki", 500_000), 
    			new Kaupunki("Tampere", 200_000), new Kaupunki("Pori", 70_000));
    	
    	//Esimerkki 1. Verrataan for-loop ja stream-toteutuksia.
		System.out.println("Summa for loopilla = " + laskeKaupunkienAsukasluvutForLoop(kaupungit));
		System.out.println("Summa streameilla = " + laskeKaupunkienAsukasluvutStream(kaupungit));

        List<Integer> numerot = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Esimerkki 2. Käytetään Streamia ja lambdalauseketta parittaisten numeroiden suodattamiseen
        List<Integer> parillisetNumerot = numerot.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList()); // Stream pitää "kerätä", jotta se oikeasti "suoritetaan" eli evaluoidaan.
        System.out.println("Parilliset numerot: " + parillisetNumerot);

        // Esimerkki 3. Käytetään Streamia ja lambdalauseketta jokaisen numeron neliöimiseen
        List<Integer> nelioitytNumerot = numerot.stream()
                .map(n -> n * n)
                .toList();
        System.out.println("Neliöidyt numerot: " + nelioitytNumerot);

        // Esimerkki 4. Käytetään Streamia ja lambdalauseketta suurimman numeron löytämiseen
        int suurin = numerot.stream()
                .max(Integer::compareTo) // IntegerStream:issa on käytössä erityismetodeita kuten max.
                .orElse(0); // On mahdollista että Streamista ei palautunut mitään, joten tulos oli "Optional".
        System.out.println("Suurin numero: " + suurin);
        
        // Esimerkki 5. Annetaan lambda-funktio parametriksi toiselle funktiolle
        //(Tässä tapauksessa funktio tulostaa numerolistan arvojen neliöitä)
        consumeWithFunction(numerot, n -> System.out.println("Neliö: " + (n * n)));
                
        // Lisäesimerkki 6. Käytetään Streamia ja lambdalauseketta summan laskemiseen
        int summa = numerot.stream()
                .reduce(0, Integer::sum); // Reducer on erityinen funktio, joka kerryttää streamista lopulta yhden arvon.
        System.out.println("Numeroiden summa: " + summa);
    }
    
    // Esimerkki funktiosta, joka ottaa toisen funktion parametrina
    private static void consumeWithFunction(List<Integer> numbers, Consumer<Integer> consumerFunction) {
        numbers.forEach(consumerFunction); // Tässä listasta ei tarvitse luoda streamia.
    }
    
    private static int laskeKaupunkienAsukasluvutForLoop(List<Kaupunki> kaupungit) {
		int summa = 0;
		for (Kaupunki kaupunki: kaupungit) {
		    int asukasLuku = kaupunki.getAsukasluku();
		    if (asukasLuku > 100_000) {
		    	summa += asukasLuku;
		    }
		}
		return summa;
    }
    
    private static int laskeKaupunkienAsukasluvutStream(List<Kaupunki> kaupungit) {
        return kaupungit.stream()
                .filter(kaupunki -> kaupunki.getAsukasluku() > 100_000)
                .mapToInt(Kaupunki::getAsukasluku)
                .sum();
    }
}